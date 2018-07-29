package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability

trait Prior {
  def prior[A](dist: Distribution[A]): Distribution[(A, Probability)]
}
