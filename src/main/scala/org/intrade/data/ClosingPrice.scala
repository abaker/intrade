package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object ClosingPrice {

  case class ClosingPriceImpl(date: String,
                              dt: Long,
                              price: BigDecimal,
                              sessionHi: Option[BigDecimal],
                              sessionLo: Option[BigDecimal])
    extends ClosingPrice

  def apply(node: Node) =
    ClosingPriceImpl(
      node \ "@date",
      node \ "@dt",
      node \ "@price",
      node \ "@sessionHi",
      node \ "@sessionLo")
}

trait ClosingPrice {
  def date: String

  def dt: Long

  def price: BigDecimal

  def sessionHi: Option[BigDecimal]

  def sessionLo: Option[BigDecimal]
}