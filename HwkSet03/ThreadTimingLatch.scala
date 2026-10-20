import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.CountDownLatch

@main def ThreadTiming(): Unit = {
 def methodSumIt(a: Int, b: Int, cd: CountDownLatch):  Int = {
  val myList = (a to b).toList
  val thesum=myList.sum
  cd.countDown()
  return thesum
}


  val tallyman = new CountDownLatch(3)
  val al=0
  val bl=18200

  val t0 = System.nanoTime()
  println("The sum of "+al.toString+" to "+bl.toString+" is "+methodSumIt(al,bl,tallyman).toString)
  val t1 = System.nanoTime()

  println(s"Elapsed time: ${(t1 - t0) / 1000000.0} ms") // The resolution is roughly nanoseconds.
  var lowersum=0
  var uppersum=0

  val t2 = System.nanoTime()
  Future{ lowersum = methodSumIt(al,bl/2,tallyman) }
  Future{ uppersum = methodSumIt((bl/2+1),bl,tallyman) }
  tallyman.await()
  val t3 = System.nanoTime()
  println("The sum of "+al.toString()+" to "+bl.toString()+" is "+(lowersum+uppersum).toString())
  println(s"Elapsed time Threaded: ${(t3 - t2) / 1000000.0} ms") // The resolution is roughly nanoseconds.
}
