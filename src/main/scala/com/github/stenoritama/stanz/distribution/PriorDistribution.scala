package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability

class PriorDistribution {
  def proposal[A](dist: Distribution[A]): Distribution[(A, Probability)] = {
    ???
  }
}

object PriorDistribution {
  def apply(): PriorDistribution = new PriorDistribution()
}
