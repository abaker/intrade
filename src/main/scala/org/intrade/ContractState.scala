package org.intrade

import xml.NodeSeq

object ContractState extends Enumeration {
  type ContractState = Value
  val Initialized, Open, Paused, Session_Closed, Closed_for_Expiry, Settled, Canceled, Reversed = Value

  def parse(state: String) = state match {
    case "I" => ContractState.Initialized
    case "O" => ContractState.Open
    case "P" => ContractState.Paused
    case "C" => ContractState.Session_Closed
    case "E" => ContractState.Closed_for_Expiry
    case "S" => ContractState.Settled
    case "X" => ContractState.Canceled
    case "R" => ContractState.Reversed
  }

  implicit def nodeSeq2ContractState(node: NodeSeq) = parse(node.text)
}