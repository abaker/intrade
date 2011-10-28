package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object Event {

  case class EventImpl(endDate: Long,
                       startDate: Long,
                       groupID: Int,
                       id: Int,
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

  def groupID: Int

  def id: Int

  def description: String

  def name: String

  def displayOrder: Int

  def contracts: Seq[Contract]
}