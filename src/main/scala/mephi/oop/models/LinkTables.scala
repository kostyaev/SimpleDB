package mephi.oop.models

import mephi.oop.{ILinkTable}
import scala.collection.mutable

class DoctorPatientsTable extends ILinkTable[DoctorPatients]{
  /**
   * Добавление связи
   *
   * @param link Добавляемая связь
   */
  override def addLink(link: DoctorPatients): Unit = storage.get(link.source) match {
    case Some(parent: mutable.HashMap[Int, DoctorPatients]) => parent += (link.target -> link)
    case _ =>
      val entry = new mutable.HashMap[Int, DoctorPatients]()
      entry += link.target -> link
      storage += link.source -> entry
  }

  /**
   * Добавление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  override def addLink(sourceId: Int, targetId: Int): Unit = storage.get(sourceId) match {
    case Some(parent: mutable.HashMap[Int, DoctorPatients]) => parent += (targetId -> DoctorPatients(sourceId, targetId))
    case _ =>
      val entry = new mutable.HashMap[Int, DoctorPatients]()
      entry += targetId -> DoctorPatients(sourceId, targetId)
      storage += sourceId -> entry
  }
}


class WardPatientsTable extends ILinkTable[WardPatients] {
  /**
   * Добавление связи
   *
   * @param link Добавляемая связь
   */
  override def addLink(link: WardPatients): Unit = storage.get(link.source) match {
    case Some(parent: mutable.HashMap[Int, WardPatients]) => parent += (link.target -> link)
    case _ =>
      val entry = new mutable.HashMap[Int, WardPatients]()
      entry += link.target -> link
      storage += link.source -> entry
  }

  /**
   * Добавление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  override def addLink(sourceId: Int, targetId: Int): Unit = storage.get(sourceId) match {
    case Some(parent: mutable.HashMap[Int, WardPatients]) => parent += (targetId -> WardPatients(sourceId, targetId))
    case _ =>
      val entry = new mutable.HashMap[Int, WardPatients]()
      entry += targetId -> WardPatients(sourceId, targetId)
      storage += sourceId -> entry
  }

}

