package org.intrade.samples

import java.text.SimpleDateFormat
import java.util.Date
import org.intrade.util.{ContractCache, CredentialCache}
import org.intrade.Environment
import java.io.File

object SampleUtils {
  new File("./out").mkdir()
  private val env = Environment.Test
  private val dataAPI = org.intrade.data.API.create(env)
  private val contractCollection = {
    val contractCache = new ContractCache(dataAPI, "./out/intrade_%s_contracts.xml" format (env))
    val response = contractCache.loadEventClasses
    new ContractCollection(response.payload)
  }

  def getContractCollection = contractCollection

  def getDataAPI = dataAPI

  def getTradingAPI(appID: String) = {
    val credentialCache = new CredentialCache(env, "./out/intrade_%s_credentials.xml" format (env))
    if (credentialCache.invalidCredentials) {
      val username = prompt("username: ")
      val password = prompt("password: ")
      credentialCache.refreshCredentials(username, password)
    }
    org.intrade.trading.API.create(appID, credentialCache.loadCredentials)
  }

  def joinWithCommas(items: Any*) = items.mkString(",")

  def printDate(timestamp: Long) =
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date(timestamp).getTime)

  def prompt(message: String) = {
    print(message)
    scala.Console.flush()
    scala.Console.readLine()
  }
}