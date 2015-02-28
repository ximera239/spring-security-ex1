package com.elance.nephrite.pts.util

import org.slf4j.LoggerFactory

/**
 * Created by Evgeny Zhoga on 27.02.15.
 */
trait Logger {
  val log = LoggerFactory.getLogger(getClass)

  def info(msg: String) = println(msg)

  def warn(msg: String) = log.warn(msg)

  def error(msg: String) = log.error(msg)
}
