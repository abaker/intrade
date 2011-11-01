package org.intrade.trading

import xml.Node
import org.intrade.Implicits._

object CancelConfirmation {

  case class CancelConfirmationImpl(isEndOfDay: Boolean,
                                    didCancel: Boolean,
                                    orderIDs: Seq[Int])
    extends CancelConfirmation

  def apply(node: Node) =
    CancelConfirmationImpl(
      node \ "isEndOfDay",
      node \ "didCancel",
      node \ "orderCancelList" \ "ordID" map nodeSeq2Int)
}

trait CancelConfirmation {
  def isEndOfDay: Boolean

  def didCancel: Boolean

  def orderIDs: Seq[Int]
}