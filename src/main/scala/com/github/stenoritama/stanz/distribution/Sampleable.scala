package com.github.stenoritama.stanz.distribution

import scala.util.Random

trait Sampleable[A] extends Distribution[A] {
  def sample(random: Random): A
}

trait DistMonadSampleable[F[_]] {
  def sample[A](fa: F[A])(random: Random): A
}

object DistMonadSampleable {
  def apply[F[_]](implicit F: DistMonadSampleable[F]): Distribution[F] = F
}
