package org.intrade.trading

import org.intrade.Implicits._
import xml.{XML, Node}

object Response {

  case class ResponseImpl[A](timetaken: Option[Int],
                             timestamp: Long,
                             resultCode: Int,
                             requestOp: String,
                             errorcode: Option[Int],
                             faildesc: String,
                             sessionData: String,
                             request: String,
                             response: String,
                             payload: A)
    extends Response[A]

  def apply[A](request: String, response: String, f: Node => A) = {
    val node = XML.loadString(response)
    ResponseImpl(
      node \ "@timetaken",
      node \ "@timestamp",
      node \ "@resultCode",
      node \ "@requestOp",
      node \ "errorcode",
      node \ "faildesc",
      node \ "sessionData",
      request,
      response,
      f(node))
  }
}

trait Response[+A] extends org.intrade.Response[A] {
  def timetaken: Option[Int]

  def timestamp: Long

  def resultCode: Int

  def requestOp: String

  def errorcode: Option[Int]

  def faildesc: String

  def sessionData: String
}