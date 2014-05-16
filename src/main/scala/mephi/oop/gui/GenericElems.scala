package mephi.oop.gui

import scala.swing.Table
import mephi.oop.gui.table.{TableColumnHeaderSelected, SpecificTableModel}

trait GenericElems {

  lazy val name1: String = ???

  lazy val name2: String = ???

  lazy val rowData: Array[Array[String]] = ???

  lazy val columnNames: Seq[String] = ???

  def nextId: Int = ???

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

  protected def save(x: String, y: String): Unit = ???

  protected def delete(id: Int): Unit = ???

  def isCorrect(x: String, y: String): Boolean = ???

  def addRow(x: String, y: String): Unit = {
    table.model.asInstanceOf[SpecificTableModel].addRow(Array(nextId.toString, x, y))
    save(x,y)
  }

  def deleteSelectedRows(): Unit = {
    val tableModel = table.model.asInstanceOf[SpecificTableModel]
    val ids = (for(rowId <- table.selection.rows) yield tableModel.getValueAt(rowId,0)).toSeq
    tableModel.delRows(ids)
    ids.foreach(id => delete(id.toInt))
  }


}
