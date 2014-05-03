package mephi.oop.models

import mephi.oop.ILink

case class Link(source: Int, target: Int) extends ILink{

  def getTargetId(): Int = ???

  def getSourceId(): Int = ???
}
