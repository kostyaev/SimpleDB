package mephi.oop

import argonaut._, Argonaut._
import scala.collection.mutable
import mephi.oop.models._
import mephi.oop.models.Ward
import mephi.oop.models.Patient
import mephi.oop.models.Doctor
import spray.json.DefaultJsonProtocol


object serializer extends App {

  case class Person(name: String, age: Int, things: Map[String, Map[Int,String]])

  case class DataContainer(doctorId: Int, doctorTable: Map[String, Doctor],
                           patientId: Int, patientTable: Map[String, Patient],
                           wardId: Int, wardTable: Map[String,Ward],
                           doctorPatientsTable: Map[String,Map[String, DoctorPatients]],
                           wardPatientsTable: Map[String, Map[String, WardPatients]])

  implicit def ImmutableMapToMutable[T](x: Map[Int,T]):mutable.Map[Int,T] = collection.mutable.Map(x.toSeq: _*)

  implicit def MutableMapToImmutable[T](x:mutable.Map[Int, T]) = x.toMap.map(p => (p._1.toString, p._2))

  implicit def MutableMapToImmutable[T](x:mutable.HashMap[Int, mutable.HashMap[Int,T]]):Map[String,Map[String,T]] =
    x.toMap.map(p => (p._1.toString, MutableMapToImmutable(p._2)))

  val doctor = Doctor("fio", 21)
  val patient = Patient("fio", 22)
  val ward = Ward(1,1)

  val doctorTable = new DoctorTable
  val patientTable = new PatientTable
  val wardTable = new WardTable

  val doctorPatient = DoctorPatients(0,0)
  val wardPatient = WardPatients(0,0)

  val doctorPatientsTable = new DoctorPatientsTable
  val wardPatientsTable = new WardPatientsTable

  doctorPatientsTable.addLink(doctorPatient)
  wardPatientsTable.addLink(wardPatient)

  doctorTable.add(doctor)
  patientTable.add(patient)
  wardTable.add(ward)

  val data = DataContainer(1,doctorTable.storage,1,patientTable.storage,1,wardTable.storage, doctorPatientsTable.storage, wardPatientsTable.storage)

  import spray.json._

  case class Color(name: String, red: Int, green: Int, blue: Int)

  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val colorFormat = jsonFormat4(Color)

    implicit val patientFormat = jsonFormat2(Patient)

    implicit val doctorFormat = jsonFormat2(Doctor)

    implicit val wardFormat = jsonFormat2(Ward)

    implicit val doctorPatientsFormat = jsonFormat2(DoctorPatients)

    implicit val wardPatientFormat = jsonFormat2(WardPatients)

    implicit val dataContainerFormat = jsonFormat8(DataContainer)
  }

  import MyJsonProtocol._

  val json = data.toJson
  val fromJson = json.convertTo[DataContainer]

  println(json)

}

