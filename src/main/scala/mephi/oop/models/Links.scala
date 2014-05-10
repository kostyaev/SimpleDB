package mephi.oop.models

import mephi.oop.ILink

case class DoctorPatients(source: Int, target: Int) extends ILink{

  def getTargetId(): Int = target

  def getSourceId(): Int = source
}

case class WardPatients(source: Int, target: Int) extends ILink{

  def getTargetId(): Int = target

  def getSourceId(): Int = source
}

