package org.intrade

import xml.{NodeSeq, Node}

object Implicits {
  implicit def string2ScalaBigDecimal(s: String): scala.math.BigDecimal = BigDecimal(s)

  implicit def string2JavaLong(s: String) = java.lang.Long.parseLong(s)

  implicit def attribute2Long(node: Option[Seq[Node]]): Long = java.lang.Long.parseLong(node.get.text)

  implicit def attribute2String(node: Option[Seq[Node]]): String = node.get.text

  implicit def attribute2BigDecimal(node: Option[Seq[Node]]): BigDecimal = BigDecimal(node.get.text)

  implicit def attribute2Int(node: Option[Seq[Node]]): Int = java.lang.Integer.parseInt(node.get.text)

  implicit def attribute2Boolean(node: Option[Seq[Node]]): Boolean = java.lang.Boolean.parseBoolean(node.get.text)

  implicit def nodeSeq2Long(node: NodeSeq): Long = java.lang.Long.parseLong(node.text)

  implicit def nodeSeq2String(node: NodeSeq): String = node.text

  implicit def nodeSeq2BigDecimal(node: NodeSeq): BigDecimal = BigDecimal(node.text)

  implicit def nodeSeq2Int(node: NodeSeq): Int = java.lang.Integer.parseInt(node.text)

  implicit def nodeSeq2Boolean(node: NodeSeq): Boolean = java.lang.Boolean.parseBoolean(node.text)
}
