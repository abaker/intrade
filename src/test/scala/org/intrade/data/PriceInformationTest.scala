package org.intrade.data

import org.scalatest.FunSuite
import org.intrade.ContractState

class PriceInformationTest extends FunSuite {
  test("parse price information") {
    val node =
      <contractInfo vol="31411" state="O" lstTrdTme="1319562253618" lstTrdPrc="3.8" conID="749136" close="3.0">
        <symbol>2012.PRES.CAIN</symbol>
        <orderBook>
          <bids>
            <bid quantity="16" price="3.8"></bid>
          </bids>
          <offers>
            <offer quantity="70" price="3.9"></offer>
          </offers>
        </orderBook>
      </contractInfo>

    val priceInformation = PriceInformation(node)
    expect("31411") {
      priceInformation.vol
    }
    expect(ContractState.Open) {
      priceInformation.state
    }
    expect(Option(1319562253618L)) {
      priceInformation.lstTrdTme
    }
    expect(Option(BigDecimal(3.8))) {
      priceInformation.lstTrdPrc
    }
    expect(749136) {
      priceInformation.conID
    }
    expect(Option(BigDecimal(3.0))) {
      priceInformation.close
    }
    expect("2012.PRES.CAIN") {
      priceInformation.symbol
    }
    expect(Seq[BookLevel](BookLevel(BigDecimal(3.8), 16))) {
      priceInformation.bids
    }
    expect(Seq[BookLevel](BookLevel(BigDecimal(3.9), 70))) {
      priceInformation.offers
    }
  }

  test("parse contract that has not traded") {
    val node =
      <contractInfo vol="0" state="O" lstTrdTme="-" lstTrdPrc="-" conID="749306" close="-">
        <symbol>DEM.VP.2012.RENDELL</symbol>
        <orderBook>
          <bids>
            <bid quantity="1" price="0.1"></bid>
          </bids>
          <offers>
            <offer quantity="14" price="3.0"></offer>
          </offers>
        </orderBook>
      </contractInfo>

    val priceInformation = PriceInformation(node)
    expect("0") {
      priceInformation.vol
    }
    expect(Option.empty) {
      priceInformation.lstTrdTme
    }
    expect(Option.empty) {
      priceInformation.lstTrdPrc
    }
    expect(Option.empty) {
      priceInformation.close
    }
  }
}