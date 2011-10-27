package org.intrade.data

import xml.Node
import org.intrade.ContractState._
import org.intrade.Implicits._

object PriceInformation {
  def apply(node: Node) = new PriceInformation {
    val vol: String = node \ "@vol"
    val state: ContractState = node \ "@state"
    val lstTrdTme: Option[Long] = node \ "@lstTrdTme"
    val lstTrdPrc: Option[BigDecimal] = node \ "@lstTrdPrc"
    val conID: String = node \ "@conID"
    val close: Option[BigDecimal] = node \ "@close"
    val symbol: String = node \ "symbol"
    val bids = node \ "orderBook" \ "bids" \ "bid" map node2BookLevel
    val offers = node \ "orderBook" \ "offers" \ "offer" map node2BookLevel
  }

  private def node2BookLevel(node: Node) = BookLevel(node \ "@price", node \ "@quantity")
}

trait PriceInformation {
  def vol: String

  def state: ContractState

  def lstTrdTme: Option[Long]

  def lstTrdPrc: Option[BigDecimal]

  def conID: String

  def close: Option[BigDecimal]

  def symbol: String

  def bids: Seq[BookLevel]

  def offers: Seq[BookLevel]
}