package org.intrade.trading

import org.intrade.trading.Requests._
import org.scalatest.FunSuite
import xml.Utility.trim
import xml.Node

class RequestsTest extends FunSuite {
  test("create login request") {
    val expected =
      <xmlrequest requestOp="getLogin">
        <membershipNumber>some.user</membershipNumber>
        <password>passw0rd</password>
      </xmlrequest>

    compareXml(expected, getLogin("some.user", "passw0rd"))
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

  test("get orders for single order id") {
    val expected =
      <xmlrequest requestOp="getOrdersForUser">
        <orderID>
          {1234}
        </orderID>
      </xmlrequest>

    compareXml(expected, getOrdersForUser(List(1234)))
  }

  test("get orders for multiple order ids") {
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
        <timestamp>{12345}</timestamp>
      </xmlrequest>

    compareXml(expected, getUserMessages(12345))
  }

  private def compareXml(expected: Node, actual: Node) {
    expect(trim(expected)) {
      trim(actual)
    }
  }
}