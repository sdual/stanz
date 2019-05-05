package com.github.sdual.stanz.monad

import com.github.sdual.stanz.typeclass.Monad

sealed trait FreeM[F[_], A]

case class Impure[F[_], A](ff: F[FreeM[F, A]]) extends FreeM[F, A]
case class Pure[F[_], A](a: A) extends FreeM[F, A]


trait FreeMInstance {

  implicit def freeMInstance[F[_]]: Monad[({type f[x] = FreeM[F, x]})#f] = new Monad[({type f[x] = FreeM[F, x]})#f] {

    override def flatMap[A, B](fa: FreeM[F, A])(f: A => FreeM[F, B]): FreeM[F, B] = ???

    override def pure[A](a: => A): FreeM[F, A] = ???

    override def ap[A, B](fa: => FreeM[F, A])(f: => FreeM[F, A => B]): FreeM[F, B] = ???

    override def map[A, B](fa: FreeM[F, A])(f: A => B): FreeM[F, B] = ???
  }

}

object FreeMInstance extends FreeMInstance
