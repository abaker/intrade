package org.intrade.data

import org.scalatest.FunSuite

class ClosingPriceTest extends FunSuite {
  test("parse closing price") {
    val elem =
        <cp date="6:07AM 05/10/11 GMT" dt="1305007669013"
            price="1.899999976158142" sessionHi="2.2" sessionLo="0.6"/>

    val closingPrice = ClosingPrice(elem)

    expect("6:07AM 05/10/11 GMT") {
      closingPrice.date
    }
    expect(1305007669013L) {
      closingPrice.dt
    }
    expect(BigDecimal(1.899999976158142)) {
      closingPrice.price
    }
    expect(Option(BigDecimal(2.2))) {
      closingPrice.sessionHi
    }
    expect(Option(BigDecimal(0.6))) {
      closingPrice.sessionLo
    }
  }

  test("handle empty hi and lo") {
    val elem =
        <cp date="6:00AM 06/27/11 GMT" dt="1309154441273"
            price="1.399999976158142" sessionHi=" " sessionLo=" "/>

    val closingPrice = ClosingPrice(elem)

    expect(Option.empty) {
      closingPrice.sessionHi
    }
    expect(Option.empty) {
      closingPrice.sessionLo
    }
  }
}