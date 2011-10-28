package org.intrade.trading

import xml.Node
import org.intrade.Implicits._

object Response {

  case class ResponseImpl[A](timetaken: Int,
                             timestamp: Long,
                             resultCode: Int,
                             requestOp: String,
                             faildesc: String,
                             sessionData: String,
                             request: Node,
                             response: Node,
                             payload: A)
    extends Response[A]

  def node2Response[A](req: Node, node: Node, f: Node => A) =
    ResponseImpl(
      node \ "@timetaken",
      node \ "@timestamp",
      node \ "@resultCode",
      node \ "@requestOp",
      node \ "faildesc",
      node \ "sessionData",
      req,
      node,
      f(node))
}

trait Response[+A] {
  def timetaken: Int

  def timestamp: Long

  def resultCode: Int

  def requestOp: String

  def faildesc: String

  def sessionData: String

  def payload: A

  def request: Node

  def response: Node
}