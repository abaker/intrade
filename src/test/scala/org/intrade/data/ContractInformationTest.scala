package org.intrade.data

import org.scalatest.FunSuite
import org.intrade.ContractState

class ContractInformationTest extends FunSuite {
  test("parse active contract") {
    val node =
      <conInfo>
        <contract ccy="USD" close="4.0" conID="749136" dayhi="4.4" daylo="4.4"
                  dayvol="3" lifehi="5.5" lifelo="0.1" lstTrdPrc="4.4"
                  lstTrdTme="1319616022446" maxMarginPrice="100.0"
                  minMarginPrice="0.0" state="O" tickSize="0.1" tickValue="0.01"
                  totalvol="31.6k" type="PX">
          <symbol>2012.PRES.CAIN</symbol>
        </contract>
      </conInfo>

    val response = ContractInformation(node)
    expect(node) {
      response.response
    }
    expect(Option.empty) {
      response.timestamp
    }
    expect(1) {
      response.values.size
    }
    val contractInfo = response.values(0)
    expect("USD") {
      contractInfo.ccy
    }
    expect(Option.empty) {
      contractInfo.close
    }
    expect("749136") {
      contractInfo.conID
    }
    expect(Option.empty) {
      contractInfo.dayhi
    }
    expect(Option.empty) {
      contractInfo.daylo
    }
    expect("3") {
      contractInfo.dayvol
    }
    expect(Option.empty) {
      contractInfo.lifehi
    }
    expect(Option.empty) {
      contractInfo.lifelo
    }
    expect(Option.empty) {
      contractInfo.lstTrdPrc
    }
    expect(Option.empty) {
      contractInfo.lstTrdTme
    }
    expect(BigDecimal(100.0)) {
      contractInfo.maxMarginPrice
    }
    expect(BigDecimal(0.0)) {
      contractInfo.minMarginPrice
    }
    expect(ContractState.Open) {
      contractInfo.state
    }
    expect(BigDecimal(0.1)) {
      contractInfo.tickSize
    }
    expect(BigDecimal(0.01)) {
      contractInfo.tickValue
    }
    expect("31.6k") {
      contractInfo.totalvol
    }
    expect("PX") {
      contractInfo._type
    }
  }

  test("parse contract that has never traded") {
    val node =
      <conInfo>
        <contract ccy="USD" close="-" conID="747269" dayhi="-" daylo="-" dayvol="0"
                  lifehi="-" lifelo="-" lstTrdPrc="-" lstTrdTme="-"
                  maxMarginPrice="100.0" minMarginPrice="0.0" state="O"
                  tickSize="0.1" tickValue="0.01" totalvol="0" type="PX">
          <symbol>PUJOLS.2012.NATIONALS</symbol>
        </contract>
      </conInfo>

    val contractInfo = ContractInformation(node).values(0)
    expect(Option.empty) {
      contractInfo.close
    }
    expect(Option.empty) {
      contractInfo.dayhi
    }
    expect(Option.empty) {
      contractInfo.daylo
    }
    expect(Option.empty) {
      contractInfo.lifehi
    }
    expect(Option.empty) {
      contractInfo.lifelo
    }
    expect(Option.empty) {
      contractInfo.lstTrdPrc
    }
    expect(Option.empty) {
      contractInfo.lstTrdTme
    }
  }

  test("parse response for invalid contract") {
    val node = <conInfo/>

    val response = ContractInformation(node)
    expect(node) {
      response.response
    }
    expect(Option.empty) {
      response.timestamp
    }
    expect(Seq[ContractInformation]()) {
      response.values
    }
  }
}