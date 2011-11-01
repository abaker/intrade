package org.intrade.trading

import org.scalatest.FunSuite

class OrderTest extends FunSuite {
  test("should parse gtc order") {
    val node =
      <order orderID="647977055">
        <conID>743474</conID>
        <limitprice>99.9</limitprice>
        <type>Limit</type>
        <side>S</side>
        <quantity>5</quantity>
        <originalQuantity>5</originalQuantity>
        <timeInForce>GTC</timeInForce>
        <visibleTime>1320180736550</visibleTime>
      </order>

    val order = Order(node)

    expect(647977055) {
      order.orderID
    }
    expect(743474) {
      order.conID
    }
    expect(BigDecimal(99.9)) {
      order.limitprice
    }
    expect(OrderType.Limit) {
      order.orderType
    }
    expect(Side.Sell) {
      order.side
    }
    expect(5) {
      order.quantity
    }
    expect(5) {
      order.originalQuantity
    }
    expect(TimeInForce.Good_Til_Cancel) {
      order.timeInForce
    }
    expect(1320180736550L) {
      order.visibleTime
    }
  }
}