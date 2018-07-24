package com.github.stenoritama.stanz.syntax

import com.github.stenoritama.stanz.distribution.Sampleable
import com.github.stenoritama.stanz.typeclass.{Applicative, Functor, Monad}

import scala.util.Random

class FunctorOps[F[_], T](val self: F[T])(implicit val F: Functor[F]) {
  final def map[U](f: T => U): F[U] = F.map(self)(f)
}

class ApplicativeOps[A[_], T](val self: A[T])(implicit val A: Applicative[A]) {
  final def pure[U](v: => U): A[U] = A.pure(v)
  final def ap[U](f: => A[T => U]): A[U] = A.ap(self)(f)
}

class MonadOps[M[_], T](val self: M[T])(implicit val M: Monad[M]) {
  final def point[U](v: => U): M[U] = M.pure(v)
  final def flatMap[U](f: T => M[U]): M[U] = M.flatMap(self)(f)
}

class SampleableOps[S](val self: S)(implicit val S: Sampleable[S]) {
  final def sample(random: Random): S = S.sample(random)
}

trait ToFunctorOps {
  implicit def ToFunctorOps[F[_], T](v: F[T])(implicit F0: Functor[F]): FunctorOps[F, T] =
    new FunctorOps[F, T](v)
}

trait ToApplicativeOps {
  implicit def ToApplicativeOps[A[_], T](v: A[T])(implicit A0: Applicative[A]): ApplicativeOps[A, T] =
    new ApplicativeOps[A, T](v)
}

trait ToMonadOps {
  implicit def ToMonadOps[M[_], T](v: M[T])(implicit M0: Monad[M]): MonadOps[M, T] =
    new MonadOps[M, T](v)
}

trait ToSampleableOps {
  implicit def ToSampleable[D, T](v: D)(implicit D0: Sampleable[D]): SampleableOps[D] =
    new SampleableOps[D](v)
}

trait ToApplicative

trait ToMonad

trait ToSampleable

trait ToTypeClass extends ToFunctorOps with ToApplicative with ToMonad with ToSampleable
