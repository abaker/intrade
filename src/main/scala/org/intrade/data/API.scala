package org.intrade.data

import io.Source.fromInputStream
import org.intrade.Environment._
import org.intrade.data.Response._
import java.net.URL
import xml.XML

object API {
  def apply(env: Environment) = new API {
    private val urls = new URLProvider(env)

    def activeContractListing =
      process(urls.activeContractListing(), node2EventClassResponse)

    def activeContractListing(eventClass: Int) =
      process(urls.activeContractListing(eventClass), node2EventClassResponse)

    def priceInformation(contractIds: Seq[Int], timestamp: Long = 0, depth: Int = 5) =
      process(urls.priceInformation(contractIds, timestamp, depth), node2PriceInformationResponse)

    def contractInformation(contractIds: Seq[Int]) =
      process(urls.contractInformation(contractIds), node2ContractInformationResponse)

    def closingPrices(contractId: Int) =
      process(urls.closingPrices(contractId), node2ClosingPriceResponse)

    def dailyTimeAndSales(contractId: Int) =
      process(urls.dailyTimeAndSales(contractId), string2TradeResponse)
  }

  private def process[A, B](request: String, toResult: (String, String) => Response[B]) = {
    toResult(request, stringRequest(request))
  }

  private def stringRequest(url: String) = {
    val stream = new URL(url).openStream
    val source = fromInputStream(stream)
    source.getLines().mkString
  }
}

trait API {
  def activeContractListing: Response[Seq[EventClass]]

  def activeContractListing(eventClass: Int): Response[Seq[EventClass]]

  def priceInformation(contractIds: Seq[Int], timestamp: Long = 0, depth: Int = 5): Response[Seq[PriceInformation]]

  def contractInformation(contractIds: Seq[Int]): Response[Seq[ContractInformation]]

  def closingPrices(contractId: Int): Response[Seq[ClosingPrice]]

  def dailyTimeAndSales(contractId: Int): Response[Seq[Trade]]
}