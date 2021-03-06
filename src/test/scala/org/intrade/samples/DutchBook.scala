package org.intrade.samples

import SampleUtils._
import org.intrade.Implicits.string2JavaInt
import org.intrade.trading.{Side, OrderRequest}
import org.intrade.data.PriceInformation

object DutchBook extends App {
  override def main(args: Array[String]) {
    val tradingAPI = getTradingAPI("DutchBookSample")
    val eventID = prompt("enter cross margined event id: ")
    val contractIDs = getContractIDsForEvent(eventID)
    val markets = getPricesForContracts(contractIDs)
    val marketsWithBids = markets.filter(_.bids.nonEmpty)
    val price = sumOfBidPrices(marketsWithBids)
    if (price > 100) {
      val orders = marketsWithBids.map(createSellOrder)
      tradingAPI.multiOrderRequest(orders)
      println("Submitted %s orders, sum of bid prices was %s" format (marketsWithBids.size, price))
    } else {
      println("No orders submitted, sum of bid prices was %s" format(price))
    }
  }

  def getContractIDsForEvent(eventID: Int) =
    getContractCollection
      .getContractsForEvent(eventID)
      .map(_.id)

  def getPricesForContracts(contractIDs: Seq[Int]) = getDataAPI.priceInformation(contractIDs).payload

  def sumOfBidPrices(prices: Seq[PriceInformation]) = prices.foldLeft(BigDecimal(0))(_ + _.bids(0).price)

  def createSellOrder(priceInformation: PriceInformation) = new OrderRequest {
    def conID = priceInformation.conID

    def side = Side.Sell

    def limitprice = priceInformation.bids(0).price

    def quantity = 1
  }
}