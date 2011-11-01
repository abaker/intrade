package org.intrade.trading

import org.scalatest.FunSuite

class PositionTest extends FunSuite {
  test("should parse position") {
    val node =
      <position conID="741970">
        <quantity>106</quantity>
        <totalCost>47.29</totalCost>
        <trueTotalCost>47.29</trueTotalCost>
        <totalIM>-47.29</totalIM>
        <openIM>0.00</openIM>
        <bidAmt>0.00</bidAmt>
        <bidQty>0</bidQty>
        <offerAmt>-0.24</offerAmt>
        <offerQty>-1</offerQty>
        <netPL>14.69</netPL>
      </position>

    val position = Position(node)

    expect(741970) {
      position.conID
    }
    expect(106) {
      position.quantity
    }
    expect(BigDecimal(47.29)) {
      position.totalCost
    }
    expect(BigDecimal(47.29)) {
      position.trueTotalCost
    }
    expect(BigDecimal(-47.29)) {
      position.totalIM
    }
    expect(BigDecimal(0.00)) {
      position.openIM
    }
    expect(BigDecimal(0.00)) {
      position.bidAmt
    }
    expect(0) {
      position.bidQty
    }
    expect(BigDecimal(-0.24)) {
      position.offerAmt
    }
    expect(-1) {
      position.offerQty
    }
    expect(BigDecimal(14.69)) {
      position.netPL
    }
  }
}