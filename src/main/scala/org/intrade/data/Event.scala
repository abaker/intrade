package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object Event {

  case class EventImpl(endDate: Long,
                       startDate: Long,
                       groupID: String,
                       id: String,
                       description: String,
                       name: String,
                       displayOrder: Int,
                       contracts: Seq[Contract])
    extends Event

  def apply(node: Node) =
    EventImpl(
      node \ "@EndDate",
      node \ "@StartDate",
      node \ "@groupID",
      node \ "@id",
      node \ "Description",
      node \ "name",
      node \ "displayOrder",
      node \ "contract" map Contract.apply)
}

trait Event {
  def endDate: Long

  def startDate: Long

  def groupID: String

  def id: String

  def description: String

  def name: String

  def displayOrder: Int

  def contracts: Seq[Contract]
}