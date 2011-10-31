package org.intrade.trading

object TimeInForce extends Enumeration {
  type TimeInForce = Value
  val Good_Til_Cancel, Good_For_Session, Good_Til_Time = Value
}