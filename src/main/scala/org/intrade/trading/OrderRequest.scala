package org.intrade.trading

import org.intrade.trading.TimeInForce._
import org.intrade.trading.OrderType._
import org.intrade.trading.Side._

trait OrderRequest {
  def conID: Int

  def side: Side

  def quantity: Int

  def limitprice: BigDecimal

  def userReference: String = ""

  def timeInForce: TimeInForce = Good_Til_Cancel

  def timeToExpire: Long = 0

  def orderType: OrderType = Limit

  def touchPrice: BigDecimal = 0
}