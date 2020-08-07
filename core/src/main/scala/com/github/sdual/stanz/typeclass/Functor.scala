package com.github.sdual.stanz.typeclass

import simulacrum.typeclass

@typeclass trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
