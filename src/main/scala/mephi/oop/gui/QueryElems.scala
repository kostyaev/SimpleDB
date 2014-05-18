package mephi.oop.gui

import mephi.oop.models.{WardTable, Ward, Doctor, DoctorTable}
import mephi.oop.Queries
import mephi.oop.gui.table.SpecificTableModel


trait QueryElems extends GenericElems {

  lazy val model = table.model.asInstanceOf[SpecificTableModel]

  override def rowData: Array[Array[String]] = Array.empty[Array[String]]

  override lazy val columnNames: Seq[String] = Seq("ID", "№ палаты", "№ здания")

  override def nextId = DoctorTable.nextId

  def isCorrect(x: String): Boolean = !x.isEmpty

  def search(id: String) = DoctorTable.get(id.toInt) match {
    case Some(doctor) => show(Queries.getMostPopulatedWard(doctor))
    case None => show(None)
  }

  def show(thing: Option[Ward]) = thing match {
    case Some(ward) =>
      val id = WardTable.findByAttributes(ward.number, ward.building).get
      model.update(Array(Array(id.toString, ward.number.toString, ward.building.toString)))
    case None => model.update(Array.empty[Array[String]])
  }


}
