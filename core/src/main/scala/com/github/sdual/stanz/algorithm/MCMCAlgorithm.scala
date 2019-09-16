package com.github.sdual.stanz.algorithm

import com.github.sdual.stanz.monad.Distribution

trait MCMCAlgorithm[A <: RandomVariable] {
  def run(num: Int, dist: Distribution[A]): Distribution[List[A]]
}
