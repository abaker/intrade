package org.intrade.trading

import xml.NodeSeq

object OrderStatus extends Enumeration {
  type OrderStatus = Value
  val Limit_Checked, New, Partially_Filled, Touched, Deleted_By_User,
  Deleted_By_Admin, Filled, Expired, Mass_Delete, Settled, Rejected,
  Cancel_Rejected = Value

  implicit def nodeSeq2OrderStatus(node: NodeSeq) = node.text match {
    case "LCO" => Limit_Checked
    case "CEN" => New
    case "CEMX" => Partially_Filled
    case "CEMT" => Touched
    case "CEDU" => Deleted_By_User
    case "CEDA" => Deleted_By_Admin
    case "CEDX" => Filled
    case "CEDE" => Expired
    case "CEDM" => Mass_Delete
    case "CEDS" => Settled
    case "CENR" => Rejected
    case "CERC" => Cancel_Rejected
  }
}