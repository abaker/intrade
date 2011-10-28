package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object EventClass {

  case class EventClassImpl(id: Int,
                            name: String,
                            displayOrder: Int,
                            eventGroups: Seq[EventGroup])
    extends EventClass

  def apply(node: Node) =
    EventClassImpl(
      node \ "@id",
      node \ "name",
      node \ "displayOrder",
      node \ "EventGroup" map EventGroup.apply)
}

trait EventClass {
  def id: Int

  def name: String

  def displayOrder: Int

  def eventGroups: Seq[EventGroup]
}