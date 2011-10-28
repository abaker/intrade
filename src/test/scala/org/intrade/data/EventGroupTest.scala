package org.intrade.data

import org.scalatest.FunSuite
import org.intrade.ContractState

class EventGroupTest extends FunSuite {
  test("should create event group") {
    val node =
      <EventClass id="78">
        <name>Business</name>
        <displayOrder>20</displayOrder>
        <EventGroup id="9679">
          <name>BP</name>
          <displayOrder>278</displayOrder>
          <Event EndDate="1420131600000" StartDate="1276732800000" groupID="9679" id="89688">
              <Description/>
            <name>BP to file for Chapter 11 bankruptcy in the United States</name>
            <displayOrder>28201</displayOrder>
            <contract ccy="USD" id="729021" inRunning="false" state="O" tickSize="0.1" tickValue="0.01" type="PX">
              <name>BP to announce before midnight ET 31 Dec 2011 it will file for Chapter 11 Bankruptcy</name>
              <symbol>BP.CHAP11.DEC2011</symbol>
              <totalVolume>2</totalVolume>
            </contract>
          </Event>
        </EventGroup>
      </EventClass>

    val eventClass = EventClass(node)
    expect(78) {
      eventClass.id
    }
    expect("Business") {
      eventClass.name
    }
    expect(20) {
      eventClass.displayOrder
    }
    expect(1) {
      eventClass.eventGroups.size
    }
    val eventGroup = eventClass.eventGroups(0)
    expect(9679) {
      eventGroup.id
    }
    expect("BP") {
      eventGroup.name
    }
    expect(278) {
      eventGroup.displayOrder
    }
    expect(1) {
      eventGroup.events.size
    }
    val event = eventGroup.events(0)
    expect(1420131600000L) {
      event.endDate
    }
    expect(1276732800000L) {
      event.startDate
    }
    expect(9679) {
      event.groupID
    }
    expect(89688) {
      event.id
    }
    expect("") {
      event.description
    }
    expect("BP to file for Chapter 11 bankruptcy in the United States") {
      event.name
    }
    expect(28201) {
      event.displayOrder
    }
    expect(1) {
      event.contracts.size
    }
    val contract = event.contracts(0)
    expect("USD") {
      contract.ccy
    }
    expect(729021) {
      contract.id
    }
    expect(false) {
      contract.inRunning
    }
    expect(ContractState.Open) {
      contract.state
    }
    expect(BigDecimal(0.1)) {
      contract.tickSize
    }
    expect(BigDecimal(0.01)) {
      contract.tickValue
    }
    expect("PX") {
      contract._type
    }
    expect("BP to announce before midnight ET 31 Dec 2011 it will file for Chapter 11 Bankruptcy") {
      contract.name
    }
    expect("BP.CHAP11.DEC2011") {
      contract.symbol
    }
    expect("2") {
      contract.totalVolume
    }
  }
}
