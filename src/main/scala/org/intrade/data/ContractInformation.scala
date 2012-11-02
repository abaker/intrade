package org.intrade.data

import org.intrade.ContractState._
import org.intrade.Implicits._
import xml.Node

object ContractInformation {

  case class ContractInformationImpl(ccy: String,
                                     close: Option[BigDecimal],
                                     conID: Int,
                                     dayhi: Option[BigDecimal],
                                     daylo: Option[BigDecimal],
                                     dayvol: String,
                                     lastOpenInterest: Option[Int],
                                     lifehi: Option[BigDecimal],
                                     lifelo: Option[BigDecimal],
                                     lstTrdPrc: Option[BigDecimal],
                                     lstTrdTme: Option[Long],
                                     maxMarginPrice: BigDecimal,
                                     minMarginPrice: BigDecimal,
                                     state: ContractState,
                                     tickSize: BigDecimal,
                                     tickValue: BigDecimal,
                                     totalvol: String,
                                     _type: String,
                                     marginLinked: Option[Boolean],
                                     marginGroupId: Option[Int],
                                     symbol: String)
    extends ContractInformation

  def apply(node: Node) =
    ContractInformationImpl(
      node \ "@ccy",
      node \ "@close",
      node \ "@conID",
      node \ "@dayhi",
      node \ "@daylo",
      node \ "@dayvol",
      node \ "@lastOpenInterest",
      node \ "@lifehi",
      node \ "@lifelo",
      node \ "@lstTrdPrc",
      node \ "@lstTrdTme",
      node \ "@maxMarginPrice",
      node \ "@minMarginPrice",
      node \ "@state",
      node \ "@tickSize",
      node \ "@tickValue",
      node \ "@totalvol",
      node \ "@type",
      node \ "@marginLinked",
      node \ "@marginGroupId",
      node \ "symbol")
}

trait ContractInformation {
  def ccy: String

  def close: Option[BigDecimal]

  def conID: Int

  def dayhi: Option[BigDecimal]

  def daylo: Option[BigDecimal]

  def dayvol: String

  def lastOpenInterest: Option[Int]

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

  def marginLinked: Option[Boolean]

  def marginGroupId: Option[Int]

  def symbol: String
}