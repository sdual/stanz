package com.github.sdual.stanz.typeclass

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
object Functor {
  def apply[F[_]](implicit T: Functor[F]): Functor[F] = T
}

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: => A): F[A]
  def ap[A, B](fa: => F[A])(f: => F[A => B]): F[B]
}

object Applicative {
  def apply[F[_]](implicit T: Applicative[F]): Applicative[F] = T
}
