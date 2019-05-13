package com.github.sdual.stanz.algorithm

import com.github.sdual.stanz.Probability
import com.github.sdual.stanz.Stanz._
import com.github.sdual.stanz.distribution.PrimitiveDistribution
import com.github.sdual.stanz.monad.Distribution

import scala.annotation.tailrec

trait MetropolisHastings {
  def run[A](n: Int, d: Distribution[A]): Distribution[List[A]]
}

class MetropolisHastingsImpl(prior: PriorDistribution) extends MetropolisHastings {

  def run[A](n: Int, d: Distribution[A]): Distribution[List[A]] = {

    val proposal: Distribution[(A, Probability)] = prior.proposal(d)

    @tailrec
    def iterate(i: Int, prob: Distribution[List[(A, Probability)]]): Distribution[List[(A, Probability)]] = {
      i match {
        case 0 => prob
        case _ =>
          val nextDist = for {
            p <- prob
            (v1, p1) = p.head
            prop <- proposal
            (v2, p2) = prop
            isAccepted <- PrimitiveDistribution.bernoulli(1.0 min p2 / p1)
            next = if (isAccepted) (v2, p2) else (v1, p1)
          } yield next :: p
          iterate(i - 1, nextDist)
      }
    }

    for {
      result <- iterate(n, proposal.map(x => List(x)))
    } yield result.map(x => x._1)

  }

}

object MetropolisHastingsImpl {
  val prior: PriorDistribution = PriorDistributionImpl()

  def apply(): MetropolisHastings = new MetropolisHastingsImpl(prior)
}
