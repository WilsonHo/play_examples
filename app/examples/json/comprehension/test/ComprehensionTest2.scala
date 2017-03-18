package examples.json.comprehension.test

/**
  * Created by wilson on 3/9/17.
  */
object ComprehensionTest2 extends App {
  def foo(n: Int, v: Int) =
    for (i <- 0 to n;
         j <- i to n
         if i + j == v)
      yield (i, j)

  foo(20, 32) foreach {
    case (i, j) =>
      println(s"($i, $j)")
  }

 /* for {
    _ ← List("Some", "data").tell
  } yield someComputation*/
}
