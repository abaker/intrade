package org.intrade

trait Response[+A] {
  def request: String

  def raw: String

  def payload: A
}