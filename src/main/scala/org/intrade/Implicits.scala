package org.intrade

import xml.{Elem, NodeSeq}

object Implicits {
  implicit def string2ScalaBigDecimal(s: String) = BigDecimal(s)

  implicit def string2JavaLong(s: String) = java.lang.Long.parseLong(s)

  implicit def string2JavaInt(s: String) = java.lang.Integer.parseInt(s)

  implicit def nodeSeq2Long(node: NodeSeq) = java.lang.Long.parseLong(node.text)

  implicit def nodeSeq2String(node: NodeSeq) = node.text

  implicit def nodeSeq2BigDecimal(node: NodeSeq) = BigDecimal(node.text)

  implicit def nodeSeq2Int(node: NodeSeq) = java.lang.Integer.parseInt(node.text)

  implicit def nodeSeq2Boolean(node: NodeSeq) = java.lang.Boolean.parseBoolean(node.text)

  implicit def nodeSeq2BigDecimalOption(node: NodeSeq): Option[BigDecimal] = node.text match {
    case "-" | "" => Option.empty
    case s: String => Option(s)
  }

  implicit def nodeSeq2LongOption(node: NodeSeq): Option[Long] = node.text match {
    case "-" => Option.empty
    case s: String => Option(s)
  }

  implicit def nodeSeq2IntOption(node: NodeSeq): Option[Int] = node.text match {
    case "" => Option.empty
    case s: String => Option(s)
  }

  implicit def append2NodeSeq(node: NodeSeq) = new {
    def append(addMe: NodeSeq) = node match {
      case Elem(prefix, label, attribs, scope, child@_*) =>
        Elem(prefix, label, attribs, scope, child ++ addMe: _*)
    }
  }
}
