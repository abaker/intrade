package org.intrade

object Environment extends Enumeration {
  type Environment = Value
  val Live, Test = Value

  def dataUrl(env: Environment) = "http://%s" format host(env)

  def tradingUrl(env: Environment) = env match {
    case Live => "https://%s" format host(env)
    case Test => "http://%s" format host(env)
  }

  private def host(env: Environment) = env match {
    case Live => "api.intrade.com"
    case Test => "testexternal.intrade.com"
  }
}