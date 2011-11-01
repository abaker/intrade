package org.intrade.trading

import xml.NodeSeq

object Side extends Enumeration {
  type Side = Value
  val Buy, Sell = Value

  implicit def nodeSeq2String(node: NodeSeq) = node.text match {
    case "Buy" | "BUY" | "B" => Side.Buy
    case "Sell" | "SELL" | "S" => Side.Sell
  }
}