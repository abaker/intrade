package org.intrade.trading

import xml.Node
import org.intrade.trading.UserMessageType._
import org.intrade.trading.Side._
import org.intrade.Implicits._

object UserMessage {

  case class UserMessageImpl(msgID: Int,
                             conID: Int,
                             symbol: String,
                             readFlag: Boolean,
                             messageType: UserMessageType,
                             msg: Int,
                             price: BigDecimal,
                             quantity: Int,
                             side: Side,
                             timestamp: Long)
    extends UserMessage


  def apply(node: Node) =
    UserMessageImpl(
      node \ "msgID",
      node \ "conID",
      node \ "symbol",
      node \ "readFlag",
      node \ "type",
      node \ "msg",
      node \ "price",
      node \ "quantity",
      node \ "side",
      node \ "timestamp")
}

trait UserMessage {
  def msgID: Int

  def conID: Int

  def symbol: String

  def readFlag: Boolean

  def messageType: UserMessageType

  def msg: Int

  def price: BigDecimal

  def quantity: Int

  def side: Side

  def timestamp: Long
}