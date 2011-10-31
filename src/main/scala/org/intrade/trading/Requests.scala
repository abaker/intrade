package org.intrade.trading

import orders._
import org.intrade.trading.Side._

object Requests {
  def getLogin(username: String, password: String) =
    <xmlrequest requestOp="getLogin">
      <membershipNumber>
        {username}
      </membershipNumber>
      <password>
        {password}
      </password>
    </xmlrequest>

  def getBalance = <xmlrequest requestOp="getBalance"/>

  def multiOrderRequest(orders: Seq[OrderRequest], cancelPrevious: Boolean = false, quickCancel: Boolean = false) =
    <xmlrequest requestOp="multiOrderRequest">
      {if (cancelPrevious) {
      if (quickCancel)
        <cancelPrevious>true</cancelPrevious> <quickCancel>true</quickCancel>
      else
        <cancelPrevious>true</cancelPrevious>
    }}{for (order <- orders) yield
      <order>
        {orderToOrderString(order)}
      </order>}
    </xmlrequest>

  def timeInForce[T <: OrderRequest](order: T) = order match {
    case order: GoodTilTime => "timeInForce=GTT,timeToExpire=%s" format order.timeToExpire
    case order: GoodForSession => "timeInForce=GFS"
    case order: OrderRequest => "timeInForce=GTC"
  }

  def orderType[T <: OrderRequest](order: T) = order match {
    case order: Touch => ",orderType=T,touchPrice=%s" format order.touchPrice
    case order: FillOrKill => ",orderType=F"
    case order: OrderRequest => ""
  }

  def orderToOrderString[T <: OrderRequest](order: T) =
    "conID=%s,side=%s,limitPrice=%s,quantity=%s,%s%s" format(
      order.conID, sideToString(order.side), order.limitprice, order.quantity, timeInForce(order), orderType(order))

  def sideToString(side: Side) = side match {
    case Buy => "B"
    case Sell => "S"
  }

  def cancelMultipleOrdersForUser(orderIDs: Seq[Int]) =
    <xmlrequest requestOp="cancelMultipleOrdersForUser">
      {for (orderID <- orderIDs) yield
      <orderID>
        {orderID}
      </orderID>}
    </xmlrequest>

  def cancelAllInContract(contractID: Int) =
    <xmlrequest requestOp="cancelAllInContract">
      <contractID>
        {contractID}
      </contractID>
    </xmlrequest>

  def cancelAllBids(contractID: Int) =
    <xmlrequest requestOp="cancelAllBids">
      <contractID>
        {contractID}
      </contractID>
    </xmlrequest>

  def cancelAllOffers(contractID: Int) =
    <xmlrequest requestOp="cancelAllOffers">
      <contractID>
        {contractID}
      </contractID>
    </xmlrequest>

  def cancelAllInEvent(eventID: Int) =
    <xmlrequest requestOp="cancelAllInEvent">
      <eventID>
        {eventID}
      </eventID>
    </xmlrequest>

  def cancelAllOrdersForUser =
      <xmlrequest requestOp="cancelAllOrdersForUser"/>

  def getPosForUser(contractID: Int = 0) =
    <xmlrequest requestOp="getPosForUser">
      {if (contractID > 0)
      <contractID>
        {contractID}
      </contractID>}
    </xmlrequest>

  def getOpenOrders(contractID: Int = 0) =
    <xmlrequest requestOp="getOpenOrders">
      {if (contractID > 0)
      <contractID>
        {contractID}
      </contractID>}
    </xmlrequest>

  def getOrdersForUser(orderIDs: Seq[Int]) =
    <xmlrequest requestOp="getOrdersForUser">
      {for (orderID <- orderIDs) yield
      <orderID>
        {orderID}
      </orderID>}
    </xmlrequest>

  def getUserMessages(timestamp: Long = 0) =
    <xmlrequest requestOp="getUserMessages">
      {if (timestamp > 0)
      <timestamp>
        {timestamp}
      </timestamp>}
    </xmlrequest>

  def setAsRead(notificationIDs: Seq[Int]) =
    <xmlrequest requestOp="setAsRead">
      {for (notificationID <- notificationIDs) yield
      <userNotificationID>
        {notificationID}
      </userNotificationID>}
    </xmlrequest>

  def getTradesForUser =
    throw new RuntimeException("not implemented")

  def getGSXToday =
    <xmlrequest requestOp="getGSXToday">
      <checkMessages>true</checkMessages>
    </xmlrequest>
}