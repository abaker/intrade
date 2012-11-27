package org.intrade.samples

import java.text.SimpleDateFormat
import java.util.Date
import org.intrade.util.{ContractCache, CredentialCache}
import org.intrade.Environment

object SampleUtils {
  private val env = Environment.Test

  def joinWithCommas(items: Any*) = items.mkString(",")

  def printDate(timestamp: Long) =
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date(timestamp).getTime)

  def getEventClasses = {
    val api = getDataAPI
    val contractCache = new ContractCache(api, "./out/intrade_%s_contracts.xml" format(env))
    val response = contractCache.loadEventClasses
    response.payload
  }

  def getDataAPI = org.intrade.data.API.create(env)

  def getTradingAPI(appID: String) = {
    val login = getLogin
    if (login.resultCode != 0) {
      throw new RuntimeException(login.faildesc)
    }
    org.intrade.trading.API.create(appID, login)
  }

  private def getLogin = {
    val cache = new CredentialCache(env, "./out/intrade_%s_credentials.xml" format(env))
    if (cache.invalidCredentials) {
      val username = scala.Console.readLine("username: ")
      val password = scala.Console.readLine("password: ")
      cache.refreshCredentials(username, password)
    }
    cache.loadCredentials
  }
}