package mephi.oop.gui

import mephi.oop.models.{Patient, PatientTable}
import mephi.oop.IEntityTable


trait PatientsElems extends GenericElems[Patient] {

  override lazy val name1: String = "ФИО"

  override lazy val name2: String = "Возраст"

  override lazy val rowData: Array[Array[String]] =
    PatientTable.storage.toArray.map(x => Array(x._1.toString, x._2.fio.toString, x._2.age.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "ФИО", "Возраст")

  override lazy val entityTable: IEntityTable[Patient] = PatientTable

  override def save(x: String, y: String): Unit = PatientTable.add(Patient(x, y.toInt))

}
