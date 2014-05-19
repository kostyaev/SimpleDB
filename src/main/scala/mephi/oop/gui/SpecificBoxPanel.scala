package mephi.oop.gui

import scala.swing._
import scala.swing.event.ButtonClicked

class SpecificBoxPanel(orientation: Orientation.Value) extends BoxPanel(orientation) with GenericElems {

  val text1: TextField = new TextField()
  val text2: TextField = new TextField()

  val addButton: Button = new Button("Добавить")
  val deleteButton: Button = new Button("Удалить")

  override lazy val output:TextArea = new TextArea(6, 40) { editable = false }

  lazy val input = new BoxPanel(Orientation.Horizontal) {
    contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label(name1)
      contents += text1
    }
    contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label(name2)
      contents += text2
    }
    contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label(" ")
      contents += addButton
    }
    contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label(" ")
      contents += deleteButton
    }

    listenTo(addButton, deleteButton)

    reactions += {
      case ButtonClicked(`addButton`) if isCorrect(text1.text, text2.text) => addRow(text1.text, text2.text)
      case ButtonClicked(`deleteButton`) => deleteSelectedRows()
      case _ =>
    }
  }


  contents ++= Seq(input, new ScrollPane(table), new ScrollPane(output))

}
