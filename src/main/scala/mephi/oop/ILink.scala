package mephi.oop

/**
 * Интерфейс для связей между объектами
 */

trait ILink {

  def getSourceId(): Int

  def getTargetId(): Int

}
