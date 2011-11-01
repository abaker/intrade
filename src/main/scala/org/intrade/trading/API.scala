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
    send(url, Requests.getLogin(username, password)
      .append(
      <appID>
        {appID}
      </appID>))
  }

  def send(url: URL, request: Node) = {
    val conn = url.openConnection()
    conn.setDoOutput(true)
    val printWriter = new PrintWriter(conn.getOutputStream)
    println("Request: " + request.toString())
    printWriter.write(request.toString())
    printWriter.close()
    val stream = fromInputStream(conn.getInputStream)
    val text = stream.getLines().mkString
    println("Response: " + text)
    stream.close()
    XML.loadString(text)
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

    private def send[A](request: Node, f: Node => A): Response[A] = {
      val result = API.send(url, request.append(auth))
      Response(request, result, f)
    }
  }
}

trait API {
  def getBalance: Response[Balance]

  def getPosForUser(contractId: Int = 0): Response[Seq[Position]]
}