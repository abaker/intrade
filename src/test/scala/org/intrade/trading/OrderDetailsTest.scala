package org.intrade.trading

import org.scalatest.FunSuite

class OrderDetailsTest extends FunSuite {
  test("parse order details") {
    val node =
      <order orderID="646444453">
        <conID>741970</conID>
        <side>Sell</side>
        <quantity>1</quantity>
        <orig_quantity>1</orig_quantity>
        <limitprice>2.4</limitprice>
        <timeInForce>0</timeInForce>
        <numFills>0</numFills>
        <status>CEN</status>
      </order>

    val ord = OrderDetails(node)

    expect(646444453) {
      ord.orderID
    }
    expect(741970) {
      ord.conID
    }
    expect(Side.Sell) {
      ord.side
    }
    expect(1) {
      ord.quantity
    }
    expect(1) {
      ord.originalQuantity
    }
    expect(BigDecimal(2.4)) {
      ord.limitprice
    }
    expect(0) {
      ord.timeInForce
    }
    expect(0) {
      ord.numFills
    }
    expect(OrderStatus.New) {
      ord.status
    }
  }
}