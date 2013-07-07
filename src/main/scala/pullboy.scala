package pullboy

object Pullboy {
  def apply(args: Array[String]): Int =
    Config().given(args).fold({ e =>
      println("invalid args: %s".format(e))
      1
    }, { c =>
      Actions(c.action).map(_(c))
        .getOrElse {
          println("invalid action %s" format c.action)
          0
        }
    })
}
