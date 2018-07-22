package com.github.stenoritama.stanz.typeclass

trait Functor[T[_]] {
  def map[A, B](fa: T[A])(f: A => B): T[B]
}
object Functor {
  def apply[T[_]](implicit T: Functor[T]): Functor[T] = T
}

trait Applicative[T[_]] extends Functor[T] {
  def pure[A](a: => A): T[A]
  def ap[A, B](fa: => T[A])(f: => T[A => B]): T[B]
}

object Applicative {
  def apply[T[_]](implicit T: Applicative[T]): Applicative[T] = T
}
