package org.intrade.util

import org.intrade.Environment
import org.intrade.trading.Login
import org.intrade.Implicits.string2Node
import java.io.File
import org.intrade.trading.Response

class CredentialCache(private val environment: Environment.Environment,
                      private val user: String,
                      private val pass: String,
                      private val cache: FileCache) {

  def this(environment: Environment.Environment,
           user: String,
           pass: String,
           path: String = "./out/intrade_session.xml") = this(environment, user, pass, new FileCache(360, new File(path)))

  def loadSessionData = {
    if (cache.refreshNeeded) {
      refresh
    } else {
      val xmlString = cache.readFromCache
      val response = Response.apply(cache.path, xmlString, Login.apply)
      if (response.errorcode == 0) response else refresh
    }
  }

  private def refresh = {
    val response = org.intrade.trading.API.getLogin(environment, user, pass)
    cache.writeToCache(response.raw)
    response
  }
}
