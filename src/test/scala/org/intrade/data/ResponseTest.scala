package org.intrade.data

import org.scalatest.FunSuite

class ResponseTest extends FunSuite {
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
      response.values
    }
  }
}