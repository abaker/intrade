package org.intrade

object Environment extends Enumeration {
  type Environment = Value
  val Live, Test = Value

  def dataUrl(env: Environment) = "http://%s" format host(env)

  def tradingUrl(exchange: String): String = exchange match {
    case "testExt_intrade" => tradingUrl(Test)
    case "intrade" => tradingUrl(Live)
    case _ => throw new RuntimeException("Unknown exchange: " + exchange)
  }

  def tradingUrl(env: Environment) = env match {
    case Live => "https://%s/xml/handler.jsp" format host(env)
    case Test => "http://%s/xml/handler.jsp" format host(env)
  }

  private def host(env: Environment) = env match {
    case Live => "api.intrade.com"
    case Test => "testexternal.intrade.com"
  }
}