package com.github.sdual.stanz.distribution

import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

class PrimitiveDistributionSpec extends WordSpec with Matchers with MockFactory {

  "Bernoulli" when {
    "sample method" should {

      "return true if random.nextDouble returns the value less than the specified value(0.5)" in {
        val randomMock: Random = mock[Random]
        (randomMock.nextDouble _).expects().returning(0.2)
        val dist: PrimitiveDistribution[Boolean] = Bernoulli(0.5)
        val actual: Boolean = dist.sample(randomMock)

        actual should be(true)
      }

      "return false if random.nextDouble returns a value greater than the specified value(0.5)" in {
        val randomMock: Random = mock[Random]
        (randomMock.nextDouble _).expects().returning(0.7)
        val dist: PrimitiveDistribution[Boolean] = Bernoulli(0.5)
        val actual: Boolean = dist.sample(randomMock)

        actual should be(false)
      }
    }
  }

  "Gaussian" when {
    "sample method" should {

      "return a value sampled from Gaussian distribution" in {
        val randomMock: Random = mock[Random]
        (randomMock.nextGaussian _).expects().returning(0.5)
        val dist: PrimitiveDistribution[Double] = Gaussian(0.0, 1.0)
        val actual: Double = dist.sample(randomMock)

        actual should be(0.5)
      }
    }

  }
}
