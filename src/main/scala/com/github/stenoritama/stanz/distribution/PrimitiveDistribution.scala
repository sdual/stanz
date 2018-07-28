package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability
import com.github.stenoritama.stanz.distribution.Distribution.Primitive

import scala.util.Random

trait PrimitiveDistribution {

  implicit def bernoulliToPrimitiveDistribution(dist: Bernoulli): Primitive[Boolean] = Primitive(dist)

  implicit def normalToPrimitiveDistribution(dist: Normal): Primitive[Double] = Primitive(dist)

}

class Bernoulli(prob: Probability) extends Sampleable[Boolean] {
  def sample(random: Random): Boolean = {
    prob > random.nextDouble()
  }
}

class Normal(mean: Double, stdDev: Double) extends Sampleable[Double] {
  def sample(random: Random): Double = {
    val sampled = random.nextGaussian()
    (stdDev * sampled) + mean
  }
}

trait ToPrimitiveDistribution extends PrimitiveDistribution
