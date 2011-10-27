package org.intrade.data

import org.intrade.Implicits._
import org.intrade.ContractState._
import xml.Node

object Contract {
  def apply(node: Node) = new Contract {
    val ccy: String = node \ "@ccy"
    val id: String = node \ "@id"
    val inRunning: Boolean = node \ "@inRunning"
    val state: ContractState = node \ "@state"
    val tickSize: BigDecimal = node \ "@tickSize"
    val tickValue: BigDecimal = node \ "@tickValue"
    val _type: String = node \ "@type"
    val name: String = node \ "name"
    val symbol: String = node \ "symbol"
    val totalVolume: String = node \ "totalVolume"
  }
}

trait Contract {
  def ccy: String

  def id: String

  def inRunning: Boolean

  def state: ContractState

  def tickSize: BigDecimal

  def tickValue: BigDecimal

  def _type: String

  def name: String

  def symbol: String

  def totalVolume: String
}