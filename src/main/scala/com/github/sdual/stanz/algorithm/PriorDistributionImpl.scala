package com.github.sdual.stanz.algorithm

import com.github.sdual.stanz.Probability
import com.github.sdual.stanz.distribution.Distribution

class PriorDistributionImpl extends PriorDistribution {
  def proposal[A](dist: Distribution[A]): Distribution[(A, Probability)] = {
    ???
  }
}

object PriorDistributionImpl {
  def apply(): PriorDistribution = new PriorDistributionImpl()
}
