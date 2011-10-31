package org.intrade.trading

object OrderType extends Enumeration {
  type OrderType = Value
  val Limit, Touch, Fill_Or_Kill = Value
}