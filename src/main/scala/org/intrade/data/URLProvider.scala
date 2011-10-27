package org.intrade.data

import org.intrade.Environment._

import URLProvider._

object URLProvider {
  def root(env: Environment) = "http://%s.intrade.com/jsp/XML" format (env match {
    case Live => "api"
    case Test => "testexternal"
  })
}

class URLProvider(env: Environment) {
  private val allContracts = "%s/MarketData/xml.jsp" format (root(env))
  private val allContractsByEventClass = "%s/MarketData/XMLForClass.jsp?classID=%s" format(root(env), "%s")
  private val price = "%s/MarketData/ContractBookXML.jsp?id=%s&timestamp=%s" format(root(env), "%s", "%s")
  private val priceWithDepth = "%s&depth=%s" format(price, "%s")
  private val conInfo = "%s/MarketData/ConInfo.jsp?id=%s" format(root(env), "%s")
  private val closingPrice = "%s/MarketData/ClosingPrice.jsp?conID=%s" format(root(env), "%s")
  private val trades = "%s/TradeData/TimeAndSales.jsp?conID=%s" format(root(env), "%s")

  def activeContractListing(eventClassId: Int) = eventClassId match {
    case x if x > 0 => allContractsByEventClass format (x)
    case _ => allContracts
  }

  def priceInformation(contractIds: Seq[String], timestamp: Long, depth: Int) = depth match {
    case 5 => price format(contractIds.mkString("&id="), timestamp)
    case _ => priceWithDepth format(contractIds.mkString("&id="), timestamp, depth)
  }

  def contractInformation(contractIds: Seq[String]) = conInfo format (contractIds.mkString("&id="))

  def closingPrices(contractId: String) = closingPrice format (contractId)

  def dailyTimeAndSales(contractId: String) = trades format (contractId)
}