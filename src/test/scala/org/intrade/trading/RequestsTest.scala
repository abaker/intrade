package org.intrade.trading

import org.intrade.trading.Side._
import org.intrade.trading.TimeInForce._
import org.intrade.trading.OrderType._
import org.intrade.trading.Requests._
import org.scalatest.FunSuite
import xml.Utility.trim
import xml.Node

class RequestsTest extends FunSuite {

  case class OrderRequestImpl(conID: Int,
                              side: Side,
                              quantity: Int,
                              limitprice: BigDecimal,
                              override val userReference: String = "",
                              override val timeInForce: TimeInForce = Good_Til_Cancel,
                              override val timeToExpire: Long = 0L,
                              override val orderType: OrderType = Limit,
                              override val touchPrice: BigDecimal = 0)
    extends OrderRequest

  test("create login request") {
    val expected =
      <xmlrequest requestOp="getLogin">
        <membershipNumber>some.user</membershipNumber>
        <password>passw0rd</password>
        <appID>app.v1</appID>
      </xmlrequest>

    compareXml(expected, getLogin("some.user", "passw0rd", "app.v1"))
  }

  test("create balance request") {
    compareXml(<xmlrequest requestOp="getBalance"/>, getBalance)
  }

  test("create position request") {
    compareXml(<xmlrequest requestOp="getPosForUser"/>, getPosForUser())
  }

  test("create position request for single contract") {
    val expected =
      <xmlrequest requestOp="getPosForUser">
        <contractID>
          {1234}
        </contractID>
      </xmlrequest>

    compareXml(expected, getPosForUser(1234))
  }

  test("create open orders request") {
    compareXml(<xmlrequest requestOp="getOpenOrders"/>, getOpenOrders())
  }

  test("create open orders request for single contract") {
    val expected =
      <xmlrequest requestOp="getOpenOrders">
        <contractID>
          {1234}
        </contractID>
      </xmlrequest>

    compareXml(expected, getOpenOrders(1234))
  }

  test("get orders for user") {
    val expected =
      <xmlrequest requestOp="getOrdersForUser">
        <orderID>
          {1234}
        </orderID>
        <orderID>
          {5678}
        </orderID>
      </xmlrequest>

    compareXml(expected, getOrdersForUser(List(1234, 5678)))
  }

  test("get user messages") {
    compareXml(<xmlrequest requestOp="getUserMessages"/>, getUserMessages())
  }

  test("get user messages from timestamp") {
    val expected =
      <xmlrequest requestOp="getUserMessages">
        <timestamp>
          {12345}
        </timestamp>
      </xmlrequest>

    compareXml(expected, getUserMessages(12345))
  }

  test("set messages as read") {
    val expected =
      <xmlrequest requestOp="setAsRead">
        <userNotificationID>
          {1234}
        </userNotificationID>
        <userNotificationID>
          {5678}
        </userNotificationID>
      </xmlrequest>

    compareXml(expected, setAsRead(List(1234, 5678)))
  }

  test("check messages") {
    val expected =
      <xmlrequest requestOp="getGSXToday">
        <checkMessages>true</checkMessages>
      </xmlrequest>

    compareXml(expected, getGSXToday)
  }

  test("cancel all orders for user") {
    compareXml(<xmlrequest requestOp="cancelAllOrdersForUser"/>, cancelAllOrdersForUser)
  }

  test("cancel all for event id") {
    val expected =
      <xmlrequest requestOp="cancelAllInEvent">
        <eventID>
          {1234}
        </eventID>
      </xmlrequest>

    compareXml(expected, cancelAllInEvent(1234))
  }

  test("cancel all in contract") {
    val expected =
      <xmlrequest requestOp="getCancelAllInContract">
        <contractID>
          {1234}
        </contractID>
      </xmlrequest>

    compareXml(expected, getCancelAllInContract(1234))
  }

  test("cancel all bids in contract") {
    val expected =
      <xmlrequest requestOp="getCancelAllBids">
        <contractID>
          {1234}
        </contractID>
      </xmlrequest>

    compareXml(expected, getCancelAllBids(1234))
  }

  test("cancel all offers in contract") {
    val expected =
      <xmlrequest requestOp="getCancelAllOffers">
        <contractID>
          {1234}
        </contractID>
      </xmlrequest>

    compareXml(expected, getCancelAllOffers(1234))
  }

  test("cancel multiple orders") {
    val expected =
      <xmlrequest requestOp="cancelMultipleOrdersForUser">
        <orderID>
          {1234}
        </orderID>
        <orderID>
          {5678}
        </orderID>
      </xmlrequest>

    compareXml(expected, cancelMultipleOrdersForUser(List(1234, 5678)))
  }

