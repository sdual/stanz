package com.github.sdual.stanz.typeclass

import simulacrum.typeclass

@typeclass trait Monad[F[_]] extends Applicative[F] {
  def point[A](a: => A): F[A] = pure(a)
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
}
