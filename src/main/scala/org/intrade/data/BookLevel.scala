package org.intrade.data

object BookLevel {

  case class BookLevelImpl(price: BigDecimal, quantity: Int) extends BookLevel

  def apply(px: BigDecimal, qty: Int) = BookLevelImpl(px, qty)
}

trait BookLevel {
  def price: BigDecimal

  def quantity: Int
}