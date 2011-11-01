package org.intrade.trading

import org.scalatest.FunSuite

class SideTest extends FunSuite {
  test("should parse single character sides") {
    expect(Side.Buy) {
      Side.nodeSeq2String(<node>B</node>)
    }

    expect(Side.Sell) {
      Side.nodeSeq2String(<node>S</node>)
    }
  }

  test("should parse upcase sides") {
    expect(Side.Buy) {
      Side.nodeSeq2String(<node>BUY</node>)
    }

    expect(Side.Sell) {
      Side.nodeSeq2String(<node>SELL</node>)
    }
  }

  test("should parse sides") {
    expect(Side.Buy) {
      Side.nodeSeq2String(<node>Buy</node>)
    }

    expect(Side.Sell) {
      Side.nodeSeq2String(<node>Sell</node>)
    }
  }
}