package com.github.sdual.stanz

import com.github.sdual.stanz.functor.Function0Instance
import com.github.sdual.stanz.monad.DistributionInstance
import com.github.sdual.stanz.syntax.ToTypeClassOps

object Stanz extends ToTypeClassOps with MonadInstance

trait MonadInstance extends DistributionInstance with Function0Instance
