package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability

trait Prior {
  def prior[A](dist: Distribution[A]): Distribution[(A, Probability)]
}

class MetropolisHastingsPrior extends Prior {
  def prior[A](dist: Distribution[A]): Distribution[(A, Probability)] = {
    ???
  }
}

object MetropolisHastingsPrior {
  def apply(): MetropolisHastingsPrior = new MetropolisHastingsPrior()
}
