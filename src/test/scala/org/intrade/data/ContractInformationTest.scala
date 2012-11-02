package org.intrade.data

import org.scalatest.FunSuite
import org.intrade.ContractState

class ContractInformationTest extends FunSuite {
  test("parse active contract") {
    val node =
      <contract ccy="USD" close="66.3" conID="743474" dayhi="68.9" daylo="65.7" dayvol="113.5k"
                lastOpenInterest="1587970"
                lifehi="79.5"
                lifelo="45.1"
                lstTrdPrc="67.2"
                lstTrdTme="1351894342877"
                marginGroupId="31582"
                marginLinked="true"
                maxMarginPrice="100.0"
                minMarginPrice="0.0"
                state="O"
                tickSize="0.1"
                tickValue="0.01"
                totalvol="2.9m"
                type="PX">
        <symbol>2012.PRES.OBAMA</symbol>
      </contract>

    val contractInfo = ContractInformation(node)
    expect("USD") {
      contractInfo.ccy
    }
    expect(Option(BigDecimal(66.3))) {
      contractInfo.close
    }
    expect(743474) {
      contractInfo.conID
    }
    expect(Option(BigDecimal(68.9))) {
      contractInfo.dayhi
    }
    expect(Option(BigDecimal(65.7))) {
      contractInfo.daylo
    }
    expect("113.5k") {
      contractInfo.dayvol
    }
    expect(Some(1587970)) {
      contractInfo.lastOpenInterest
    }
    expect(Option(BigDecimal(79.5))) {
      contractInfo.lifehi
    }
    expect(Option(BigDecimal(45.1))) {
      contractInfo.lifelo
    }
    expect(Option(BigDecimal(67.2))) {
      contractInfo.lstTrdPrc
    }
    expect(Option(1351894342877L)) {
      contractInfo.lstTrdTme
    }
    expect(Some(31582)) {
      contractInfo.marginGroupId
    }
    expect(Some(true)) {
      contractInfo.marginLinked
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
    expect("2.9m") {
      contractInfo.totalvol
    }
    expect("PX") {
      contractInfo._type
    }
    expect("2012.PRES.OBAMA") {
      contractInfo.symbol
    }
  }

  test("parse contract that has never traded") {
    val node =
      <contract ccy="USD" close="-" conID="331919" dayhi="-" daylo="-" dayvol="0" lifehi="-"
                lifelo="-"
                lstTrdPrc="-"
                lstTrdTme="-"
                maxMarginPrice="100.0"
                minMarginPrice="0.0"
                state="O"
                tickSize="0.1"
                tickValue="0.01"
                totalvol="0"
                type="PX">
        <symbol>REP.ELEC.COLLEGE.400+</symbol>
      </contract>

    val contractInfo = ContractInformation(node)

    expect(Option.empty) {
      contractInfo.close
    }
    expect(Option.empty) {
      contractInfo.dayhi
    }
    expect(Option.empty) {
      contractInfo.daylo
    }
    expect("0") {
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
  }
}