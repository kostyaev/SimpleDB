package mephi.oop

import org.scalatest.{FlatSpec, Matchers}
import mephi.oop.models._
import scala.Some
import mephi.oop.models.DoctorPatients


class LinkTableSuite extends FlatSpec with Matchers {

  "A DoctorPatientsTable" should "contain element after adding it" in {
    DoctorPatientsTable.clear()
    DoctorPatientsTable addLink DoctorPatients(1,1)
    DoctorPatientsTable getLink (1,1) should equal(Some(DoctorPatients(1,1)))

    DoctorPatientsTable addLink DoctorPatients(3,5)
    DoctorPatientsTable getLink (3,5) should equal(Some(DoctorPatients(3,5)))
    DoctorPatientsTable getLink (5,3) should be (None)

    DoctorPatientsTable addLink (1,2)
    DoctorPatientsTable getLink (1,2) should equal(Some(DoctorPatients(1,2)))

    DoctorPatientsTable addLink (6,3)
    DoctorPatientsTable getLink (6,3) should equal(Some(DoctorPatients(6,3)))

    DoctorPatientsTable getLink (3,6) should be (None)
  }


  "A DoctorPatientsTable" should "not contain element after removing it" in {
    DoctorPatientsTable.clear()
    DoctorPatientsTable addLink DoctorPatients(1,2)
    DoctorPatientsTable getLink(1,2) should not be None
    DoctorPatientsTable deleteLink(1,2)
    DoctorPatientsTable getLink(1,2) should be (None)

    DoctorPatientsTable addLink DoctorPatients(1,2)
    DoctorPatientsTable getLink (1,2) should not be None
    DoctorPatientsTable getLink (2,1) should be (None)
    DoctorPatientsTable deleteLink (2,1)
    DoctorPatientsTable getLink (1,2) should not be None

  }

  "A DoctorPatientsTable" should "return correct sourceId" in {
    DoctorPatientsTable.clear()
    DoctorPatientsTable addLink DoctorPatients(1,2)
    DoctorPatientsTable addLink DoctorPatients(1,5)
    DoctorPatientsTable addLink DoctorPatients(1,3)
    DoctorPatientsTable addLink DoctorPatients(2,4)

    DoctorPatientsTable.getSourceId(5) should be (Some(1))
    DoctorPatientsTable.getSourceId(3) should be (Some(1))
    DoctorPatientsTable.getSourceId(4) should be (Some(2))
    DoctorPatientsTable.getSourceId(6) should be (None)


  }

  "A DoctorPatientsTable" should "return correct target ids" in {
    DoctorPatientsTable.clear()
    DoctorPatientsTable addLink DoctorPatients(1,2)
    DoctorPatientsTable addLink DoctorPatients(1,5)
    DoctorPatientsTable addLink DoctorPatients(1,3)
    DoctorPatientsTable addLink DoctorPatients(2,4)
    DoctorPatientsTable addLink DoctorPatients(2,7)

    DoctorPatientsTable.getTargetIds(1) should contain only (2,5,3)
    DoctorPatientsTable.getTargetIds(2) should contain only (4,7)
    DoctorPatientsTable.getTargetIds(3) should be (List())

  }

  "A WardPatientsTable" should "contain element after adding it" in {
    WardPatientsTable.clear()
    WardPatientsTable addLink WardPatients(1,1)
    WardPatientsTable getLink (1,1) should equal(Some(WardPatients(1,1)))

    WardPatientsTable addLink WardPatients(3,5)
    WardPatientsTable getLink (3,5) should equal(Some(WardPatients(3,5)))
    WardPatientsTable getLink (5,3) should be (None)

    WardPatientsTable addLink (1,2)
    WardPatientsTable getLink (1,2) should equal(Some(WardPatients(1,2)))

    WardPatientsTable addLink (6,3)
    WardPatientsTable getLink (6,3) should equal(Some(WardPatients(6,3)))
    WardPatientsTable getLink (3,6) should be (None)
  }


  "A WardPatientsTable" should "not contain element after removing it" in {
    WardPatientsTable.clear()
    WardPatientsTable addLink WardPatients(1,2)
    WardPatientsTable getLink(1,2) should not be None
    WardPatientsTable deleteLink(1,2)
    WardPatientsTable getLink(1,2) should be (None)

    WardPatientsTable addLink WardPatients(1,2)
    WardPatientsTable getLink (1,2) should not be None
    WardPatientsTable getLink (2,1) should be (None)
    WardPatientsTable deleteLink (2,1)
    WardPatientsTable getLink (1,2) should not be None

  }

  "A WardPatientsTable" should "return correct sourceId" in {
    WardPatientsTable.clear()
    WardPatientsTable addLink WardPatients(1,2)
    WardPatientsTable addLink WardPatients(1,5)
    WardPatientsTable addLink WardPatients(1,3)
    WardPatientsTable addLink WardPatients(2,4)

    WardPatientsTable.getSourceId(5) should be (Some(1))
    WardPatientsTable.getSourceId(3) should be (Some(1))
    WardPatientsTable.getSourceId(4) should be (Some(2))
    WardPatientsTable.getSourceId(6) should be (None)

  }

  "A WardPatientsTable" should "return correct target ids" in {
    WardPatientsTable.clear()
    WardPatientsTable addLink WardPatients(1,2)
    WardPatientsTable addLink WardPatients(1,5)
    WardPatientsTable addLink WardPatients(1,3)
    WardPatientsTable addLink WardPatients(2,4)
    WardPatientsTable addLink WardPatients(2,7)

    WardPatientsTable.getTargetIds(1) should contain only (2,5,3)
    WardPatientsTable.getTargetIds(2) should contain only (4,7)
    WardPatientsTable.getTargetIds(3) should be (List())

  }


}
