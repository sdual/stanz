package com.github.sdual.stanz.prototype.functor

import com.github.sdual.stanz.prototype.typeclass.Functor

trait Function0Instance {
  implicit val f0Functor: Functor[Function0] = new Functor[Function0] {
    override def map[A, B](fa: () => A)(f: A => B): () => B = {
      () => f(fa())
    }
  }
}

object Function0Instance extends Function0Instance
