package org.intrade.data

import xml.Node
import org.intrade.Implicits._

object Event {
  def apply(node: Node) = new Event {
    val endDate: Long = node.attribute("EndDate")
    val startDate: Long = node.attribute("StartDate")
    val groupID: String = node.attribute("groupID")
    val id: String = node.attribute("id")
    val description: String = node \ "Description"
    val name: String = node \ "name"
    val displayOrder: Int = node \ "displayOrder"
    val contracts = node \ "contract" map Contract.apply
  }
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