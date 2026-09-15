import scala.annotation.tailrec

@main def main(): Unit =
  println(radixConvert(53, 3)) // Should be "1222"
  println(radixConvert(53, 4)) // Should be "311"
  println(radixConvert(53, 10)) // Should be "53"
  println(radixConvert(-53, 2)) // Should be "Error"
  println(radixConvert(53, 16)) // Should be "Error"
  println(radixConvert(0, 2)) // Should be "0"

// Bases between 2 and 10, non-negative value
def radixConvert(value: Int, base: Int): String =
  if value < 0 || base < 2 || base > 10 then
    "Error"
  else if value == 0 then
    "0"
  else
    convert(value, base, "")

@tailrec
def convert(value: Int, base: Int, acc: String): String =
  value match
    case 0 => acc
    case _ => convert(value/base, base, (value%base).toString() + acc)
