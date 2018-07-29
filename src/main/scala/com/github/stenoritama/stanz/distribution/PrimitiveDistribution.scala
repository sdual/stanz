package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability

import scala.util.Random

class Bernoulli(prob: Probability) extends Sampleable[Boolean] {
  def sample(random: Random): Boolean = {
    prob > random.nextDouble()
  }
}

object Bernoulli {
  def apply(prob: Probability): Distribution[Boolean] = new Bernoulli(prob)
}

class Normal(mean: Double, stdDev: Double) extends Sampleable[Double] {
  def sample(random: Random): Double = {
    val sampled = random.nextGaussian()
    (stdDev * sampled) + mean
  }
}

object Normal {
  def apply(mean: Probability, stdDev: Probability): Distribution[Double] =
    new Normal(mean, stdDev)
}
