package mephi.oop.gui

import mephi.oop.models._
import mephi.oop.models.Ward


trait WardPatientsElems extends GenericElems {

  override lazy val name1: String = "Номер палаты"

  override lazy val name2: String = "Номер строения"

  override lazy val rowData: Array[Array[String]] =
    WardTable.storage.toArray.map(x => Array(x._1.toString, x._2.number.toString, x._2.building.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "№ палаты", "№ здания")

  override protected def save(x: String, y: String) = WardPatientsTable.addLink(x.toInt,y.toInt)

  override protected def delete(id: Int) = WardPatientsTable.deleteLink(id)

  override def isCorrect(x: String, y: String): Boolean = !x.isEmpty && !y.isEmpty

}
