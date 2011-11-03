package org.intrade.trading

import xml.Node
import org.intrade.trading.Side._
import org.intrade.Implicits._

object OrderResponse {

  case class OrderResponseImpl(orderID: Int,
                               conID: Int,
                               side: Side,
                               quantity: Int,
                               limitprice: BigDecimal,
                               success: Boolean,
                               timeInForce: Int,
                               timeToExpire: Long)
    extends OrderResponse

  def apply(node: Node) =
    OrderResponseImpl(
      node \ "@orderID",
      node \ "conID",
      node \ "side",
      node \ "quantity",
      node \ "limitprice",
      node \ "success",
      node \ "timeInForce",
      node \ "timeToExpire")
}

trait OrderResponse {
  def orderID: Int

  def conID: Int

  def side: Side

  def quantity: Int

  def limitprice: BigDecimal

  def success: Boolean

  def timeInForce: Int

  def timeToExpire: Long
}