package org.intrade.trading

import xml.NodeSeq

object OrderType extends Enumeration {
  type OrderType = Value
  val Limit, Touch, Fill_Or_Kill, Stop = Value

  implicit def nodeSeq2OrderType(node: NodeSeq) = node.text match {
    case "Limit" => Limit
    case "Touch" => Touch
    case "FillorKill" => Fill_Or_Kill
    case "Stop" => Stop
  }
}