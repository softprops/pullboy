package pullboy

case class Config(
  action: String = "ls",
  head: Option[String] = None,
  base: Option[String] = None,
  state: Option[String] = None,
  login: Option[String] = None,
  password: Option[String] = None,
  title: Option[String] = None) {
  def given(args: Array[String]) =
     new scopt.OptionParser[Config]("pullboy") {

       cmd("ls") action {
         (_, c) => c.copy(action = "ls")
       } text("lists pull requests") children {
         opt[String]("head") action {
           (h, c) => c.copy(head = Some(h))
         } text("Optional string - Filter pulls by head user and branch name in the format of: user:ref-name. Example: github:new-script-format.")
         opt[String]("base") action {
           (b, c) => c.copy(base = Some(b))
         } text("Optional string - Filter pulls by base branch name. Example: gh-pages.")
         opt[String]("state") action {
           (s, c) => c.copy(state = Some(s))
         } text("Optional string - open or closed to filter by state. Default is open")
       }

       cmd("auth") action {
         (_, c) => c.copy(action = "auth")
       } text("authenticate with github") children {
         opt[String]("login") action {
           (l, c) => c.copy(login = Some(l))
         } text("github login")
         opt[String]("password") action {
           (p, c) => c.copy(password = Some(p))
         } text("github password")
       }

       cmd("whoami") action {
         (_, c) => c.copy(action = "whoami")
       } text("print currently authenticated github user")

       cmd("make") action {
         (_, c) => c.copy("make")
       } text("create a new pull request") children {
         opt[String]("title") action {
           (t, c) => c.copy(title = Some(t))
         } text("title of pull request")
         opt[String]("base") action {
           (b, c) => c.copy(base = Some(b))             
         } text("Required string - The branch (or git ref) you want your changes pulled into. This should be an existing branch on the current repository. You cannot submit a pull request to one repo that requests a merge to a base of another repo.")
         opt[String]("head") action {
           (h,c) => c.copy(head = Some(h))
         } text("Required string - The branch (or git ref) where your changes are implemented.")
       }

       cmd("merge") action {
         (_, c) => c.copy(action = "merge")
       } text("merges pull request")

       cmd("merged") action {
         (_, c) => c.copy(action = "merged")
       } text("returns successful if merged")
     }
     .parse(args, this).map(Right(_))
     .getOrElse(Left("failed to parse arguments"))
}

