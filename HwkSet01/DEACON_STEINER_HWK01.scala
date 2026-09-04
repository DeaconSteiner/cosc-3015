def methodSum(a: Int, b: Int): Int =
  ((b * (b+1)) / 2) - (((a-1) * a) / 2)

@main def hello(): Unit = {
  val al = 21
  val bl = 137

  println("The sum of all integers from "+al.toString+" to "+bl.toString+" is "+methodSum(al, bl).toString)
}
