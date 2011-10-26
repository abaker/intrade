package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object Response {
  def node2EventClassResponse(node: Node) = new Response[EventClass] {
    val timestamp: Option[Long] = node.attribute("intrade.timestamp")
    val response: Node = node
    val values: Seq[EventClass] = node \ "EventClass" map EventClass.apply
  }

  def node2ContractInformationResponse(node: Node) = new Response[ContractInformation] {
    val timestamp: Option[Long] = Option.empty
    val response: Node = node
    val values: Seq[ContractInformation] = node \ "contract" map ContractInformation.apply
  }

  def node2PriceInformationResponse(node: Node) = new Response[PriceInformation] {
    val timestamp: Option[Long] = node.attribute("lastUpdateTime")
    val response: Node = node
    val values: Seq[PriceInformation] = node \ "contractInfo" map PriceInformation.apply
  }
}

trait Response[A] {
  def timestamp: Option[Long]

  def response: Node

  def values: Seq[A]
}