package org.intrade.trading

import org.scalatest.FunSuite

class OrderStatusTest extends FunSuite {
  test("should parse three letter status with trailing space") {
    expect(OrderStatus.New) {
      OrderStatus.nodeSeq2OrderStatus(<node>CEN </node>)
    }
  }
}