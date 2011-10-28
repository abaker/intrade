package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object EventGroup {

  case class EventGroupImpl(id: Int,
                            name: String,
                            displayOrder: Int,
                            events: Seq[Event])
    extends EventGroup

  def apply(node: Node) =
    EventGroupImpl(
      node \ "@id",
      node \ "name",
      node \ "displayOrder",
      node \ "Event" map Event.apply)
}

trait EventGroup {
  def id: Int

  def name: String

  def displayOrder: Int

  def events: Seq[Event]
}