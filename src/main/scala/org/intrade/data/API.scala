package org.intrade.data

import io.Source.fromInputStream
import org.intrade.Environment._
import org.intrade.data.Response._
import java.net.URL
import xml.{XML, Node}

object API {
  def apply(env: Environment) = new API {
    private val urls = new URLProvider(env)

    def activeContractListing(optionalEventClass: Int = 0) =
      process(urls.activeContractListing(optionalEventClass), xmlRequest, node2EventClassResponse)

    def priceInformation(contractIds: Seq[Int], timestamp: Long = 0, depth: Int = 5) =
      process(urls.priceInformation(contractIds, timestamp, depth), xmlRequest, node2PriceInformationResponse)

    def contractInformation(contractIds: Seq[Int]) =
      process(urls.contractInformation(contractIds), xmlRequest, node2ContractInformationResponse)

    def closingPrices(contractId: Int) =
      process(urls.closingPrices(contractId), xmlRequest, node2ClosingPriceResponse)

    def dailyTimeAndSales(contractId: Int) =
      process(urls.dailyTimeAndSales(contractId), stringRequest, string2TradeResponse)
  }

  private def process[A, B](request: String, getResponse: String => A, toResult: (String, A) => Response[A, B]) = {
    toResult(request, getResponse(request))
  }

  private def stringRequest(url: String) = {
    val stream = new URL(url).openStream
    val source = fromInputStream(stream)
    source.getLines().mkString
  }

  private def xmlRequest(url: String) = XML.loadString(stringRequest(url))
}

trait API {
  def activeContractListing(optionalEventClass: Int = 0): Response[Node, EventClass]

  def priceInformation(contractIds: Seq[Int], timestamp: Long = 0, depth: Int = 5): Response[Node, PriceInformation]

  def contractInformation(contractIds: Seq[Int]): Response[Node, ContractInformation]

  def closingPrices(contractId: Int): Response[Node, ClosingPrice]

  def dailyTimeAndSales(contractId: Int): Response[String, Trade]
}