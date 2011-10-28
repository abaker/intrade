package org.intrade.data

import org.intrade.Implicits._
import org.intrade.ContractState._
import xml.Node

object Contract {

  case class ContractImpl(ccy: String,
                          id: Int,
                          inRunning: Boolean,
                          state: ContractState,
                          tickSize: BigDecimal,
                          tickValue: BigDecimal,
                          _type: String,
                          name: String,
                          symbol: String,
                          totalVolume: String)
    extends Contract

  def apply(node: Node) =
    ContractImpl(
      node \ "@ccy",
      node \ "@id",
      node \ "@inRunning",
      node \ "@state",
      node \ "@tickSize",
      node \ "@tickValue",
      node \ "@type",
      node \ "name",
      node \ "symbol",
      node \ "totalVolume")
}

trait Contract {
  def ccy: String

  def id: Int

  def inRunning: Boolean

  def state: ContractState

  def tickSize: BigDecimal

  def tickValue: BigDecimal

  def _type: String

  def name: String

  def symbol: String

  def totalVolume: String
}