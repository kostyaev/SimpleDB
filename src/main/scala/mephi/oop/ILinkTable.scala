package mephi.oop

import scala.collection.mutable

trait ILinkTable[T <: ILink] {

  val storage: mutable.HashMap[Int, T] = new mutable.HashMap[Int, T]

  var nextId: Int = 1

  /**
   * Получение объекта-связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   * @return Объект связи
   */
  def getLink(sourceId: Int, targetId: Int): Option[T] = {
    val result:Iterable[T] = for((k,v) <- storage if v.getSourceId() == sourceId && v.getTargetId() == targetId) yield v
    result.toList match {
      case Nil => None
      case x::xs => Some(x)
    }
  }

  /**
   * Получение идентификатора источника
   * по идентификатору одного из
   * связанных с ним объектов
   *
   * @param targetId Идентификатор связанного объекта
   * @return Идентификатор источника
   */
  def getSourceId(targetId: Int): Option[Int] = {
    storage.find(e => e._2.getTargetId == targetId) match {
      case Some((k,v)) => Some(v.getSourceId())
      case _ => None
    }

  }

  /**
   * Получение списка идентификаторов объектов, связанных с указанным источником
   *
   * @param sourceId Идентификатор источника
   * @return Список идентификаторов
   */
  def getTargetIds(sourceId: Int): List[Int] =
    storage.filter(e => e._2.getSourceId() == sourceId).map(x => x._2.getTargetId()).toList

  /**
   * Удаление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  def deleteLink(sourceId: Int, targetId: Int): Unit = {
    storage.find(e => e._2.getSourceId() == sourceId && e._2.getTargetId() == targetId) match {
      case Some((k,v)) => storage.remove(k)
      case _ =>
    }
  }

  /**
   * Удаление связи
   *
   * @param id Идентификатор ссылки
   */
  def deleteLink(id: Int): Unit = {
    storage.find(e => e._1 == id) match {
      case Some((k,v)) => storage.remove(k)
      case _ =>
    }
  }

  /**
   * Добавление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  def addLink(sourceId: Int, targetId: Int): Unit

  /**
   * Добавление связи
   *
   * @param link Добавляемая связь
   */
  def addLink(link: T): Unit = {
    storage += nextId -> link
    nextId += 1
  }

  def clear():Unit = {
    storage.clear()
    nextId = 1
  }

  def load(id: Int, data: Map[Int, T]) = {
    storage.clear()
    storage ++= data
    nextId = id
  }
}
