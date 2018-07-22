package com.github.stenoritama.stanz.distribution

import scala.util.Random

trait Sampleable[A] {
  def sample(random: Random): A
}
