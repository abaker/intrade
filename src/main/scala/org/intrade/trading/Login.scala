package org.intrade.trading

import xml.Node
import org.intrade.Implicits._

object Login {

  case class LoginImpl(exchange: String,
                       username: String)
    extends Login

  def apply(node: Node) =
    LoginImpl(
      node \ "@exchange",
      node \ "username")
}

trait Login {
  def exchange: String

  def username: String
}