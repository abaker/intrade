package org.intrade.trading

import org.scalatest.FunSuite

class OrderTest extends FunSuite {
  test("should parse gtc limit order") {
    val node =
      <order orderID="645440077">
        <conID>743474</conID>
        <limitprice>99.9</limitprice>
        <type>Limit</type>
        <side>S</side>
        <quantity>5</quantity>
        <originalQuantity>5</originalQuantity>
        <timeInForce>GTC</timeInForce>
        <touchprice>0.0</touchprice>
        <visibleTime>1318693421219</visibleTime>
      </order>

    val order = Order(node)

    expect(645440077) {
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
    expect(BigDecimal(0.0)) {
      order.touchprice
    }
    expect(1318693421219L) {
      order.visibleTime
    }
  }
}