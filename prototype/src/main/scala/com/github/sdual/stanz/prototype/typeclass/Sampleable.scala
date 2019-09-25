package com.github.sdual.stanz.prototype.typeclass

import scala.util.Random

trait Sampleable[F[_]] {
  def sample[A](fa: F[A])(random: Random): A
}

object Sampleable {
  def apply[F[_]](implicit F: Sampleable[F]): Sampleable[F] = F
}
