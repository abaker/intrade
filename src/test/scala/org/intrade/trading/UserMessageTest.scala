package org.intrade.trading

import org.scalatest.FunSuite

class UserMessageTest extends FunSuite {
  test("parse message") {
    val node =
      <msg msgID="40345839">
        <msgID>40345839</msgID>
        <conID>743475</conID>
        <symbol>2012.PRES.ROMNEY</symbol>
        <readFlag>true</readFlag>
        <type>X</type>
        <msg>647987458</msg>
        <price>0.1</price>
        <quantity>1</quantity>
        <side>B</side>
        <timestamp>1320185824774</timestamp>
      </msg>

    val message = UserMessage(node)

    expect(40345839) {
      message.msgID
    }
    expect(743475) {
      message.conID
    }
    expect("2012.PRES.ROMNEY") {
      message.symbol
    }
    expect(true) {
      message.readFlag
    }
    expect(UserMessageType.Expired_Order) {
      message.messageType
    }
    expect(647987458) {
      message.msg
    }
    expect(BigDecimal(0.1)) {
      message.price
    }
    expect(1) {
      message.quantity
    }
    expect(Side.Buy) {
      message.side
    }
    expect(1320185824774L) {
      message.timestamp
    }
  }
}