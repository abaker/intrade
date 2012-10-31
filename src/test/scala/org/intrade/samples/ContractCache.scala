package org.intrade.samples

import org.intrade._
import ContractCache._
import data.Response
import java.util.concurrent.TimeUnit
import java.io.{FileWriter, File}
import java.util.Date

/*
From the Intrade API Documentation:
"As [the contract listing] is a large file, [it] should only be retrieved at start-up and not more than one time per 15 minutes."

The purpose of this class is to store contracts on disk so we may reuse them in other samples
without violating the policy mentioned above. This is not a robust implementation as it assumes
all file operations are successful.
*/

class ContractCache(private val api: data.API, private val file: File = defaultCacheFile) {
  def loadEventClasses = if (refreshNeeded) {
    // get contract listing from intrade
    val response = api.activeContractListing
    // write intrade's xml response to disk
    writeToFile(response.raw)
    // return response
    response
  } else {
    // fetch intrade's xml response from disk
    val xmlString = readFromFile
    // convert to response and return
    Response.node2EventClassResponse(file.getAbsolutePath, xmlString)
  }

  private def refreshNeeded = cacheDoesNotExist || cacheIsStale

  private def cacheDoesNotExist = !file.exists()

  private def cacheIsStale = file.lastModified() < new Date().getTime - TimeUnit.MINUTES.toMillis(15)

  private def writeToFile(contents: String) {
    using(new FileWriter(file.getAbsolutePath, false)) {
      _.write(contents)
    }
  }

  private def readFromFile = {
    using(scala.io.Source.fromFile(file.getAbsolutePath)) {
      _.mkString
    }
  }
}

object ContractCache {
  private val defaultCacheFile = new File("./out/intrade_contract_cache.xml")

  private def using[A <: {def close()}, B](resource: A)(f: A => B) =
    try {
      f(resource)
    } finally {
      resource.close()
    }
}
