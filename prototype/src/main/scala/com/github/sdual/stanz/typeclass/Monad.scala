package com.github.sdual.stanz.typeclass

trait Monad[F[_]] extends Applicative[F] {
  def point[A](a: => A): F[A] = pure(a)
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
}

object Monad {
  def apply[F[_]](implicit M: Monad[F]): Monad[F] = M
}
