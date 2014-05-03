package mephi.oop

/**
 * Таблица связей между объектами
 *
 * @tparam T Тип связей, хранящихся в таблице
 */
trait ILinkTable[T <: ILink] {

/**
 * Добавление связи
 *
 * @param link Добавляемая связь
 */
def addLink(link: T)

/**
 * Добавление связи
 *
 * @param sourceId Идентификатор первого объекта (источника связи)
 * @param targetId Идентификатор второго объекта
 */
def addLink(sourceId: Int, targetId: Int)

/**
 * Удаление связи
 *
 * @param sourceId Идентификатор первого объекта (источника связи)
 * @param targetId Идентификатор второго объекта
 */
def deleteLink(sourceId: Int, targetId: Int)

/**
 * Получение списка идентификаторов объектов, связанных с указанным источником
 *
 * @param sourceId Идентификатор источника
 * @return Список идентификаторов
 */
def getTargetIds(sourceId: Int): List[Integer]

/**
 * Получение идентификатора источника
 * по идентификатору одного из
 * связанных с ним объектов
 *
 * @param targetId Идентификатор связанного объекта
 * @return Идентификатор источника
 */
def getSourceId(targetId: Int): Int

/**
 * Получение объекта-связи
 *
 * @param sourceId Идентификатор первого объекта (источника связи)
 * @param targetId Идентификатор второго объекта
 * @return Объект связи
 */
def getLink(sourceId: Int, targetId: Int): T


}
