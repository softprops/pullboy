package pullboy

object Actions {
  val actions: Map[String, Action] = Map(
    "ls" -> { c =>
      println("listing")
      1
    },
    "auth" -> { c =>
      println("authenticating")
      0
    }
  )
  def apply(name: String) =
    actions.get(name)    
}
