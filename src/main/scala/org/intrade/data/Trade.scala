package org.intrade.data

import org.intrade.Implicits._

object Trade {

  case class TradeImpl(utcTimestamp: Long,
                       bstTimestamp: String,
                       price: BigDecimal,
                       volume: Int)
    extends Trade

  def apply(csv: String) = {
    val split = csv replaceAll("\t", "") split (',')
    TradeImpl(split(0), split(1), split(2), split(3))
  }
}

trait Trade {
  def utcTimestamp: Long

  def bstTimestamp: String

  def price: BigDecimal

  def volume: Int
}