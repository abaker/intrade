package org.intrade.trading.orders

trait GoodTilTime extends OrderRequest {
  def timeToExpire: Long
}