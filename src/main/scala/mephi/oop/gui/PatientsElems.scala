package mephi.oop.gui

import mephi.oop.models.{Patient, PatientTable}


trait PatientsElems extends GenericElems {

  override lazy val name1: String = "ФИО"

  override lazy val name2: String = "Возраст"

  override lazy val rowData: Array[Array[String]] =
    PatientTable.storage.toArray.map(x => Array(x._1.toString, x._2.fio.toString, x._2.age.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "ФИО", "Возраст")

  override protected def save(x: String, y: String): Unit = PatientTable.add(Patient(x,y.toInt))

  override protected def delete(id: Int) = PatientTable.delete(id)

  override def isCorrect(x: String, y: String): Boolean = !x.isEmpty && !y.isEmpty

}
