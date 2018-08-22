package com.github.stenoritama.stanz.algorithm

import com.github.stenoritama.stanz.Probability
import com.github.stenoritama.stanz.distribution.Distribution

trait PriorDistribution {
  def proposal[A](dist: Distribution[A]): Distribution[(A, Probability)]
}