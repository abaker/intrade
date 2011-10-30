package org.intrade.trading

import xml.Node
import org.intrade.Implicits._

object Balance {

  case class BalanceImpl(ccy: String,
                         available: BigDecimal,
                         frozen: BigDecimal)
    extends Balance

  def apply(node: Node) =
    BalanceImpl(
      node \ "ccy",
      node \ "available",
      node \ "frozen")
}

trait Balance {
  def ccy: String

  def available: BigDecimal

  def frozen: BigDecimal
}