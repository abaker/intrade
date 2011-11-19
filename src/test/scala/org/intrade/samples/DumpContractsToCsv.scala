package org.intrade.samples

import org.intrade._
import org.intrade.samples.CsvHelpers._

object DumpContractsToCsv extends App {
  override def main(args: Array[String]) {
    // instantiate data api
    val api = data.API(Environment.Test)
    // use contract cache to fetch and store contract listing
    val contractCache = new ContractCache(api)
    val contractResponse = contractCache.loadEventClasses
    // the response payload contains the sequence of event classes
    val eventClasses = contractResponse.payload

    println(
      joinWithCommas(
        "Class Id",
        "Class Name",
        "Group Id",
        "Group Name",
        "Event Id",
        "Event Name",
        "Start Date",
        "End Date",
        "Contract Id",
        "Contract Name",
        "Symbol",
        "State",
        "Currency",
        "In Running",
        "Tick Size",
        "Tick Value",
        "Type",
        "Total Volume"))

    for (eventClass <- eventClasses) {
      for (eventGroup <- eventClass.eventGroups) {
        for (event <- eventGroup.events) {
          for (contract <- event.contracts) {
            println(
              joinWithCommas(
                eventClass.id,
                wrapInQuotes(eventClass.name),
                eventGroup.id,
                wrapInQuotes(eventGroup.name),
                event.id,
                wrapInQuotes(event.name),
                printDate(event.startDate),
                printDate(event.endDate),
                contract.id,
                wrapInQuotes(contract.name),
                contract.symbol,
                contract.state,
                contract.ccy,
                contract.inRunning,
                contract.tickSize,
                contract.tickValue,
                contract._type,
                contract.totalVolume))
          }
        }
      }
    }
  }

  def wrapInQuotes(item: Any) = "\"%s\"" format (item)
}