package org.intrade.util

import org.intrade.trading
import java.io.File
import org.intrade.Environment._
import org.intrade.trading.Login

class CredentialCache(private val env: Environment, private val cache: FileCache) {
  def this(env: Environment, path: String) = this(env, new FileCache(360, new File(path)))

  def invalidCredentials = cache.refreshNeeded || loadCredentials.resultCode != 0

  def loadCredentials = trading.Response(cache.path, cache.readFromCache, Login.apply)

  def refreshCredentials(username: String, password: String) {
    val loginResponse = org.intrade.trading.API.login(env, username, password)
    cache.writeToCache(loginResponse.raw)
  }
}
