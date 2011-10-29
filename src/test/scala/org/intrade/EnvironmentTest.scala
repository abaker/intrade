package org.intrade

import org.scalatest.FunSuite

class EnvironmentTest extends FunSuite {
  test("should get test data url") {
    expect("http://testexternal.intrade.com") {
      Environment.dataUrl(Environment.Test)
    }
  }

  test("should get live data url") {
    expect("http://api.intrade.com") {
      Environment.dataUrl(Environment.Live)
    }
  }

  test("should get test trading url") {
    expect("http://testexternal.intrade.com/xml/handler.jsp") {
      Environment.tradingUrl(Environment.Test)
    }
  }

  test("should get live trading url") {
    expect("https://api.intrade.com/xml/handler.jsp") {
      Environment.tradingUrl(Environment.Live)
    }
  }
}