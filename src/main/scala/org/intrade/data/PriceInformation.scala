package org.intrade.data

import xml.Node
import org.intrade.ContractState._
import org.intrade.Implicits._

object PriceInformation {
  def apply(xml: Node) = new PriceInformation {
    val vol: String = xml.attribute("vol")
    val state: ContractState = xml.attribute("state")
    val lstTrdTme: Option[Long] = xml.attribute("lstTrdTme")
    val lstTrdPrc: Option[BigDecimal] = xml.attribute("lstTrdPrc")
    val conID: String = xml.attribute("conID")
    val close: Option[BigDecimal] = xml.attribute("close")
    val symbol: String = xml \ "symbol"
    val bids = xml \ "orderBook" \ "bids" \ "bid" map node2BookLevel
    val offers = xml \ "orderBook" \ "offers" \ "offer" map node2BookLevel
  }

  private def node2BookLevel(node: Node) = BookLevel(node.attribute("price"), node.attribute("quantity"))
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