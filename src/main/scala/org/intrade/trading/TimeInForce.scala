package org.intrade.trading

import xml.NodeSeq

object TimeInForce extends Enumeration {
  type TimeInForce = Value
  val Good_Til_Cancel, Good_For_Session, Good_Til_Time = Value

  implicit def nodeSeq2TimeInForce(node: NodeSeq) = node.text match {
    case "GTC" => Good_Til_Cancel
    case "GFS" => Good_For_Session
    case "GTT" => Good_Til_Time
  }
}