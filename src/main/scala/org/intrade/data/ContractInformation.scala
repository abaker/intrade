package org.intrade.data

import org.intrade.Implicits._
import org.intrade.ContractState._
import xml.Node

object ContractInformation {
  def apply(node: Node) = new ContractInformation {
    val ccy: String = node \ "@ccy"
    val close: Option[BigDecimal] = node \ "@close"
    val conID: String = node \ "@conID"
    val dayhi: Option[BigDecimal] = node \ "@dayhi"
    val daylo: Option[BigDecimal] = node \ "@daylo"
    val dayvol: String = node \ "@dayvol"
    val lifehi: Option[BigDecimal] = node \ "@lifehi"
    val lifelo: Option[BigDecimal] = node \ "@lifelo"
    val lstTrdPrc: Option[BigDecimal] = node \ "@lstTrdPrc"
    val lstTrdTme: Option[Long] = node \ "@lstTrdTme"
    val maxMarginPrice: BigDecimal = node \ "@maxMarginPrice"
    val minMarginPrice: BigDecimal = node \ "@minMarginPrice"
    val state: ContractState = node \ "@state"
    val tickSize: BigDecimal = node \ "@tickSize"
    val tickValue: BigDecimal = node \ "@tickValue"
    val totalvol: String = node \ "@totalvol"
    val _type: String = node \ "@type"
    val symbol: String = node \ "symbol"
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