package org.intrade.samples

import org.intrade._
import java.util.GregorianCalendar
import java.text.SimpleDateFormat

object DumpContractsToCsv extends App {
  override def main(args: Array[String]) {
    val api = data.API(Environment.Test)

    val contractListingResponse = api.activeContractListing
    val eventClasses = contractListingResponse.payload

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

  def wrapInQuotes(item: Any) = "\"%s\"" format item

  def joinWithCommas(items: Any*) = items.mkString(",")

  def printDate(timestamp: Long) = {
    val calendar = new GregorianCalendar
    calendar.setTimeInMillis(timestamp)
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").format(calendar.getTime)
  }
}