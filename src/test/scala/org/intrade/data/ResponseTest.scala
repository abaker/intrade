package org.intrade.data

import org.scalatest.FunSuite

class ResponseTest extends FunSuite {
  test("parse response for invalid contract information request") {
    val node = <conInfo/>

    val response = Response.node2ContractInformationResponse(node)

    expect(node) {
      response.response
    }
    expect(Option.empty) {
      response.timestamp
    }
    expect(Seq[ContractInformation]()) {
      response.values
    }
  }
}