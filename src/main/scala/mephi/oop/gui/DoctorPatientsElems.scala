package mephi.oop.gui

import mephi.oop.models._
import scala.util.{Success, Failure, Try}


trait DoctorPatientsElems extends GenericElems {

  override lazy val name1: String = "ID доктора"

  override lazy val name2: String = "ID пациента"

  override def rowData: Array[Array[String]] =
    DoctorPatientsTable.storage.toArray.map(x => Array(x._1.toString, x._2.source.toString, x._2.target.toString))

  override lazy val columnNames: Seq[String] = Seq("ID", "ID доктора", "ID пациента")

  override def nextId = DoctorPatientsTable.nextId

  override protected def save(x: String, y: String) = DoctorPatientsTable.addLink(x.toInt,y.toInt)

  override protected def delete(id: Int) = DoctorPatientsTable.deleteLink(id)

  def checkConstraint(x: String, y: String): Boolean = Try {
    val doctor = DoctorTable.get(x.toInt)
    val patient = PatientTable.get(y.toInt)
    (doctor, patient)
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
