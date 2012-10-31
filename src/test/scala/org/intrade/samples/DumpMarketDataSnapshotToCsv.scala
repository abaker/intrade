package org.intrade.samples

import org.intrade._
import data.BookLevel
import java.util.concurrent.TimeUnit

/*
From Intrade support (11-21-11):
"There is a limit of 100 contracts per request. You should not ask for more than
50 market data updates per second."

The purpose of this sample is to demonstrate subscribing to market data updates. Intrade's policy
allows you to fetch updates at a rate of 50 per second. You may include up to 100 market ids
in a single request, however you then need to space those requests by two seconds to adhere
to the rate limit
*/

object DumpMarketDataSnapshotToCsv extends App {
  override def main(args: Array[String]) {
    // instantiate data api
    val api = data.API(Environment.Test)
    // use contract cache to fetch and store contract listing
    val contractCache = new ContractCache(api)
    val contractResponse = contractCache.loadEventClasses
    // the response payload contains the sequence of event classes
    val eventClasses = contractResponse.payload
    val eventGroups = eventClasses flatMap (_.eventGroups)
    val events = eventGroups flatMap (_.events)
    val contracts = events flatMap (_.contracts)
    // get list of contract ids from contracts
    val contractIds = contracts map (_.id)

    // split ids into groups of 100
    val groups = contractIds.grouped(100).toSeq
    val timestamps = Seq.fill(groups.size)(0L)
    printUpdates(api, groups, timestamps)
  }

  def printLevel(levels: Seq[BookLevel]) =
    if (levels.isEmpty) "" else "%6sx%4s" format(levels.head.quantity, levels.head.price)

  private def printUpdates(api: data.API, groups: Iterable[Iterable[Int]], timestamps: Iterable[Long]) {
    val responses = (groups, timestamps).zipped.map({
      Thread.sleep(TimeUnit.SECONDS.toMillis(2))
      api.priceInformation(_, _)
    })
    for (response <- responses) {

    }
    val latestTimestamps = (groups, timestamps).zipped.map((group, timestamp) => {
      val response = api.priceInformation(group, timestamp)
      val books = response.payload
      for (book <- books) {
        println("%s (%s) BID: %11s OFFER: %11s" format(book.symbol, book.conID, printLevel(book.bids), printLevel(book.offers)))
      }
      Thread.sleep(TimeUnit.SECONDS.toMillis(2))
      response.timestamp.get
    })

    printUpdates(api, groups, latestTimestamps.toSeq)
  }

  private def print[A <: {def isEmpty : Boolean}, B](item: A)(f: A => B) = item.isEmpty match {
    case true => ""
    case false => f(item)
  }
}