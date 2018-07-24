package com.github.stenoritama.stanz.distribution

import com.github.stenoritama.stanz.Probability
import com.github.stenoritama.stanz.typeclass.Monad
import com.github.stenoritama.stanz.Stanz._
import com.github.stenoritama.stanz.distribution.DistributionInstance._

import scala.annotation.tailrec
import scala.util.Random

trait Distribution[+P]

object Distribution {

  case class Point[P](value: P) extends Distribution[P]
  case class FlatMap[P0, P1](dist: Distribution[P0], f: P0 => Distribution[P1]) extends Distribution[P1]
  case class Primitive[P](fa: Sampleable[P]) extends Distribution[P]
  case class Conditional[P](dist: Distribution[P], likelihood: P => Probability) extends Distribution[P]

  def sample[A](dist: Distribution[A])(random: Random): A = {
    @tailrec
    def loop(dist: Distribution[A], random: Random): A = {
      dist match {
        case pt1: Point[A]      => pt1.value
        case fm1: FlatMap[A, _] => fm1.dist match {
          case pt2: Point[A]      => loop(fm1.f(pt2.value), random)
          case fm2: FlatMap[A, _] => loop(fm2.dist flatMap (a => fm2.f(a) flatMap fm1.f), random)
          case pr2: Primitive[A]  => loop(fm1.f(pr2.fa.sample(random)), random)
        }
        case pr1: Primitive[A] => pr1.fa.sample(random)
        case _ => throw new Exception("can't sample.")
      }
    }

    loop(dist, random)
  }

}

trait DistributionInstance {

  implicit val distributionInstance: Monad[Distribution] = new Monad[Distribution] with DistMonadSampleable[Distribution] {

    override def pure[A](v: => A): Distribution[A] = Distribution.Point(v)

    override def flatMap[A, B](fa: Distribution[A])(f: A => Distribution[B]): Distribution[B] =
      Distribution.FlatMap(fa, f)

    override def map[A, B](fa: Distribution[A])(f: A => B): Distribution[B] =
      Distribution.FlatMap(fa, (a: A) => Distribution.Point(f(a)))

    override def ap[A, B](fa: => Distribution[A])(f: => Distribution[A => B]): Distribution[B] = ???

    override def sample[D](dist: Distribution[D])(random: Random): D = Distribution.sample(dist)(random)

  }

}

object DistributionInstance extends DistributionInstance
