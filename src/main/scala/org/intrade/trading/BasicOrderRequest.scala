package org.intrade.trading

import org.intrade.trading.Side._

trait BasicOrderRequest {
  def conID: Int

  def side: Side

  def quantity: Int

  def limitprice: BigDecimal
}