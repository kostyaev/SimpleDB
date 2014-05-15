package mephi.oop.gui

import scala.swing._
import mephi.oop.gui.table.{SpecificTableModel, TableColumnHeaderSelected}
import scala.swing.event.ButtonClicked
import mephi.oop.IEntityTable

class SpecificBoxPanel[T](orientation: Orientation.Value) extends BoxPanel(orientation) with GenericElems[T] {

  val text1: TextField = new TextField()
  val text2: TextField = new TextField()

  val button: Button = new Button("Добавить")

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
      contents += button
    }
    listenTo(button)
    reactions += {
      case ButtonClicked(`button`) =>
        table.model.asInstanceOf[SpecificTableModel].addRow(Array(entityTable.nextId.toString, text1.text, text2.text))
        save(text1.text, text2.text)
        table.model.asInstanceOf[SpecificTableModel].fireTableDataChanged()
    }
  }

  lazy val table = new Table() {
    model = SpecificTableModel(rowData, columnNames)
    selection.elementMode = Table.ElementMode.Cell
    val header = {
      import java.awt.event.{MouseEvent, MouseAdapter}
      val makeHeaderEvent = TableColumnHeaderSelected(this, _:Int)
      val tableHeader = peer.getTableHeader
      tableHeader.addMouseListener(new MouseAdapter() {
        override def mouseClicked(e: MouseEvent) {
          selection.publish(makeHeaderEvent(tableHeader.columnAtPoint(e.getPoint)))
        }
      })
      tableHeader
    }
  }

  contents ++= Seq(input, new ScrollPane(table))

}
