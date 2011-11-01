package org.intrade.trading

import org.intrade.Implicits._
import xml.Node

object Position {

  case class PositionImpl(conID: Int,
                          quantity: Int,
                          totalCost: BigDecimal,
                          trueTotalCost: BigDecimal,
                          totalIM: BigDecimal,
                          openIM: BigDecimal,
                          bidAmt: BigDecimal,
                          bidQty: Int,
                          offerAmt: BigDecimal,
                          offerQty: Int,
                          netPL: BigDecimal)
    extends Position

  def apply(node: Node) =
    PositionImpl(
      node \ "@conID",
      node \ "quantity",
      node \ "totalCost",
      node \ "trueTotalCost",
      node \ "totalIM",
      node \ "openIM",
      node \ "bidAmt",
      node \ "bidQty",
      node \ "offerAmt",
      node \ "offerQty",
      node \ "netPL")
}

trait Position {
  def conID: Int

  def quantity: Int

  def totalCost: BigDecimal

  def trueTotalCost: BigDecimal

  def totalIM: BigDecimal

  def openIM: BigDecimal

  def bidAmt: BigDecimal

  def bidQty: Int

  def offerAmt: BigDecimal

  def offerQty: Int

  def netPL: BigDecimal
}