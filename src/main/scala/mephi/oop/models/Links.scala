package mephi.oop.models

import mephi.oop.ILink

case class DoctorPatients(source: Int, target: Int) extends ILink{

  def getTargetId(): Int = ???

  def getSourceId(): Int = ???
}

case class WardPatients(source: Int, target: Int) extends ILink{

  def getTargetId(): Int = ???

  def getSourceId(): Int = ???
}

