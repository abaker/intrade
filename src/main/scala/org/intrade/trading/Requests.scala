package org.intrade.trading

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

  def updateMultiOrder =
    throw new RuntimeException("not implemented")

  def multiOrderRequest =
    throw new RuntimeException("not implemented")

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