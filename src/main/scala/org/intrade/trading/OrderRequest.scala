package org.intrade.trading

import org.intrade.trading.Side._

trait OrderRequest {
  def conID: String

  def limitprice: BigDecimal

  def quantity: Int

  def side: Side
}