package org.intrade.trading

import io.Source._
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
        throw new RuntimeException("Failed, request: %s, response: %s, error: %s" format(request, response, e))
    }
  }

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

    def getPosForUser(contractId: Int = 0) =
      send(Requests.getPosForUser(contractId), node => node \ "position" map Position.apply)

    def getOpenOrders(contractId: Int = 0) =
      send(Requests.getOpenOrders(contractId), node => node \ "order" map Order.apply)

    def getOrdersForUser(orderIDs: Seq[Int]) =
      send(Requests.getOrdersForUser(orderIDs), node => node \ "order" map OrderDetails.apply)

    def getCancelAllInContract(contractID: Int) =
      send(Requests.getCancelAllInContract(contractID), CancelConfirmation.apply)

    def getCancelAllBids(contractID: Int) =
      send(Requests.getCancelAllBids(contractID), CancelConfirmation.apply)

    def getCancelAllOffers(contractID: Int) =
      send(Requests.getCancelAllOffers(contractID), CancelConfirmation.apply)

    def cancelAllInEvent(eventID: Int) =
      send(Requests.cancelAllInEvent(eventID), CancelConfirmation.apply)

    def cancelAllOrdersForUser =
      send(Requests.cancelAllOrdersForUser, CancelConfirmation.apply)

    private def send[A](request: Node, f: Node => A): Response[A] =
      API.send(url, request.append(auth), f)
  }
}

trait API {
  def getBalance: Response[Balance]

  def getPosForUser(contractId: Int = 0): Response[Seq[Position]]

  def getOpenOrders(contractId: Int = 0): Response[Seq[Order]]

  def getOrdersForUser(orderIDs: Seq[Int]): Response[Seq[OrderDetails]]

  def getCancelAllInContract(contractID: Int): Response[CancelConfirmation]

  def getCancelAllBids(contractID: Int): Response[CancelConfirmation]

  def getCancelAllOffers(contractID: Int): Response[CancelConfirmation]

  def cancelAllInEvent(eventID: Int): Response[CancelConfirmation]

  def cancelAllOrdersForUser: Response[CancelConfirmation]
}