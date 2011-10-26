package org.intrade.data

import xml.Node

trait Response[A] {
  def timestamp: Option[Long]

  def response: Node

  def values: Seq[A]
}