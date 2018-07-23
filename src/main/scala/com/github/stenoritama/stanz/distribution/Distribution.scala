package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability
import com.github.stenoritama.stanz.typeclass.Monad

trait Distribution[P] {
  ???
}

object Distribution {
  case class Point[P](value: P) extends Distribution[P]
  case class FlatMap[P0, P1](dist: Distribution[P0], f: P0 => Distribution[P1]) extends Distribution[P1]
  case class Primitive[P](fa: Sampleable[P]) extends Distribution[P]
  case class Conditional[P](dist: Distribution[P], likelihood: P => Probability) extends Distribution[P]
}

trait DistributionInstance {

  implicit val distributionInstance = new Monad[Distribution] {
    override def pure[A](v: => A): Distribution[A] = Distribution.Point(v)
    override def flatMap[A, B](fa: Distribution[A])(f: A => Distribution[B]): Distribution[B] =
      Distribution.FlatMap(fa, f)
    override def map[A, B](fa: Distribution[A])(f: A => B): Distribution[B] =
      Distribution.FlatMap(fa, (a: A) => Distribution.Point(f(a)))
    override def ap[A, B](fa: => Distribution[A])(f: => Distribution[A => B]): Distribution[B] = ???
  }

}
