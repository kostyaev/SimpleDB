package mephi.oop.gui

import mephi.oop.models.{DoctorTable, Ward, WardTable}


trait WardElems extends GenericElems {

  override lazy val name1: String = "Номер палаты"

  override lazy val name2: String = "Номер строения"

  override def rowData: Array[Array[String]] =
    WardTable.storage.toArray.map(x => Array(x._1.toString, x._2.number.toString, x._2.building.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "№ палаты", "№ здания")

  override def nextId = WardTable.nextId

  override protected def save(x: String, y: String) = WardTable.add(Ward(x.toInt,y.toInt))

  override protected def delete(id: Int) = WardTable.delete(id)

  override def isCorrect(x: String, y: String): Boolean = !x.isEmpty && !y.isEmpty

}
