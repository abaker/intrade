package org.intrade

trait Response[+A] {
  def request: String

  def response: String

  def payload: A
}