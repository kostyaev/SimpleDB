package mephi.oop

import org.scalatest.{Matchers, FlatSpec}
import mephi.oop.models._
import mephi.oop.models.Patient
import scala.Some
import mephi.oop.models.Doctor

class EntityTableSuite extends FlatSpec with Matchers {

  "EntityTable" should "return correct id" in {
    DoctorTable.clear()
    PatientTable add Patient("name1", 33) should be (1)
    DoctorTable add Doctor("name1", 33) should be (1)
    WardTable add Ward(10, 20) should be (1)

  }

  "An EntityTable" should "contain element after adding an element" in {
    DoctorTable.clear()
    val doctorId = DoctorTable add Doctor("name1", 33)
    (DoctorTable get doctorId) should equal (Some(Doctor("name1", 33)))

  }

  "An EntityTable" should "not contain element after removing it" in {
    DoctorTable.clear()
    val doctorId = DoctorTable add Doctor("name1", 33)
    DoctorTable.delete(doctorId)
    (DoctorTable get doctorId) should be (None)

  }

  "An EntityTable" should "contain at least 2 elements after inserting them" in {
    DoctorTable.clear()
    val doctorId = DoctorTable add Doctor("name1", 33)
    val doctorId2 = DoctorTable add Doctor("name2", 34)
    (DoctorTable get doctorId) should equal (Some(Doctor("name1", 33)))
    (DoctorTable get doctorId2) should equal (Some(Doctor("name2", 34)))

  }

  "An EntityTable" should "return None when trying to get a not added yet element" in {
    DoctorTable.clear()
    val doctorId = DoctorTable add Doctor("name1", 33)
    (DoctorTable get (doctorId + 1)) should be (None)

  }

  "DoctorTable" should "find existing element by attributes" in {
    DoctorTable.clear()
    DoctorTable add Doctor("name1", 33)
    val id2 = DoctorTable add Doctor("name2", 30)
    val id3 = DoctorTable add Doctor("name3", 34)
    DoctorTable findByAttributes ("name2", 30) should be (Some(id2))
    DoctorTable findByAttributes ("name3", 34) should be (Some(id3))
    DoctorTable findByAttributes ("name1", 33) should contain noneOf (id2, id3)
  }

  "PatientTable" should "find existing element by attributes" in {
    PatientTable.clear()
    PatientTable add Patient("name1", 33)
    val id2 = PatientTable add Patient("name2", 30)
    val id3 = PatientTable add Patient("name3", 34)
    PatientTable findByAttributes ("name2", 30) should be (Some(id2))
    PatientTable findByAttributes ("name3", 34) should be (Some(id3))
    PatientTable findByAttributes ("name1", 33) should contain noneOf (id2, id3)
  }

  "WardTable" should "find existing element by attributes" in {
    WardTable.clear()
    WardTable add Ward(10, 33)
    val id2 = WardTable add Ward(11, 30)
    val id3 = WardTable add Ward(12, 34)
    WardTable findByAttributes (11, 30) should be (Some(id2))
    WardTable findByAttributes (12, 34) should be (Some(id3))
    WardTable findByAttributes (10, 33) should contain noneOf (id2, id3)
  }


}
	