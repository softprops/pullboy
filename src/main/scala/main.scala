package pullboy

import hubcat._

object Main {
  def main(args: Array[String]) {
    println(Config().given(args))
  }
}
