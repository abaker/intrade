package org.intrade.trading

import org.intrade.trading.TimeInForce._
import org.intrade.trading.OrderType._

trait AdvancedOrderRequest extends BasicOrderRequest {
  def userReference: String = ""

  def timeInForce: TimeInForce = Good_Til_Cancel

  def timeToExpire: Long = 0

  def orderType: OrderType = Limit

  def touchPrice: BigDecimal = 0
}