package mephi.oop.gui

import mephi.oop.models.{Doctor, DoctorTable}


trait DoctorElems extends GenericElems {

  override lazy val name1: String = "ФИО"

  override lazy val name2: String = "Возраст"

  override lazy val rowData: Array[Array[String]] =
    DoctorTable.storage.map(x => Array(x._1.toString, x._2.fio.toString, x._2.age.toString)).toArray

  override lazy val columnNames: Seq[String] = Seq("ID", "ФИО", "Возраст")

  override def nextId = DoctorTable.nextId

  override protected def save(x: String, y: String): Unit = DoctorTable.add(Doctor(x,y.toInt))

  override protected def delete(id: Int): Unit = DoctorTable.delete(id)

  override def isCorrect(x: String, y: String): Boolean = !x.isEmpty && !y.isEmpty



}
