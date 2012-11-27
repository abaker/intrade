package org.intrade.samples

import org.intrade.samples.SampleUtils._

object GetPositions extends App {
  override def main(args: Array[String]) {
    val trading = getTradingAPI("GetPositionsSample")
    val positionResponse = trading.getPositions
    for (position <- positionResponse.payload) {
      println(position)
    }
  }
}
