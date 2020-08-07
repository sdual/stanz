package com.github.sdual.stanz.typeclass

import simulacrum.typeclass

@typeclass trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: => A): F[A]
  def ap[A, B](fa: => F[A])(f: => F[A => B]): F[B]
}
