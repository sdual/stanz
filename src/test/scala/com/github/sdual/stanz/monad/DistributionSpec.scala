package com.github.sdual.stanz.monad

import com.github.sdual.stanz.Stanz._
import com.github.sdual.stanz.distribution.PrimitiveDistribution
import com.github.sdual.stanz.typeclass.Monad
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

class DistributionSpec extends WordSpec with Matchers with MockFactory {

  val gaussianDist: Distribution[Double] = PrimitiveDistribution.gaussian(0.0, 1.0)

  def testFunc1(x: Double): Distribution[Double] = Distribution.Point(x + 10.0)
  def testFunc2(x: Double): Distribution[Double] = Distribution.Point(x - 20.0)

  "Distribution" when {
    "A probability Monad" should {
      "have left identity" in {
        val randomMock: Random = mock[Random]
        (Monad[Distribution].pure(20.0) flatMap testFunc1).sample(randomMock) should be(testFunc1(20.0).sample(randomMock))
      }
      "have right identity" in {
        val randomMock: Random = mock[Random]
        (randomMock.nextGaussian _).expects().returning(0.5)
        (randomMock.nextGaussian _).expects().returning(0.5)
        (gaussianDist flatMap (x => Monad[Distribution].pure(x))).sample(randomMock) should be(gaussianDist.sample(randomMock))
      }
      "satisfy associative law" in {
        val randomMock: Random = mock[Random]
        (randomMock.nextGaussian _).expects().returning(0.5)
        (randomMock.nextGaussian _).expects().returning(0.5)
        ((gaussianDist flatMap testFunc1) flatMap testFunc2).sample(randomMock) should be(
          (gaussianDist flatMap {testFunc1(_) flatMap testFunc2}).sample(randomMock)
        )
      }
    }
  }
}
