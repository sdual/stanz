package com.github.stenoritama.stanz.typeclass

import scala.util.Random

trait MonadSampleable[F[_]] {
  def sample[A](fa: F[A])(random: Random): A
}
