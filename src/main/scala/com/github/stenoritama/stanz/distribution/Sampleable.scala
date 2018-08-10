package com.github.stenoritama.stanz.distribution

import scala.util.Random

trait DistMonadSampleable[F[_]] {
  def sample[A](fa: F[A])(random: Random): A
}
