package mephi.oop.gui

import mephi.oop.IEntityTable

trait GenericElems[T] {

  lazy val name1: String = ???

  lazy val name2: String = ???

  lazy val rowData: Array[Array[String]] = ???

  lazy val columnNames: Seq[String] = ???

  lazy val entityTable: IEntityTable[T] = ???

  def save(x: String, y: String): Unit = ???


}
