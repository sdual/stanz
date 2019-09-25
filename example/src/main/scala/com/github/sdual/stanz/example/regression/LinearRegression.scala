package com.github.sdual.stanz.example.regression

import java.io.{File, FileOutputStream, OutputStreamWriter}

import com.github.sdual.stanz.prototype.Probability
import com.github.sdual.stanz.prototype.Stanz._
import com.github.sdual.stanz.prototype.algorithm.{MetropolisHastings, MetropolisHastingsImpl}
import com.github.sdual.stanz.prototype.distribution.Gaussian
import com.github.sdual.stanz.prototype.monad.Distribution
import com.github.sdual.stanz.prototype.monad.Distribution.{Conditional, Primitive}
import org.apache.commons.math3.distribution.NormalDistribution

import scala.util.Random

object LinearRegression extends App {

  val mh: MetropolisHastings = MetropolisHastingsImpl()
  val r = new Random
  val n = 100000

  val ps: Distribution[(Double, Double)] = points(createTrainingData(), linear())

  val sampled = mh.run(n, ps).sample(r)

  // print sampled data.
  println(sampled.map(x => s"${x._1},${x._2}").mkString("\n"))

  val fileName = "output/linear-regression-result.csv"
  val outputFile = new File(fileName)
  val fileOutPutStream = new FileOutputStream(outputFile, true)
  val writer = new OutputStreamWriter(fileOutPutStream, "UTF-8")

  // write sampled values of parameter a and b.
  writer.write("a,b\n")
  for {
    line <- sampled.reverse.map(x => s"${x._1},${x._2}").mkString("\n")
  } yield writer.write(line)

  writer.close()

  def point(data: (Double, Double), dist: Distribution[(Double, Double)]): Distribution[(Double, Double)] = {
    def func(param: (Double, Double)): Probability = {
      val norm = new NormalDistribution(param._1 * data._1 + param._2, 1.0)
      norm.density(data._2)
    }

    Conditional(dist, func)
  }

  def points(data: List[(Double, Double)], dist: Distribution[(Double, Double)]): Distribution[(Double, Double)] = {
    data.foldLeft(dist) { (u, v) => point(v, u) }
  }

  def linear(): Distribution[(Double, Double)] = {

    def normal(mean: Double, stdDev: Double): Distribution[Double] = Primitive(Gaussian(mean, stdDev))

    for {
      a <- normal(0.0, 1.0)
      b <- normal(0.0, 1.0)
    } yield (a, b)
  }

  def createTrainingData(): List[(Double, Double)] = {
    val points = (0.0 to 2.0 by 1.0).toList

    def equation(x: Double): Double = -0.5 * x + 0.3

    def addNoise(x: Double): Double = (new Random).nextGaussian() * 0.2 + x

    points.map { x =>
      (x, addNoise(equation(x)))
    }
  }

}
