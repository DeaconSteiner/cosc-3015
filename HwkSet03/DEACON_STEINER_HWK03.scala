import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.CountDownLatch

@main def ThreadTiming(): Unit =
  def sumIt(a: Int, b: Int, cd: CountDownLatch, parity: Int = 0): Int =
    if a < 0 || b < 0 || a > b then throw new IllegalArgumentException("Endpoints cannot be negative and a must be <= b")
    val result = parity match
      case 0 => (a to b).toList.sum
      case 1 => (a to b).toList.filter(_ % 2 == 0).sum // even case
      case 2 => (a to b).toList.filter(_ % 2 != 0).sum // odd case
      case _ => throw new IllegalArgumentException("Invalid parity")
    cd.countDown()
    result

  val tallyman = new CountDownLatch(3)
  val al=0
  val bl=18200

  println("---Sequential---")
  val t0 = System.nanoTime()
  println("The sum of "+al.toString+" to "+bl.toString+" is "+sumIt(al,bl,tallyman).toString)
  val t1 = System.nanoTime()

  println(s"Elapsed time: ${(t1 - t0) / 1000000.0} ms") // The resolution is roughly nanoseconds.

  println("---Threaded---")

  var evensum=0
  var oddsum=0

  val t2 = System.nanoTime()
  Future{evensum = sumIt(al,bl,tallyman, 1)}
  Future{oddsum = sumIt(al,bl,tallyman, 2)}
  val t3 = System.nanoTime()

  tallyman.await()
  println("The sum of "+al.toString()+" to "+bl.toString()+" is "+(evensum+oddsum).toString())

  println(s"Elapsed time Threaded: ${(t3 - t2) / 1000000.0} ms") // The resolution is roughly nanoseconds.
