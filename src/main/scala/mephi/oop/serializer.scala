package mephi.oop

import argonaut._, Argonaut._
import scala.collection.mutable
import mephi.oop.models._
import mephi.oop.models.Ward
import mephi.oop.models.Patient
import mephi.oop.models.Doctor



object serializer extends App {
  case class Person(name: String, age: Int, things: Map[String, Map[Int,String]])


  case class DataContainer(doctorId: Int, doctorTable: Map[Int, Map[Int, Doctor]],
                           patientId: Int, patientTable: Map[Int, Map[Int, Patient]],
                           wardId: Int, wardTable: Map[Int, Map[Int, Ward]])
//
//  implicit def PersonCodecJson =
//    casecodec3(Person.apply, Person.unapply)("name", "age", "things")
//
//  implicit def DataContainerCodecJson =
//    casecodec6(DataContainer.apply, DataContainer.unapply)("doctorId", "doctorTable", "patientId", "patientTable","wardId", "wardTable")

  implicit def MutableMapToImmutable[T](x: Map[Int,T]) = collection.mutable.Map(x.toSeq: _*)

  implicit def ImmutableMapToImmutable[T](x:mutable.Map[Int, T]) = x.toMap

  val doctor = Doctor("fio", 21)
  val patient = Patient("fio", 22)
  val ward = Ward(1,1)

  val doctorTable = new DoctorTable
  val patientTable = new PatientTable
  val wardTable = new WardTable

  doctorTable.add(doctor)
  patientTable.add(patient)
  wardTable.add(ward)


//
//  val names:mutable.Map[String, String] = new mutable.HashMap[String,String]()
//  names += ("Marla" -> "Stinson")
//
//  names += ("Jane" -> "Watson")
//
//  val map: Map[String,String] = names.toMap
//  val names2 = collection.mutable.Map(map.toSeq: _*)
//
//  println(Person("John", 21, names2).asJson)

}

