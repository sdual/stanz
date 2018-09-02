package com.github.sdual.stanz.typeclass

import scala.util.Random

trait MonadSampleable[F[_]] {
  def sample[A](fa: F[A])(random: Random): A
}

object MonadSampleable {
  def apply[F[_]](implicit F: MonadSampleable[F]): MonadSampleable[F] = F
}
