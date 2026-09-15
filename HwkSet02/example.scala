@main def radixPlayEvil(): Unit = {
  println("Playing with the radix of whole numbers in Scala")
  
  val a = 53
  println("a is "+a.toString)
  println("a is decimal:"+a.toString+" a is binary:"+a.toBinaryString+" a is hex:"+a.toHexString+" a is octal:"+a.toOctalString)
 
  println("a/8 is:"+(a/8).toString()+" then a%8 is:"+(a%8).toString())
  printf("VIA printf: %d \t %b \t %h \t %o\n",a,a,a,a)
  printf("VIA printf with toBinaryString help: %d \t %s \t %h \t %o\n",a,a.toBinaryString,a,a)
  
  println("first octal digit of a is:"+buildDigit(a,8))
  println("fullyconverted a in octal is:"+buildDigit(a/8,8)+buildDigit(a,8))

  def buildDigit(value: Int,base: Int): String = {
    return (value % base).toOctalString
  }
  var dBase=2
  println("\nStarting Loop game.")
  var divsr = a/dBase
  var remdr = a%dBase
  while(divsr > 0) {
    print(remdr.toString)
    remdr = divsr%dBase
    divsr = divsr/dBase
  }
  print(remdr.toString)
  println("\nDone with loop game.")
  
}