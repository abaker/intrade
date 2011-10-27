package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object Response {
  def node2EventClassResponse(req: String, node: Node) = new Response[Node, EventClass] {
    val timestamp: Option[Long] = node \ "@intrade.timestamp"
    val request = req
    val response = node
    val values = node \ "EventClass" map EventClass.apply
  }

  def node2ContractInformationResponse(req: String, node: Node) = new Response[Node, ContractInformation] {
    val timestamp: Option[Long] = Option.empty
    val request = req
    val response = node
    val values = node \ "contract" map ContractInformation.apply
  }

  def node2PriceInformationResponse(req: String, node: Node) = new Response[Node, PriceInformation] {
    val timestamp: Option[Long] = node \ "@lastUpdateTime"
    val request = req
    val response = node
    val values = node \ "contractInfo" map PriceInformation.apply
  }

  def node2ClosingPriceResponse(req: String, node: Node) = new Response[Node, ClosingPrice] {
    val timestamp: Option[Long] = node \ "@timestamp"
    val request = req
    val response = node
    val values = node \ "cp" map ClosingPrice.apply
  }

  def string2TradeResponse(req: String, resp: String) = new Response[String, Trade] {
    val timestamp: Option[Long] = Option.empty
    val request = req
    val response = resp
    val values = resp.lines.toSeq map Trade.apply
  }
}

trait Response[A, B] {
  def timestamp: Option[Long]

  def request: String

  def response: A

  def values: Seq[B]
}