package org.intrade.data

import org.intrade.ContractState._

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