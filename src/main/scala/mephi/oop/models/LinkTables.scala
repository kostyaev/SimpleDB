package mephi.oop.models

import mephi.oop.{ILinkTable}
import scala.collection.mutable

object DoctorPatientsTable extends ILinkTable[DoctorPatients]{

  /**
   * Добавление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  override def addLink(sourceId: Int, targetId: Int): Unit = {
    storage += nextId -> DoctorPatients(sourceId, targetId)
    nextId += 1

  }
}


object WardPatientsTable extends ILinkTable[WardPatients] {

  /**
   * Добавление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  override def addLink(sourceId: Int, targetId: Int): Unit =  {
    storage += nextId -> WardPatients(sourceId, targetId)
    nextId += 1
  }

}

