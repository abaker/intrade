package org.intrade.samples

import org.intrade.data.EventClass

class ContractCollection(val eventClasses: Seq[EventClass]) {
  val eventGroups = eventClasses.flatMap(_.eventGroups)
  val events = eventGroups.flatMap(_.events)
  val contracts = events.flatMap(_.contracts)

  def getEvent(eventID: Int) = events.find(_.id == eventID).get

  def getContractsForEvent(eventID: Int) = getEvent(eventID).contracts
}
