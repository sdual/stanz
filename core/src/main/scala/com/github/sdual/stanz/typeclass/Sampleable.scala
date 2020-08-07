package com.github.sdual.stanz.typeclass

import simulacrum.typeclass

import scala.util.Random

@typeclass trait Sampleable[F[_]] {
  def sample[A](fa: F[A])(random: Random): A
}
