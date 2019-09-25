package com.github.sdual.stanz.prototype

import com.github.sdual.stanz.prototype.functor.Function0Instance
import com.github.sdual.stanz.prototype.monad.DistributionInstance
import com.github.sdual.stanz.prototype.syntax.ToTypeClassOps

object Stanz extends ToTypeClassOps with MonadInstance

trait MonadInstance extends DistributionInstance with Function0Instance
