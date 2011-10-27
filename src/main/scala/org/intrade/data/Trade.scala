package org.intrade.data

import org.intrade.Implicits._

object Trade {
  def apply(csv: String) = new Trade {
    private val split = csv replaceAll("\t", "") split(',')
    val utcTimestamp: Long = split(0)
    val bstTimestamp = split(1)
    val price: BigDecimal = split(2)
    val volume: Int = split(3)
  }
}

trait Trade {
  def utcTimestamp: Long

  def bstTimestamp: String

  def price: BigDecimal

  def volume: Int
}