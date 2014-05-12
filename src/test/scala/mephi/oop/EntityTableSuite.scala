package mephi.oop

import org.scalatest.{Matchers, FlatSpec}
import mephi.oop.models.{Doctor, DoctorTable}

class EntityTableSuite extends FlatSpec with Matchers {
  "An EntityTable" should "contain element after adding an element" in {
    val doctorTable = new DoctorTable
    val doctorId = doctorTable add Doctor("name1", 33)
    (doctorTable get doctorId) should equal (Some(Doctor("name1", 33)))

  }

  "An EntityTable" should "not contain element after removing it" in {
    val doctorTable = new DoctorTable
    val doctorId = doctorTable add(Doctor("name1", 33))
    doctorTable.delete(doctorId)
    (doctorTable get doctorId) should be (None)

  }

  "An EntityTable" should "contain at least 2 elements after inserting them" in {
    val doctorTable = new DoctorTable
    val doctorId = doctorTable add Doctor("name1", 33)
    val doctorId2 = doctorTable add Doctor("name2", 34)
    (doctorTable get doctorId) should equal (Some(Doctor("name1", 33)))
    (doctorTable get doctorId2) should equal (Some(Doctor("name2", 34)))

  }

  "An EntityTable" should "return None when trying to get a not added yet element" in {
    val doctorTable = new DoctorTable
    val doctorId = doctorTable add Doctor("name1", 33)
    (doctorTable get (doctorId + 1)) should be (None)


  }
  

}
	