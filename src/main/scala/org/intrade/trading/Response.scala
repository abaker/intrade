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
                             payload: Option[A])
    extends Response[A]

  def apply[A](req: String, resp: String, f: Node => A) = {
    val node = XML.loadString(resp)
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
      resp,
      resultCode match {
        case 0 => Option(f(node))
        case -1 => Option.empty[A]
      })
  }
}

trait Response[+A] {
  def timetaken: Option[Int]

  def timestamp: Long

  def resultCode: Int

  def requestOp: String

  def errorcode: Option[Int]

  def faildesc: String

  def sessionData: String

  def payload: Option[A]

  def request: String

  def response: String
}