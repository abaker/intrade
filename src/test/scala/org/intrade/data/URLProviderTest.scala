package org.intrade.data

import org.scalatest.FunSuite
import org.intrade.Environment

class URLProviderTest extends FunSuite {
  test("should get base url") {
    expect("http://api.intrade.com/jsp/XML") {
      URLProvider.root(Environment.Live)
    }
    expect("http://testexternal.intrade.com/jsp/XML") {
      URLProvider.root(Environment.Test)
    }
  }

  test("should create request for all active contract listings") {
    expect("http://api.intrade.com/jsp/XML/MarketData/xml.jsp") {
      new URLProvider(Environment.Live).activeContractListing(0)
    }
  }

  test("should create request for active contract listings in single event class") {
    expect("http://testexternal.intrade.com/jsp/XML/MarketData/XMLForClass.jsp?classID=19") {
      new URLProvider(Environment.Test).activeContractListing(19)
    }
  }

  test("should create price information request single contract default depth") {
    expect("http://api.intrade.com/jsp/XML/MarketData/ContractBookXML.jsp?id=1234&timestamp=12345") {
      new URLProvider(Environment.Live).priceInformation(List("1234"), 12345, 5)
    }
  }

  test("should create price information request multiple contracts default depth") {
    expect("http://testexternal.intrade.com/jsp/XML/MarketData/ContractBookXML.jsp?id=1234&id=5678&timestamp=0") {
      new URLProvider(Environment.Test).priceInformation(List("1234", "5678"), 0, 5)
    }
  }

  test("should create price information request single contract specified depth") {
    expect("http://testexternal.intrade.com/jsp/XML/MarketData/ContractBookXML.jsp?id=9876&timestamp=9248492&depth=25") {
      new URLProvider(Environment.Test).priceInformation(List("9876"), 9248492, 25)
    }
  }

  test("should create price information request multiple contracts specified depth") {
    expect("http://api.intrade.com/jsp/XML/MarketData/ContractBookXML.jsp?id=9876&id=5432&timestamp=11111&depth=25") {
      new URLProvider(Environment.Live).priceInformation(List("9876", "5432"), 11111, 25)
    }
  }

  test("should create contract information request for single id") {
    expect("http://api.intrade.com/jsp/XML/MarketData/ConInfo.jsp?id=666") {
      new URLProvider(Environment.Live).contractInformation(List("666"))
    }
  }

  test("should create contract information request for multiple ids") {
    expect("http://testexternal.intrade.com/jsp/XML/MarketData/ConInfo.jsp?id=666&id=555") {
      new URLProvider(Environment.Test).contractInformation(List("666", "555"))
    }
  }

  test("should create closing prices request") {
    expect("http://testexternal.intrade.com/jsp/XML/MarketData/ClosingPrice.jsp?conID=9876") {
      new URLProvider(Environment.Test).closingPrices("9876")
    }
  }

  test("should create time and sales request") {
    expect("http://api.intrade.com/jsp/XML/TradeData/TimeAndSales.jsp?conID=1234") {
      new URLProvider(Environment.Live).dailyTimeAndSales("1234")
    }
  }
}