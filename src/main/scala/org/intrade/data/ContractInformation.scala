package org.intrade.data

import org.intrade.Implicits._
import org.intrade.ContractState._
import xml.Node

object ContractInformation {
  def apply(xml: Node) = new ContractInformation {
    val ccy: String = xml.attribute("ccy")
    val close: Option[BigDecimal] = xml.attribute("close")
    val conID: String = xml.attribute("conID")
    val dayhi: Option[BigDecimal] = xml.attribute("dayhi")
    val daylo: Option[BigDecimal] = xml.attribute("daylo")
    val dayvol: String = xml.attribute("dayvol")
    val lifehi: Option[BigDecimal] = xml.attribute("lifehi")
    val lifelo: Option[BigDecimal] = xml.attribute("lifelo")
    val lstTrdPrc: Option[BigDecimal] = xml.attribute("lstTrdPrc")
    val lstTrdTme: Option[Long] = xml.attribute("lstTrdTme")
    val maxMarginPrice: BigDecimal = xml.attribute("maxMarginPrice")
    val minMarginPrice: BigDecimal = xml.attribute("minMarginPrice")
    val state: ContractState = xml.attribute("state")
    val tickSize: BigDecimal = xml.attribute("tickSize")
    val tickValue: BigDecimal = xml.attribute("tickValue")
    val totalvol: String = xml.attribute("totalvol")
    val _type: String = xml.attribute("type")
    val symbol: String = xml \ "symbol"
  }
}

trait ContractInformation {
  def ccy: String

  def close: Option[BigDecimal]

  def conID: String

  def dayhi: Option[BigDecimal]

  def daylo: Option[BigDecimal]

  def dayvol: String

  def lifehi: Option[BigDecimal]

  def lifelo: Option[BigDecimal]

  def lstTrdPrc: Option[BigDecimal]

  def lstTrdTme: Option[Long]

  def maxMarginPrice: BigDecimal

  def minMarginPrice: BigDecimal

  def state: ContractState

  def tickSize: BigDecimal

  def tickValue: BigDecimal

  def totalvol: String

  def _type: String

  def symbol: String
}