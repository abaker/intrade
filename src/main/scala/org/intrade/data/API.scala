package org.intrade.data

trait API {
  def activeContractListing(optionalEventClass: Int = 0): Response[Contract]

  def priceInformation(contractIds: Seq[String], timestamp: Long = 0, depth: Long = 0): Response[PriceInformation]

  def contractInformation(contractIds: Seq[String]): Response[ContractInformation]

  def closingPrices(contractId: String): Response[ClosingPrice]

  def dailyTimeAndSales(contractId: String): Response[Trade]
}