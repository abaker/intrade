package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object EventGroup {
  def apply(node: Node) = new EventGroup {
    val id: String = node \ "@id"
    val name: String = node \ "name"
    val displayOrder: Int = node \ "displayOrder"
    val events = node \ "Event" map Event.apply
  }
}

trait EventGroup {
  def id: String

  def name: String

  def displayOrder: Int

  def events: Seq[Event]
}