package org.intrade.data

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