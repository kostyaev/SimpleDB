package mephi.oop.gui

import scala.swing._
import scala.swing.event.ButtonClicked

class QueryBoxPanel(orientation: Orientation.Value) extends BoxPanel(orientation) with QueryElems {

  val text1: TextField = new TextField()
  val text2: TextField = new TextField()

  override lazy val output:TextArea = new TextArea(6, 40) { editable = false }

  val searchButton: Button = new Button("Найти")

  lazy val input = new BoxPanel(Orientation.Horizontal) {
    contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label("ID доктора")
      contents += text1
    }
    contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label(" ")
      contents += searchButton
    }

    listenTo(searchButton)

    reactions += {
      case ButtonClicked(`searchButton`) if isCorrect(text1.text) => search(text1.text)
      case _ =>
    }
  }
  contents ++= Seq(input, new ScrollPane(table), new ScrollPane(output))
}
