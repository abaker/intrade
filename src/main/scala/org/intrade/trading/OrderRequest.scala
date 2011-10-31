package org.intrade.trading

import org.intrade.trading.Side._
import org.intrade.trading.TimeInForce._
import org.intrade.trading.OrderType._

trait OrderRequest {
  def conID: String

  def limitprice: BigDecimal

  def quantity: Int

  def side: Side

  def timeInForce: TimeInForce

  def orderType: OrderType
}