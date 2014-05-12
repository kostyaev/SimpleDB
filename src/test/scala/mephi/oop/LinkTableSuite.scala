package mephi.oop

import org.scalatest.{FlatSpec, Matchers}
import mephi.oop.models._
import scala.Some
import mephi.oop.models.DoctorPatients


class LinkTableSuite extends FlatSpec with Matchers {

  "A DoctorPatientsTable" should "contain element after adding it" in {
    val doctorPatientsTable = new DoctorPatientsTable
    doctorPatientsTable addLink DoctorPatients(1,1)
    doctorPatientsTable getLink (1,1) should equal(Some(DoctorPatients(1,1)))

    doctorPatientsTable addLink DoctorPatients(3,5)
    doctorPatientsTable getLink (3,5) should equal(Some(DoctorPatients(3,5)))
    doctorPatientsTable getLink (5,3) should be (None)

    doctorPatientsTable addLink (1,2)
    doctorPatientsTable getLink (1,2) should equal(Some(DoctorPatients(1,2)))

    doctorPatientsTable addLink (6,3)
    doctorPatientsTable getLink (6,3) should equal(Some(DoctorPatients(6,3)))

    doctorPatientsTable getLink (3,6) should be (None)
  }


  "A DoctorPatientsTable" should "not contain element after removing it" in {
    val doctorPatientsTable = new DoctorPatientsTable
    doctorPatientsTable addLink DoctorPatients(1,2)
    doctorPatientsTable getLink(1,2) should not be None
    doctorPatientsTable deleteLink(1,2)
    doctorPatientsTable getLink(1,2) should be (None)

    doctorPatientsTable addLink DoctorPatients(1,2)
    doctorPatientsTable getLink (1,2) should not be None
    doctorPatientsTable getLink (2,1) should be (None)
    doctorPatientsTable deleteLink (2,1)
    doctorPatientsTable getLink (1,2) should not be None

  }

  "A DoctorPatientsTable" should "return correct sourceId" in {
    val doctorPatientsTable = new DoctorPatientsTable
    doctorPatientsTable addLink DoctorPatients(1,2)
    doctorPatientsTable addLink DoctorPatients(1,5)
    doctorPatientsTable addLink DoctorPatients(1,3)
    doctorPatientsTable addLink DoctorPatients(2,4)

    doctorPatientsTable.getSourceId(5) should be (Some(1))
    doctorPatientsTable.getSourceId(3) should be (Some(1))
    doctorPatientsTable.getSourceId(4) should be (Some(2))
    doctorPatientsTable.getSourceId(6) should be (None)


  }

  "A DoctorPatientsTable" should "return correct target ids" in {
    val doctorPatientsTable = new DoctorPatientsTable 
    doctorPatientsTable addLink DoctorPatients(1,2)
    doctorPatientsTable addLink DoctorPatients(1,5)
    doctorPatientsTable addLink DoctorPatients(1,3)
    doctorPatientsTable addLink DoctorPatients(2,4)
    doctorPatientsTable addLink DoctorPatients(2,7)

    doctorPatientsTable.getTargetIds(1) should contain only (2,5,3)
    doctorPatientsTable.getTargetIds(2) should contain only (4,7)
    doctorPatientsTable.getTargetIds(3) should be (List())

  }

  "A WardPatientsTable" should "contain element after adding it" in {
    val wardPatientsTable = new WardPatientsTable
    wardPatientsTable addLink WardPatients(1,1)
    wardPatientsTable getLink (1,1) should equal(Some(WardPatients(1,1)))

    wardPatientsTable addLink WardPatients(3,5)
    wardPatientsTable getLink (3,5) should equal(Some(WardPatients(3,5)))
    wardPatientsTable getLink (5,3) should be (None)

    wardPatientsTable addLink (1,2)
    wardPatientsTable getLink (1,2) should equal(Some(WardPatients(1,2)))

    wardPatientsTable addLink (6,3)
    wardPatientsTable getLink (6,3) should equal(Some(WardPatients(6,3)))
    wardPatientsTable getLink (3,6) should be (None)
  }


  "A WardPatientsTable" should "not contain element after removing it" in {
    val wardPatientsTable = new WardPatientsTable
    wardPatientsTable addLink WardPatients(1,2)
    wardPatientsTable getLink(1,2) should not be None
    wardPatientsTable deleteLink(1,2)
    wardPatientsTable getLink(1,2) should be (None)

    wardPatientsTable addLink WardPatients(1,2)
    wardPatientsTable getLink (1,2) should not be None
    wardPatientsTable getLink (2,1) should be (None)
    wardPatientsTable deleteLink (2,1)
    wardPatientsTable getLink (1,2) should not be None

  }

  "A WardPatientsTable" should "return correct sourceId" in {
    val wardPatientsTable = new WardPatientsTable
    wardPatientsTable addLink WardPatients(1,2)
    wardPatientsTable addLink WardPatients(1,5)
    wardPatientsTable addLink WardPatients(1,3)
    wardPatientsTable addLink WardPatients(2,4)

    wardPatientsTable.getSourceId(5) should be (Some(1))
    wardPatientsTable.getSourceId(3) should be (Some(1))
    wardPatientsTable.getSourceId(4) should be (Some(2))
    wardPatientsTable.getSourceId(6) should be (None)

  }

  "A WardPatientsTable" should "return correct target ids" in {
    val wardPatientsTable = new WardPatientsTable
    wardPatientsTable addLink WardPatients(1,2)
    wardPatientsTable addLink WardPatients(1,5)
    wardPatientsTable addLink WardPatients(1,3)
    wardPatientsTable addLink WardPatients(2,4)
    wardPatientsTable addLink WardPatients(2,7)

    wardPatientsTable.getTargetIds(1) should contain only (2,5,3)
    wardPatientsTable.getTargetIds(2) should contain only (4,7)
    wardPatientsTable.getTargetIds(3) should be (List())

  }




}
