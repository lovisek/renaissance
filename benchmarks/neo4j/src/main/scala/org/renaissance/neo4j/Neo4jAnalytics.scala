package org.renaissance.neo4j

import java.nio.file.Paths

import org.renaissance.Benchmark._
import org.renaissance.BenchmarkResult
import org.renaissance.License
import org.renaissance.neo4j.analytics.AnalyticsBenchmark
import org.renaissance.Benchmark
import org.renaissance.BenchmarkContext

@Name("neo4j-analytics")
@Group("neo4j")
@Summary("Executes Neo4J graph queries against a movie database.")
@Licenses(Array(License.GPL3))
@Repetitions(20)
final class Neo4jAnalytics extends Benchmark {

  // TODO: Unify handling of scratch directories throughout the suite.
  //  See: https://github.com/renaissance-benchmarks/renaissance/issues/13

  val scratchPath = Paths.get("target", "modules", "neo4j", "neo4j-analytics.db")

  // TODO: Consolidate benchmark parameters across the suite.
  //  See: https://github.com/renaissance-benchmarks/renaissance/issues/27

  var benchmark: AnalyticsBenchmark = new AnalyticsBenchmark(
    scratchPath.toFile,
    sys.props.get("renaissance.neo4j.long-query-count").map(_.toInt),
    sys.props.get("renaissance.neo4j.short-query-count").map(_.toInt),
    sys.props.get("renaissance.neo4j.mutator-query-count").map(_.toInt)
  )

  override def setUpBeforeAll(c: BenchmarkContext): Unit = {
    benchmark.setupAll()
  }

  override def tearDownAfterAll(c: BenchmarkContext): Unit = {
    benchmark.tearAll()
  }

  override def runIteration(config: BenchmarkContext): BenchmarkResult = {
    // TODO: Return something useful for validation
    benchmark.run()

    // TODO: add proper validation
    BenchmarkResult.dummy(benchmark)
  }
}
