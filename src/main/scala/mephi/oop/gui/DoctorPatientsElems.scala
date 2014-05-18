package mephi.oop.gui

import mephi.oop.models._
import mephi.oop.models.Ward


trait DoctorPatientsElems extends GenericElems {

  override lazy val name1: String = "ID доктора"

  override lazy val name2: String = "ID пациента"

  override def rowData: Array[Array[String]] =
    DoctorPatientsTable.storage.toArray.map(x => Array(x._1.toString, x._2.source.toString, x._2.target.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "№ палаты", "№ здания")

  override def nextId = DoctorPatientsTable.nextId

  override protected def save(x: String, y: String) = DoctorPatientsTable.addLink(x.toInt,y.toInt)

  override protected def delete(id: Int) = DoctorPatientsTable.deleteLink(id)

  override def isCorrect(x: String, y: String): Boolean = !x.isEmpty && !y.isEmpty

}