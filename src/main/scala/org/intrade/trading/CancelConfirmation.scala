package org.intrade.trading

import xml.Node
import org.intrade.Implicits._

object CancelConfirmation {

  case class CancelConfirmationImpl(isEndOfDay: Boolean,
                                    didCancel: Boolean)
    extends CancelConfirmation

  def apply(node: Node) =
    CancelConfirmationImpl(
      node \ "isEndOfDay",
      node \ "didCancel")
}

trait CancelConfirmation {
  def isEndOfDay: Boolean

  def didCancel: Boolean
}