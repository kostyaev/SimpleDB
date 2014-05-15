package mephi.oop.gui

import mephi.oop.models.{Doctor, DoctorTable}
import mephi.oop.IEntityTable


trait DoctorElems extends GenericElems[Doctor] {

  override lazy val name1: String = "ФИО"

  override lazy val name2: String = "Возраст"

  override lazy val rowData: Array[Array[String]] =
    DoctorTable.storage.toArray.map(x => Array(x._1.toString, x._2.fio.toString, x._2.age.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "ФИО", "Возраст")

  override lazy val entityTable: IEntityTable[Doctor] = DoctorTable

  override def save(x: String, y: String): Unit = DoctorTable.add(Doctor(x, y.toInt))



}
