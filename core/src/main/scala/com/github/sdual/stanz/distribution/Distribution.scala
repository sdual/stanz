package com.github.sdual.stanz.distribution

import simulacrum.typeclass

import scala.util.Random

@typeclass trait Distribution[A] {
  //def sample(dist: Distribution[A])(random: Random): A
}

object Distribution {
  case class Point[P](value: P) extends Distribution[P]
  case class FlatMap[P0, P1](dist: Distribution[P0], f: P0 => Distribution[P1]) extends Distribution[P1]
  case class Primitive[P](fa: PrimitiveDistribution[P]) extends Distribution[P]
  case class Conditional[P](dist: Distribution[P], likelihood: P => Probability) extends Distribution[P]
}
