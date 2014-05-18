package mephi.oop

import mephi.oop.models._
import scala.Some
import mephi.oop.models.Doctor

object Queries {

  /**
    * Создать метод, вычисляющий,
    * в какой палате больше всего пациентов указанного врача.
    */

  def getAllWardsOfDoctor(d: Doctor) = DoctorTable.findByAttributes(d.fio, d.age) match {
    case Some(id) => DoctorPatientsTable.getTargetIds(id).flatMap(x => WardPatientsTable.getSourceId(x))
    case None => List()
  }

  def getMostPopulatedWard(d: Doctor): Option[Ward] =
    getAllWardsOfDoctor(d) match {
     case List() => None
     case nonEmptyList => WardTable.get(nonEmptyList.groupBy(x => x).map(t => (t._1, t._2.size)).maxBy(e => e._2)._1)
    }

}
