package com.github.sdual.stanz.monad

import com.github.sdual.stanz.Stanz._
import com.github.sdual.stanz.monad.Free.Trampoline
import com.github.sdual.stanz.typeclass.Functor

import scala.annotation.tailrec

sealed trait Free[S[+ _], A] {

  def flatMap[B](f: A => Free[S, B]): Free[S, B] = Free.FlatMap(this, f)

  def map[B](f: A => B): Free[S, B] = flatMap(a => Free.Done(f(a)))

  @tailrec
  final def resume(implicit S: Functor[S]): Either[S[Free[S, A]], A] = {
    this match {
      case Free.Done(value) => Right(value)
      case Free.More(k) => Left(k)
      case Free.FlatMap(a, f) => a match {
        case Free.Done(value) => f(value).resume
        case Free.More(k) => Left(S.map(k)(_ flatMap f))
        case Free.FlatMap(b, g) => b.flatMap((x: Any) => g(x) flatMap f).resume
        case other => throw new IllegalArgumentException(other.toString)
      }
    }
  }

  final def runT(implicit ev: Free[S, A] => Trampoline[A]): A = {
    ev(this).go(_ ())
  }

  private def go(f: S[Free[S, A]] => Free[S, A])(implicit S: Functor[S]): A = {
    @tailrec
    def go2(t: Free[S, A]): A = t.resume match {
      case Left(l) => go2(f(l))
      case Right(r) => r
    }

    go2(this)
  }
}

object Free {

  case class Done[S[+ _], A](a: A) extends Free[S, A]

  case class More[S[+ _], A](k: S[Free[S, A]]) extends Free[S, A]

  case class FlatMap[S[+ _], A, B](a: Free[S, A], f: A => Free[S, B]) extends Free[S, B]

  type Trampoline[A] = Free[Function0, A]

}

object Trampoline {

  def done[A](a: A): Trampoline[A] = Free.Done(a)

  def more[A](k: => Trampoline[A]): Trampoline[A] = Free.More(() => k)

}