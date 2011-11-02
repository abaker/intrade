package org.intrade.trading

import io.Source._
import org.intrade.trading.TimeInForce._
import org.intrade.trading.Side._
import org.intrade.Implicits._
import org.intrade.Environment._
import org.intrade.Environment
import java.net.URL
import java.io.PrintWriter
import xml.{XML, Node}

object API {
  def getLogin(env: Environment, username: String, password: String, appID: String) = {
    val url = new URL(Environment.tradingUrl(env))
    send(url, Requests.getLogin(username, password, appID), Login.apply)
  }

  def send[A](url: URL, request: Node, f: Node => A): Response[A] = {
    val conn = url.openConnection()
    conn.setDoOutput(true)
    val printWriter = new PrintWriter(conn.getOutputStream)
    printWriter.write(request.toString())
    printWriter.close()
    val stream = fromInputStream(conn.getInputStream)
    val text = stream.getLines().mkString
    stream.close()
    val response = XML.loadString(text)
    try {
      Response[A](request, response, f)
    } catch {
      case e: Exception =>
        throw new RuntimeException("FAILED REQUEST: %s, RESPONSE: %s, ERROR: %s" format(request, response, e))
    }
  }

  def apply(env: Environment, username: String, password: String, appID: String): API =
    apply(env, appID, getLogin(env, username, password, appID).sessionData)

  def apply(env: Environment, appID: String, sessionData: String) = new API {
    private val url = new URL(Environment.tradingUrl(env))
    private val auth = List(
      <appID>
        {appID}
      </appID>,
      <sessionData>
        {sessionData}
      </sessionData>)

    def getBalance = send(Requests.getBalance, Balance.apply)

    def getPositions =
      send(Requests.getPosForUser(), node => node \ "position" map Position.apply)

    def getPosition(contractId: Int) =
      send(Requests.getPosForUser(contractId), node => Position(node \ "position" head))

    def getOrders =
      send(Requests.getOpenOrders(), node => node \ "order" map Order.apply)

    def getOrders(contractId: Int) =
      send(Requests.getOpenOrders(contractId), node => node \ "order" map Order.apply)

    def getOrderDetails(orderIDs: Seq[Int]) =
      send(Requests.getOrdersForUser(orderIDs), node => node \ "order" map OrderDetails.apply)

    def cancelAll =
      send(Requests.cancelAllOrdersForUser, CancelConfirmation.apply)

    def cancelAll(contractID: Int) =
      send(Requests.getCancelAllInContract(contractID), CancelConfirmation.apply)

    def cancelAll(contractID: Int, side: Side) = side match {
      case Buy => send(Requests.getCancelAllBids(contractID), CancelConfirmation.apply)
      case Sell => send(Requests.getCancelAllOffers(contractID), CancelConfirmation.apply)
    }

    def cancelAllInEvent(eventID: Int) =
      send(Requests.cancelAllInEvent(eventID), CancelConfirmation.apply)

    def cancelOrders(orderIDs: Seq[Int]) =
      send(Requests.cancelMultipleOrdersForUser(orderIDs), CancelConfirmation.apply)

    def getGSXToday =
      send(Requests.getGSXToday, node => node.attribute("hasMessages").isDefined)

    def getUserMessages =
      send(Requests.getUserMessages(), node => node \ "msg" map UserMessage.apply)

    def getUserMessages(timestamp: Long) =
      send(Requests.getUserMessages(timestamp), node => node \ "msg" map UserMessage.apply)

    def setAsRead(messageIDs: Seq[Int]) =
      send(Requests.setAsRead(messageIDs), _ => ())

    def updateMultiOrder(orders: Seq[BasicOrderRequest],
                         cancelPrevious: Boolean = false,
                         quickCancel: Boolean = false,
                         timeInForce: TimeInForce = TimeInForce.Good_Til_Cancel,
                         timeToExpire: Long = 0) =
      send(Requests.updateMultiOrder(orders, cancelPrevious, quickCancel, timeInForce, timeToExpire),
        node => node \ "order" map Order.apply)

    private def send[A](request: Node, f: Node => A): Response[A] =
      API.send(url, request.append(auth), f)
  }
}

trait API {
  def getBalance: Response[Balance]

  def getPositions: Response[Seq[Position]]

  def getPosition(contractId: Int): Response[Position]

  def getOrders: Response[Seq[Order]]

  def getOrders(contractId: Int): Response[Seq[Order]]

  def getOrderDetails(orderIDs: Seq[Int]): Response[Seq[OrderDetails]]

  def cancelAll: Response[CancelConfirmation]

  def cancelAll(contractID: Int): Response[CancelConfirmation]

  def cancelAll(contractID: Int, side: Side): Response[CancelConfirmation]

  def cancelAllInEvent(eventID: Int): Response[CancelConfirmation]

  def cancelOrders(orderIDs: Seq[Int]): Response[CancelConfirmation]

  def getGSXToday: Response[Boolean]

  def getUserMessages: Response[Seq[UserMessage]]

  def getUserMessages(timestamp: Long): Response[Seq[UserMessage]]

  def setAsRead(messageIDs: Seq[Int]): Response[Unit]

  def updateMultiOrder(orders: Seq[BasicOrderRequest],
                       cancelPrevious: Boolean = false,
                       quickCancel: Boolean = false,
                       timeInForce: TimeInForce = TimeInForce.Good_Til_Cancel,
                       timestamp: Long = 0): Response[Seq[Order]]
}