package mephi.oop

/**
 * Интерфейс основной таблицы,
 * хранящей список объектов
 *
 * @tparam T Тип объектов, хранимых в таблице
 */

trait IEntityTable[T] {

  /**
   * Добавление объекта в таблицу
   *
   * @param obj Добавляемый объект
   * @return Идентификатор, присвоенный объекту
   */

  def add(obj: T)

  /**
   * Получение объекта по его
   * идентификатору
   *
   * @param id Идентификатор объекта
   * @return Полученный объект либо null, если указанный объект не найден
   */

  def get(id: Int): T

  /**
   * Удаление объекта по его
   * идентификатору
   *
   * @param id Идентификатор удаляемого объекта
   */

  def delete(id: Int)

}
