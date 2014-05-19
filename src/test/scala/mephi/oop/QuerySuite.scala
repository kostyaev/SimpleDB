package mephi.oop

import org.scalatest.{Matchers, FlatSpec}
import mephi.oop.models._
import mephi.oop.models.Ward
import mephi.oop.models.Patient
import mephi.oop.models.DoctorPatients
import mephi.oop.models.Doctor
import mephi.oop.models.WardPatients

class QuerySuite extends FlatSpec with Matchers {
  "Query getMostPopulatedWard" should "return the most populated ward with the patients of some particular doctor" in {

    DoctorTable.clear()
    WardTable.clear()
    PatientTable.clear()

    DoctorPatientsTable.clear()
    WardPatientsTable.clear()

    val id1 = DoctorTable.add(Doctor("House", 54))
    val id2 = DoctorTable.add(Doctor("Dorian", 28))

    val wId1 = WardTable.add(Ward(1,1))
    val wId2 = WardTable.add(Ward(1,2))
    val wId3 = WardTable.add(Ward(3,2))


    val pId1 = PatientTable.add(Patient("Kate", 24))
    val pId2 = PatientTable.add(Patient("Ashley", 26))
    val pId3 = PatientTable.add(Patient("James", 43))
    val pId4 = PatientTable.add(Patient("John", 28))
    val pId5 = PatientTable.add(Patient("Maria", 28))
    val pId6 = PatientTable.add(Patient("Helen", 23))
    val pId7 = PatientTable.add(Patient("Max", 38))
    val pId8 = PatientTable.add(Patient("Haviland", 27))

    DoctorPatientsTable.addLink(DoctorPatients(id1,pId1))
    DoctorPatientsTable.addLink(DoctorPatients(id1,pId2))

    DoctorPatientsTable.addLink(DoctorPatients(id1,pId3))
    DoctorPatientsTable.addLink(DoctorPatients(id1,pId4))
    DoctorPatientsTable.addLink(DoctorPatients(id1,pId5))


    DoctorPatientsTable.addLink(DoctorPatients(id2,pId6))
    DoctorPatientsTable.addLink(DoctorPatients(id2,pId7))
    DoctorPatientsTable.addLink(DoctorPatients(id2,pId8))

    WardPatientsTable.addLink(WardPatients(wId1, pId1))
    WardPatientsTable.addLink(WardPatients(wId1, pId2))

    WardPatientsTable.addLink(WardPatients(wId2, pId3))
    WardPatientsTable.addLink(WardPatients(wId2, pId4))
    WardPatientsTable.addLink(WardPatients(wId2, pId5))

    WardPatientsTable.addLink(WardPatients(wId1, pId6))
    WardPatientsTable.addLink(WardPatients(wId3, pId7))
    WardPatientsTable.addLink(WardPatients(wId3, pId8))

    Queries.getMostPopulatedWard(Doctor("House", 54)) should be (Some(Ward(1,2)))
    Queries.getMostPopulatedWard(Doctor("Dorian", 28)) should be (Some(Ward(3,2)))

  }

}
