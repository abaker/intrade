package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object ClosingPrice {
  def apply(node: Node) = new ClosingPrice {
    val date: String = node.attribute("date")
    val dt: Long = node.attribute("dt")
    val price: BigDecimal = node.attribute("price")
    val sessionHi: Option[BigDecimal] = node.attribute("sessionHi")
    val sessionLo: Option[BigDecimal] = node.attribute("sessionLo")
  }
}

trait ClosingPrice {
  def date: String

  def dt: Long

  def price: BigDecimal

  def sessionHi: Option[BigDecimal]

  def sessionLo: Option[BigDecimal]
}