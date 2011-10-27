package org.intrade.trading

import org.scalatest.FunSuite

class SideTest extends FunSuite {
  test("should parse single character sides") {
    expect(Side.Buy) {
      Side.parse("B")
    }

    expect(Side.Sell) {
      Side.parse("S")
    }
  }

  test("should parse sides") {
    expect(Side.Buy) {
      Side.parse("BUY")
    }

    expect(Side.Sell) {
      Side.parse("SELL")
    }
  }
}