package org.intrade.trading

import xml.Node
import org.intrade.Implicits._

object Response {

  case class ResponseImpl[A](timetaken: Int,
                             timestamp: Long,
                             resultCode: Int,
                             requestOp: String,
                             errorcode: Option[Int],
                             faildesc: String,
                             sessionData: String,
                             request: Node,
                             response: Node,
                             payload: Option[A])
    extends Response[A]

  def node2Response[A](req: Node, node: Node, f: Node => A) = {
    val resultCode: Int = node \ "@resultCode"
    ResponseImpl(
      node \ "@timetaken",
      node \ "@timestamp",
      resultCode,
      node \ "@requestOp",
      node \ "errorcode",
      node \ "faildesc",
      node \ "sessionData",
      req,
      node,
      resultCode match {
        case 0 => Option(f(node))
        case -1 => Option.empty[A]
      })
  }
}

trait Response[+A] {
  def timetaken: Int

  def timestamp: Long

  def resultCode: Int

  def requestOp: String

  def errorcode: Option[Int]

  def faildesc: String

  def sessionData: String

  def payload: Option[A]

  def request: Node

  def response: Node
}