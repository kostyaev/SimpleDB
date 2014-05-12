package mephi.oop

import scala.collection.mutable
import argonaut.CodecJson
import argonaut.Argonaut._

/**
 * Интерфейс основной таблицы,
 * хранящей список объектов
 *
 * @tparam T Тип объектов, хранимых в таблице
 */

trait IEntityTable[T] {

  val storage: mutable.HashMap[Int, T] = new mutable.HashMap[Int, T]


  var nextId: Int = 1

  /**
   * Добавление объекта в таблицу
   *
   * @param obj Добавляемый объект
   * @return Идентификатор, присвоенный объекту
   */

  def add(obj: T): Int = {
    storage += (nextId -> obj)
    nextId += 1
    nextId - 1
  }

  /**
   * Получение объекта по его
   * идентификатору
   *
   * @param id Идентификатор объекта
   * @return Полученный объект либо null, если указанный объект не найден
   */

  def get(id: Int): Option[T] = storage.get(id)

  /**
   * Удаление объекта по его
   * идентификатору
   *
   * @param id Идентификатор удаляемого объекта
   */

  def delete(id: Int): Unit = storage.remove(id)

}
