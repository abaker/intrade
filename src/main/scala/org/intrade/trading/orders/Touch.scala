package org.intrade.trading.orders

trait Touch extends OrderRequest {
  def touchPrice: BigDecimal
}