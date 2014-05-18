package mephi.oop.gui

import java.io.File
import scala.swing.FileChooser
import mephi.oop.Serializer

trait IOHelper {

  def loadDBFile(title: String = "Выбрать файл БД"): Unit = {
    val chooser = new FileChooser(new File("."))
    chooser.title = title
    val result = chooser.showOpenDialog(null)
    if (result == FileChooser.Result.Approve) {
      println("Загружен файл " + chooser.selectedFile)
      Serializer.load(chooser.selectedFile)
    }
  }

  def saveDBFile(title: String = "Сохранить файл БД"): Unit = {
    val chooser = new FileChooser(new File("."))
    chooser.title = title
    val result = chooser.showSaveDialog(null)
    if (result == FileChooser.Result.Approve) {
      println("Сохранен файл " + chooser.selectedFile)
      Serializer.save(chooser.selectedFile)
    }
  }


}
