package examples.json.comprehension.test

/**
  * Created by wilson on 3/9/17.
  */
object ComprehensionTest3 extends App {

  def even(s: Int): List[Int] =
    for {i <- List.range(0, s)
         if i % 2 == 0
         m <- List("a", "s,", "c").map{ p=>
           println(s)
           p}
    } yield i

  Console.println(even(20))
}
