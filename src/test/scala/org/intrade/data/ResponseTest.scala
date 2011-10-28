package org.intrade.data

import org.scalatest.FunSuite

class ResponseTest extends FunSuite {
  test("parse empty closing prices response") {
    val node = <ClosingPrice timestamp="1319667934816"/>

    val response = Response.node2ClosingPriceResponse("http://some_url", node)

    expect("http://some_url") {
      response.request
    }
    expect(node) {
      response.response
    }
    expect(Option(1319667934816L)) {
      response.timestamp
    }
    expect(Seq[ClosingPrice]()) {
      response.payload
    }
  }

  test("parse response for invalid contract information request") {
    val node = <conInfo/>

    val response = Response.node2ContractInformationResponse("http://some_url", node)

    expect("http://some_url") {
      response.request
    }
    expect(node) {
      response.response
    }
    expect(Option.empty) {
      response.timestamp
    }
    expect(Seq[ContractInformation]()) {
      response.payload
    }
  }

  test("parse trade response") {
    val result = "1319616022446,\t08:00:22 10/26/11 GMT,\t4.4,\t3\n1319639204180,\t14:26:44 10/26/11 GMT,\t4.4,\t2\n"

    val response = Response.string2TradeResponse("http://some_url", result)

    expect(2) {
      response.payload.size
    }
    val first = response.payload(0)
    expect(1319616022446L) {
      first.utcTimestamp
    }
    expect("08:00:22 10/26/11 GMT") {
      first.bstTimestamp
    }
    expect(BigDecimal(4.4)) {
      first.price
    }
    expect(3) {
      first.volume
    }
    val second = response.payload(1)
    expect(1319639204180L) {
      second.utcTimestamp
    }
    expect("14:26:44 10/26/11 GMT") {
      second.bstTimestamp
    }
    expect(BigDecimal(4.4)) {
      second.price
    }
    expect(2) {
      second.volume
    }
  }
}