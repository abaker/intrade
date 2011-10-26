package org.intrade.data

object BookLevel {
  def apply(px: BigDecimal, qty: Int) = new BookLevel {
    val price = px
    val quantity = qty

    override def hashCode = toString.hashCode()

    override def toString = "%s@%s" format(quantity, price)

    override def equals(o: Any): Boolean = {
      o.isInstanceOf[BookLevel] && hashCode() == o.hashCode()
    }
  }
}

trait BookLevel {
  def price: BigDecimal

  def quantity: Int
}