  test("create basic limit orders") {
    val expected =
      <xmlrequest requestOp="multiOrderRequest">
        <order>conID=1234,side=B,quantity=3,limitPrice=45.5,timeInForce=GTC</order>
        <order>conID=5678,side=S,quantity=25,limitPrice=50.0,timeInForce=GTC</order>
        <order>conID=999,side=B,quantity=1,limitPrice=0.1,timeInForce=GTC,userReference=abc123</order>
      </xmlrequest>

    val buy = OrderRequestImpl(1234, Buy, 3, 45.5)
    val sell = OrderRequestImpl(5678, Sell, 25, 50.0)
    val userRef = OrderRequestImpl(999, Buy, 1, 0.1, "abc123")

    compareXml(expected, multiOrderRequest(List(buy, sell, userRef), false))
  }

  test("gfs and gtt") {
    val expected =
      <xmlrequest requestOp="multiOrderRequest">
        <order>conID=1234,side=B,quantity=1,limitPrice=99.9,timeInForce=GFS</order>
        <order>conID=1234,side=B,quantity=1,limitPrice=99.9,timeInForce=GFS,userReference=aaa111</order>
        <order>conID=5678,side=S,quantity=1,limitPrice=11.1,timeInForce=GTT,timeToExpire=12345</order>
        <order>conID=9191,side=S,quantity=100,limitPrice=50,timeInForce=GTT,timeToExpire=98765,userReference=abc123</order>
      </xmlrequest>

    val gfs = OrderRequestImpl(1234, Buy, 1, 99.9, "", Good_For_Session)
    val gfsWithUserRef = OrderRequestImpl(1234, Buy, 1, 99.9, "aaa111", Good_For_Session)
    val gtt = OrderRequestImpl(5678, Sell, 1, 11.1, "", Good_Til_Time, 12345L)
    val gttWithUserRef = OrderRequestImpl(9191, Sell, 100, 50, "abc123", Good_Til_Time, 98765L)

    compareXml(expected, multiOrderRequest(List(gfs, gfsWithUserRef, gtt, gttWithUserRef), false))
  }

  test("fok and touch orders") {
    val expected =
      <xmlrequest requestOp="multiOrderRequest">
        <order>conID=1234,side=B,quantity=1,limitPrice=99.9,timeInForce=GTC,orderType=F</order>
        <order>conID=1234,side=S,quantity=1,limitPrice=99.9,timeInForce=GTC,orderType=F,userReference=aaa111</order>
        <order>conID=5678,side=S,quantity=1,limitPrice=11.1,timeInForce=GTC,orderType=T,touchPrice=45.0</order>
        <order>conID=9191,side=B,quantity=1,limitPrice=50,timeInForce=GTC,orderType=T,touchPrice=99,userReference=abc123</order>
      </xmlrequest>

    val fok = OrderRequestImpl(1234, Buy, 1, 99.9, "", Good_Til_Cancel, 0, OrderType.Fill_Or_Kill)
    val fokWithUserRef = OrderRequestImpl(1234, Sell, 1, 99.9, "aaa111", Good_Til_Cancel, 0, OrderType.Fill_Or_Kill)
    val touch = OrderRequestImpl(5678, Sell, 1, 11.1, "", Good_Til_Cancel, 0, OrderType.Touch, 45.0)
    val touchWithUserRef = OrderRequestImpl(9191, Buy, 1, 50, "abc123", Good_Til_Cancel, 0, OrderType.Touch, 99)

    compareXml(expected, multiOrderRequest(List(fok, fokWithUserRef, touch, touchWithUserRef), false))
  }

  test("multi order cancel with quick cancel") {
    val expected =
      <xmlrequest requestOp="multiOrderRequest">
        <cancelPrevious>true</cancelPrevious>
        <quickCancel>true</quickCancel>
        <order>conID=1234,side=B,quantity=3,limitPrice=45.5,timeInForce=GTC</order>
      </xmlrequest>

    compareXml(expected, multiOrderRequest(List(OrderRequestImpl(1234, Buy, 3, 45.5)), true))
  }

  test("get trades by contract id") {
    val expected =
      <xmlrequest requestOp="getTradesForUser">
        <contractID>
          {1234}
        </contractID>
      </xmlrequest>

    compareXml(expected, getTradesForUser(1234))
  }

  test("get trades since after timestamp") {
    val expected =
      <xmlrequest requestOp="getTradesForUser">
        <tradeStartTimestamp>
          {1234L}
        </tradeStartTimestamp>
      </xmlrequest>

    compareXml(expected, getTradesForUser(1234L))
  }

  test("get trades during time period") {
    val expected =
      <xmlrequest requestOp="getTradesForUser">
        <tradeStartTimestamp>
          {1234L}
        </tradeStartTimestamp>
        <endDate>
          {5678L}
        </endDate>
      </xmlrequest>

    compareXml(expected, getTradesForUser(1234L, 5678L))
  }

  private def compareXml(expected: Node, actual: Node) {
    expect(trim(expected)) {
      trim(actual)
    }
  }
}