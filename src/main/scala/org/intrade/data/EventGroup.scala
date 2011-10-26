package org.intrade.data

trait EventGroup {
  def id: String

  def name: String

  def displayOrder: Int

  def events: Seq[Event]
}