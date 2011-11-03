package org.intrade.trading

import org.scalatest.FunSuite

class OrderResponseTest extends FunSuite {
  test("multi order request order") {
    val node =
      <order orderID="648420490">
        <conID>743475</conID>
        <side>SELL</side>
        <quantity>1</quantity>
        <limitprice>99.9</limitprice>
        <success>true</success>
        <timeInForce>0</timeInForce>
        <timeToExpire>0</timeToExpire>
      </order>

    val response = OrderResponse(node)

    expect(648420490) {
      response.orderID
    }
    expect(743475) {
      response.conID
    }
    expect(Side.Sell) {
      response.side
    }
    expect(1) {
      response.quantity
    }
    expect(BigDecimal(99.9)) {
      response.limitprice
    }
    expect(true) {
      response.success
    }
    expect(0) {
      response.timeInForce
    }
    expect(0) {
      response.timeToExpire
    }
  }
}