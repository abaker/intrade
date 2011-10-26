package org.intrade.data

import org.intrade.Implicits._
import org.intrade.ContractState._
import xml.Node

object Contract {
  def apply(node: Node) = new Contract {
    val ccy: String = node.attribute("ccy")
    val id: String = node.attribute("id")
    val inRunning: Boolean = node.attribute("inRunning")
    val state: ContractState = node.attribute("state")
    val tickSize: BigDecimal = node.attribute("tickSize")
    val tickValue: BigDecimal = node.attribute("tickValue")
    val _type: String = node.attribute("type")
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