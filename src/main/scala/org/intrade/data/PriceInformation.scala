package org.intrade.data

import xml.Node
import org.intrade.ContractState._
import org.intrade.Implicits._

object PriceInformation {

  case class PriceInformationImpl(vol: String,
                                  state: ContractState,
                                  lstTrdTme: Option[Long],
                                  lstTrdPrc: Option[BigDecimal],
                                  conID: Int,
                                  close: Option[BigDecimal],
                                  symbol: String,
                                  bids: Seq[BookLevel],
                                  offers: Seq[BookLevel])
    extends PriceInformation

  def apply(node: Node) =
    PriceInformationImpl(
      node \ "@vol",
      node \ "@state",
      node \ "@lstTrdTme",
      node \ "@lstTrdPrc",
      node \ "@conID",
      node \ "@close",
      node \ "symbol",
      node \ "orderBook" \ "bids" \ "bid" map node2BookLevel,
      node \ "orderBook" \ "offers" \ "offer" map node2BookLevel)

  private def node2BookLevel(node: Node) = BookLevel(node \ "@price", node \ "@quantity")
}

trait PriceInformation {
  def vol: String

  def state: ContractState

  def lstTrdTme: Option[Long]

  def lstTrdPrc: Option[BigDecimal]

  def conID: Int

  def close: Option[BigDecimal]

  def symbol: String

  def bids: Seq[BookLevel]

  def offers: Seq[BookLevel]
}