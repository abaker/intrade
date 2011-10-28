package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object Response {

  case class ResponseImpl[A, B](timestamp: Option[Long],
                                request: String,
                                response: A,
                                values: Seq[B])
    extends Response[A, B]

  def node2EventClassResponse(req: String, node: Node) =
    ResponseImpl(node \ "@intrade.timestamp", req, node, node \ "EventClass" map EventClass.apply)

  def node2ContractInformationResponse(req: String, node: Node) =
    ResponseImpl(Option.empty, req, node, node \ "contract" map ContractInformation.apply)

  def node2PriceInformationResponse(req: String, node: Node) =
    ResponseImpl(node \ "@lastUpdateTime", req, node, node \ "contractInfo" map PriceInformation.apply)

  def node2ClosingPriceResponse(req: String, node: Node) =
    ResponseImpl(node \ "@timestamp", req, node, node \ "cp" map ClosingPrice.apply)

  def string2TradeResponse(req: String, resp: String) =
    ResponseImpl(Option.empty, req, resp, resp.lines.toSeq map Trade.apply)
}

trait Response[A, +B] {
  def timestamp: Option[Long]

  def request: String

  def response: A

  def values: Seq[B]
}