package org.intrade.data

import xml.Node

trait API {
  def activeContractListing(optionalEventClass: Int = 0): Response[Node, EventClass]

  def priceInformation(contractIds: Seq[String], timestamp: Long = 0, depth: Long = 0): Response[Node, PriceInformation]

  def contractInformation(contractIds: Seq[String]): Response[Node, ContractInformation]

  def closingPrices(contractId: String): Response[Node, ClosingPrice]

  def dailyTimeAndSales(contractId: String): Response[String, Trade]
}