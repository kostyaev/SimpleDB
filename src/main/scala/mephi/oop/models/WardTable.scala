package mephi.oop.models

import mephi.oop.IEntityTable

case class WardTable(number: Int, building: Int) extends IEntityTable[Ward]{
  /**
   * Удаление объекта по его
   * идентификатору
   *
   * @param id Идентификатор удаляемого объекта
   */
  override def delete(id: Int): Unit = ???

  /**
   * Получение объекта по его
   * идентификатору
   *
   * @param id Идентификатор объекта
   * @return Полученный объект либо null, если указанный объект не найден
   */
  override def get(id: Int): Ward = ???

  /**
   * Добавление объекта в таблицу
   *
   * @param obj Добавляемый объект
   * @return Идентификатор, присвоенный объекту
   */
  override def add(obj: Ward): Unit = ???
}
