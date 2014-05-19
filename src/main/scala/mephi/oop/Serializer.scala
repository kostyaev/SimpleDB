package mephi.oop

import scala.collection.mutable
import mephi.oop.models._
import mephi.oop.models.Ward
import mephi.oop.models.Patient
import mephi.oop.models.Doctor
import java.io.{File, PrintWriter}

object Serializer extends App {

  case class DataContainer(nextDoctorId: Int, doctorTable: Map[String, Doctor],
                           nextPatientId: Int, patientTable: Map[String, Patient],
                           nextWardId: Int, wardTable: Map[String,Ward],
                           nextDocPatientsId:Int, doctorPatientsTable: Map[String, DoctorPatients],
                           nextWardPatientsId:Int, wardPatientsTable: Map[String, WardPatients])

  implicit def ImmutableMapToMutable[T](x: Map[Int,T]):mutable.Map[Int,T] = collection.mutable.Map(x.toSeq: _*)

  implicit def MutableMapToImmutable[T](x:mutable.Map[Int, T]): Map[String, T] = x.toMap.map(e => (e._1.toString, e._2))

  implicit def IntMapToStringMap[T](x: Map[String, T]): Map[Int, T]  = x.toMap.map(e => (e._1.toInt, e._2))

  def getDB = DataContainer(DoctorTable.nextId, DoctorTable.storage,
                            PatientTable.nextId, PatientTable.storage,
                            WardTable.nextId, WardTable.storage,
                            DoctorPatientsTable.nextId, DoctorPatientsTable.storage,
                            WardPatientsTable.nextId, WardPatientsTable.storage)

  import spray.json._

  object MyJsonProtocol extends DefaultJsonProtocol {

    implicit val patientFormat = jsonFormat2(Patient)

    implicit val doctorFormat = jsonFormat2(Doctor)

    implicit val wardFormat = jsonFormat2(Ward)

    implicit val doctorPatientsFormat = jsonFormat2(DoctorPatients)

    implicit val wardPatientFormat = jsonFormat2(WardPatients)

    implicit val dataContainerFormat = jsonFormat10(DataContainer)
  }

  import MyJsonProtocol._
  def save(file: File): Unit = {
    val out = new PrintWriter(file)
    out.print(getDB.toJson.prettyPrint)
    out.flush()
    out.close()

  }

  def load(file: File): Unit = {
    val source = scala.io.Source.fromFile(file)
    val content = source.mkString
    source.close()
    val data: DataContainer = JsonParser(content).convertTo[DataContainer]
    DoctorTable.load(data.nextDoctorId, data.doctorTable)
    PatientTable.load(data.nextPatientId, data.patientTable)
    WardTable.load(data.nextWardId, data.wardTable)
    DoctorPatientsTable.load(data.nextDocPatientsId, data.doctorPatientsTable)
    WardPatientsTable.load(data.nextWardPatientsId, data.wardPatientsTable)

  }

}

