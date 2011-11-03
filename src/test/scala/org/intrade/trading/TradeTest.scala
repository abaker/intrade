package org.intrade.trading

import org.scalatest.FunSuite

class TradeTest extends FunSuite {
  test("process trade") {
    val node =
      <trade conID="743474">
        <conID>743474</conID>
        <orderID>645728506</orderID>
        <side>S</side>
        <quantity>1</quantity>
        <price>49.7</price>
        <executionTime>1318948059300</executionTime>
      </trade>

    val trade = Trade(node)

    expect(743474) {
      trade.conID
    }
    expect(645728506) {
      trade.orderID
    }
    expect(Side.Sell) {
      trade.side
    }
    expect(1) {
      trade.quantity
    }
    expect(BigDecimal(49.7)) {
      trade.price
    }
    expect(1318948059300L) {
      trade.executionTime
    }
  }
}