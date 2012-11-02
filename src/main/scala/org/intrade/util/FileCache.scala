package org.intrade.util

import java.io.{FileWriter, File}
import java.util.Date
import java.util.concurrent.TimeUnit
import FileCache.using

class FileCache(minutesValid: Int, file: File) {
  def path = file.getAbsolutePath

  def refreshNeeded = fileDoesNotExist || fileIsStale

  def writeToCache(contents: String) {
    using(new FileWriter(file.getAbsolutePath, false)) {
      _.write(contents)
    }
  }

  def readFromCache = {
    using(scala.io.Source.fromFile(file.getAbsolutePath)) {
      _.mkString
    }
  }

  private def fileDoesNotExist = !file.exists()

  private def fileIsStale = file.lastModified() < new Date().getTime - TimeUnit.MINUTES.toMillis(minutesValid)
}

object FileCache {
  private def using[A <: {def close()}, B](resource: A)(f: A => B) =
    try {
      f(resource)
    } finally {
      resource.close()
    }
}
