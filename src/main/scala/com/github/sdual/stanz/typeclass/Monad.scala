package com.github.sdual.stanz.typeclass

trait Monad[T[_]] extends Applicative[T] {
  def point[A](a: => A): T[A] = pure(a)
  def flatMap[A, B](fa: T[A])(f: A => T[B]): T[B]
}

object Monad {
  def apply[T[_]](implicit T: Monad[T]): Monad[T] = T
}
