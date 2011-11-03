package org.intrade.trading

import xml.Node
import org.intrade.Implicits._
import org.intrade.trading.Side._

object Trade {

  case class TradeImpl(conID: Int,
                       orderID: Int,
                       side: Side,
                       quantity: Int,
                       price: BigDecimal,
                       executionTime: Long)
    extends Trade

  def apply(node: Node) =
    TradeImpl(
      node \ "conID",
      node \ "orderID",
      node \ "side",
      node \ "quantity",
      node \ "price",
      node \ "executionTime")
}

trait Trade {
  def conID: Int

  def orderID: Int

  def side: Side

  def quantity: Int

  def price: BigDecimal

  def executionTime: Long
}