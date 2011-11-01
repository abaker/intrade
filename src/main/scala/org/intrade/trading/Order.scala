package org.intrade.trading

import org.intrade.Implicits._
import org.intrade.trading.OrderType._
import org.intrade.trading.Side._
import org.intrade.trading.TimeInForce._
import xml.Node

object Order {

  case class OrderImpl(orderID: Int,
                       conID: Int,
                       limitprice: BigDecimal,
                       orderType: OrderType,
                       side: Side,
                       quantity: Int,
                       originalQuantity: Int,
                       timeInForce: TimeInForce,
                       visibleTime: Long)
    extends Order

  def apply(node: Node) =
    OrderImpl(
      node \ "@orderID",
      node \ "conID",
      node \ "limitprice",
      node \ "type",
      node \ "side",
      node \ "quantity",
      node \ "originalQuantity",
      node \ "timeInForce",
      node \ "visibleTime")
}

trait Order {
  def orderID: Int

  def conID: Int

  def limitprice: BigDecimal

  def orderType: OrderType

  def side: Side

  def quantity: Int

  def originalQuantity: Int

  def timeInForce: TimeInForce

  def visibleTime: Long
}