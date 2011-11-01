package org.intrade.trading

import xml.Node
import org.intrade.Implicits._
import org.intrade.trading.Side._
import org.intrade.trading.OrderStatus._

object OrderDetails {

  case class OrderDetailsImpl(orderID: Int,
                              conID: Int,
                              side: Side,
                              quantity: Int,
                              originalQuantity: Int,
                              limitprice: BigDecimal,
                              timeInForce: Int,
                              numFills: Int,
                              status: OrderStatus)
    extends OrderDetails

  def apply(node: Node) =
    OrderDetailsImpl(
      node \ "@orderID",
      node \ "conID",
      node \ "side",
      node \ "quantity",
      node \ "orig_quantity",
      node \ "limitprice",
      node \ "timeInForce",
      node \ "numFills",
      node \ "status")
}

trait OrderDetails {
  def orderID: Int

  def conID: Int

  def side: Side

  def quantity: Int

  def originalQuantity: Int

  def limitprice: BigDecimal

  def timeInForce: Int

  def numFills: Int

  def status: OrderStatus
}