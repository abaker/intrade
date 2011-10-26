package org.intrade

import xml.{NodeSeq, Node}

object ContractState extends Enumeration {
  type ContractState = Value
  val Initialized, Open, Paused, Session_Closed, Closed_for_Expiry, Settled, Canceled, Reversed = Value

  def parse(state: String): ContractState = state match {
    case "I" => ContractState.Initialized
    case "O" => ContractState.Open
    case "P" => ContractState.Paused
    case "C" => ContractState.Session_Closed
    case "E" => ContractState.Closed_for_Expiry
    case "S" => ContractState.Settled
    case "X" => ContractState.Canceled
    case "R" => ContractState.Reversed
    case x => throw new RuntimeException("Unknown Contract State: %s" format (x))
  }

  implicit def nodeSeq2ContractState(node: NodeSeq): ContractState = parse(node.text)

  implicit def attribute2ContractState(node: Option[Seq[Node]]): ContractState = parse(node.get.text)
}