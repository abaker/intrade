package org.intrade.util

import org.intrade._
import data.Response
import _root_.java.io.File

/*
From the Intrade API Documentation:
"As [the contract listing] is a large file, [it] should only be retrieved at start-up and not more than one time per 15 minutes."

The purpose of this class is to store contracts on disk so we may reuse them in other samples
without violating the policy mentioned above. This is not a robust implementation as it assumes
all file operations are successful.
*/

class ContractCache(private val api: data.API,
                    private val cache: FileCache) {

  def this(api: data.API,
            path: String = "./out/intrade_contracts.xml") = this(api, new FileCache(15, new File(path)))

  def loadEventClasses = if (cache.refreshNeeded) {
    // get contract listing from intrade
    val response = api.activeContractListing
    // write intrade's xml response to disk
    cache.writeToCache(response.raw)
    // return response
    response
  } else {
    // fetch intrade's xml response from disk
    val xmlString = cache.readFromCache
    // convert to response and return
    Response.node2EventClassResponse(cache.path, xmlString)
  }
}