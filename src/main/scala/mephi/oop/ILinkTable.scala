package mephi.oop

import scala.collection.mutable

trait ILinkTable[T <: ILink] {

  val storage: mutable.HashMap[Int, mutable.HashMap[Int,T]] = new mutable.HashMap[Int, mutable.HashMap[Int,T]]

  var nextId: Int = 0


  /**
   * Получение объекта-связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   * @return Объект связи
   */
  def getLink(sourceId: Int, targetId: Int): Option[T] = storage.get(sourceId) match {
    case Some(parent: mutable.HashMap[Int, T]) => parent.get(targetId)
    case _ => None
  }

  /**
   * Получение идентификатора источника
   * по идентификатору одного из
   * связанных с ним объектов
   *
   * @param targetId Идентификатор связанного объекта
   * @return Идентификатор источника
   */
  def getSourceId(targetId: Int): Int = {
    val result = storage.values.dropWhile(hashmap => !hashmap.values.exists(x => x.getTargetId() == targetId))
    result.head.head._1
  }

  /**
   * Получение списка идентификаторов объектов, связанных с указанным источником
   *
   * @param sourceId Идентификатор источника
   * @return Список идентификаторов
   */
  def getTargetIds(sourceId: Int): List[Int] = storage.get(sourceId) match {
    case Some(parent: mutable.HashMap[Int, T]) => parent.values.map(x => x.getTargetId).toList
    case _ => List()
  }

  /**
   * Удаление связи
   *
   * @param sourceId Идентификатор первого объекта (источника связи)
   * @param targetId Идентификатор второго объекта
   */
  def deleteLink(sourceId: Int, targetId: Int): Unit = storage.get(sourceId) match {
    case Some(parent: mutable.HashMap[Int, T]) => parent.remove(targetId)
    case _ =>
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
  def addLink(link: T): Unit
}
