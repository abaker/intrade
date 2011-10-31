package org.intrade.trading

import xml.NodeSeq

object Side extends Enumeration {
  type Side = Value
  val Buy, Sell = Value

  def parse(side: String) = side match {
    case "BUY" | "B" => Side.Buy
    case "SELL" | "S" => Side.Sell
  }

  implicit def nodeSeq2String(node: NodeSeq) = parse(node.text)
}