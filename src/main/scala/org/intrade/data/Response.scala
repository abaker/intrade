package org.intrade.data

import org.intrade.Implicits._
import xml.XML

object Response {

  case class ResponseImpl[A](timestamp: Option[Long],
                             request: String,
                             raw: String,
                             payload: A)
    extends Response[A]

  def node2EventClassResponse(req: String, resp: String) = {
    val node = XML.loadString(resp)
    ResponseImpl(node \ "@intrade.timestamp", req, resp, node \ "EventClass" map EventClass.apply)
  }

  def node2ContractInformationResponse(req: String, resp: String) = {
    val node = XML.loadString(resp)
    ResponseImpl(Option.empty, req, resp, node \ "contract" map ContractInformation.apply)
  }

  def node2PriceInformationResponse(req: String, resp: String) = {
    val node = XML.loadString(resp)
    ResponseImpl(node \ "@lastUpdateTime", req, resp, node \ "contractInfo" map PriceInformation.apply)
  }

  def node2ClosingPriceResponse(req: String, resp: String) = {
    val node = XML.loadString(resp)
    ResponseImpl(node \ "@timestamp", req, resp, node \ "cp" map ClosingPrice.apply)
  }

  def string2TradeResponse(req: String, resp: String) =
    ResponseImpl(Option.empty, req, resp, resp.lines.toSeq map Trade.apply)
}

trait Response[+A] extends org.intrade.Response[A] {
  def timestamp: Option[Long]
}