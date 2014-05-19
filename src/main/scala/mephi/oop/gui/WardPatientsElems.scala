package mephi.oop.gui

import mephi.oop.models._
import scala.util.{Success, Failure, Try}


trait WardPatientsElems extends GenericElems {

  override lazy val name1: String = "ID палаты"

  override lazy val name2: String = "ID пациента"

  override def rowData: Array[Array[String]] =
    WardPatientsTable.storage.toArray.map(x => Array(x._1.toString, x._2.source.toString, x._2.target.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "ID палаты", "ID пациента")

  override def nextId = WardPatientsTable.nextId

  override protected def save(x: String, y: String) = WardPatientsTable.addLink(x.toInt,y.toInt)

  override protected def delete(id: Int) = WardPatientsTable.deleteLink(id)

  def checkConstraint(x: String, y: String): Boolean = Try {
    val ward = WardTable.get(x.toInt)
    val patient = PatientTable.get(y.toInt)
    (ward, patient)
  } match {
    case Failure(e) =>
      output.append(e.toString)
      false
    case Success((None, _)) =>
      output.text = ""
      output.append(s"id = $x not found in doctors table\n")
      false
    case Success((_, None)) =>
      output.text = ""
      output.append(s"id = $y not found in patients table\n")
      false
    case _ =>
      output.text = ""
      true
  }

  override def isCorrect(x: String, y: String): Boolean = {
    if (!x.isEmpty && !y.isEmpty) checkConstraint(x,y)
    else false

  }
}
