package com.github.sdual.stanz.algorithm

import com.github.sdual.stanz.Probability
import com.github.sdual.stanz.distribution.Distribution

trait PriorDistribution {
  def proposal[A](dist: Distribution[A]): Distribution[(A, Probability)]
}

trait MetropolisHastings {
  def run[A](n: Int, d: Distribution[A]): Distribution[List[A]]
}
