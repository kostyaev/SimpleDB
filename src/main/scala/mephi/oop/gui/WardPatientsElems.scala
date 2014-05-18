package mephi.oop.gui

import mephi.oop.models._
import mephi.oop.models.Ward


trait WardPatientsElems extends GenericElems {

  override lazy val name1: String = "ID палаты"

  override lazy val name2: String = "ID пациента"

  override def rowData: Array[Array[String]] =
    WardPatientsTable.storage.toArray.map(x => Array(x._1.toString, x._2.source.toString, x._2.target.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "№ палаты", "№ здания")

  override def nextId = WardPatientsTable.nextId

  override protected def save(x: String, y: String) = WardPatientsTable.addLink(x.toInt,y.toInt)

  override protected def delete(id: Int) = WardPatientsTable.deleteLink(id)

  override def isCorrect(x: String, y: String): Boolean = !x.isEmpty && !y.isEmpty

}
