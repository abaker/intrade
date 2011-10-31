package org.intrade.trading

import xml.NodeSeq

object UserMessageType extends Enumeration {
  type UserMessageType = Value
  val Canceled_By_Exchange, Contract_Expiry, Rejected_Cancel_Request, Message,
  Rejected_Order, Contract_Scratched, Execution, Stop_Activated, Expired_Order = Value

  def parse(messageType: String) = messageType match {
    case "D" => Canceled_By_Exchange
    case "E" => Contract_Expiry
    case "J" => Rejected_Cancel_Request
    case "M" => Message
    case "R" => Rejected_Order
    case "S" => Contract_Scratched
    case "T" => Execution
    case "V" => Stop_Activated
    case "X" => Expired_Order
  }

  implicit def nodeSeq2UserMessageType(node: NodeSeq) = parse(node.text)
}