package org.renaissance.jdk.concurrent

import org.renaissance.Benchmark
import org.renaissance.Benchmark._
import org.renaissance.BenchmarkContext
import org.renaissance.BenchmarkResult
import org.renaissance.License

@Name("future-genetic")
@Group("jdk-concurrent")
@Summary("Runs a genetic algorithm using the Jenetics library and futures.")
@Licenses(Array(License.APACHE2))
@Repetitions(50)
final class FutureGenetic extends Benchmark {

  // TODO: Consolidate benchmark parameters across the suite.
  //  See: https://github.com/renaissance-benchmarks/renaissance/issues/27

  val threadCount = 2

  val randomSeed = 7

  var geneMinValue = -2000

  var geneMaxValue = 2000

  var geneCount = 200

  var chromosomeCount = 50

  var generationCount = 5000

  var benchmark: JavaJenetics = null

  override def setUpBeforeAll(c: BenchmarkContext): Unit = {
    if (c.functionalTest) {
      chromosomeCount = 10
      generationCount = 200
    }

    benchmark = new JavaJenetics(
      geneMinValue,
      geneMaxValue,
      geneCount,
      chromosomeCount,
      generationCount,
      threadCount,
      randomSeed
    )

    benchmark.setupBeforeAll()
  }

  override def tearDownAfterAll(c: BenchmarkContext): Unit = {
    benchmark.tearDownAfterAll()
  }

  override def runIteration(c: BenchmarkContext): BenchmarkResult = {
    val result = benchmark.runRepetition()

    // TODO: add proper validation
    BenchmarkResult.dummy(result)
  }
}
