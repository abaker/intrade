package org.intrade.trading

import xml.NodeSeq

object Side extends Enumeration {
  type Side = Value
  val Buy, Sell = Value

  def parse(side: String): Side = side match {
    case "BUY" | "B" => Side.Buy
    case "SELL" | "S" => Side.Sell
    case x => throw new RuntimeException("Unknown side: %s" format x)
  }

  implicit def nodeSeq2String(node: NodeSeq): Side = parse(node.text)
}