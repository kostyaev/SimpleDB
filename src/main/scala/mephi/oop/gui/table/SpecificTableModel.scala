package mephi.oop.gui.table

import javax.swing.table.AbstractTableModel
import scala.swing.Table
import scala.swing.event.TableEvent

case class SpecificTableModel(var rowData: Array[Array[String]], columnNames: Seq[String]) extends AbstractTableModel {
  override def getColumnName( column: Int) = columnNames(column).toString
  def getRowCount() = rowData.length
  def getColumnCount() = columnNames.length
  def getValueAt( row: Int, col: Int): AnyRef = rowData(row)(col).asInstanceOf[AnyRef]
  override def isCellEditable( row: Int, column: Int) = false
  override def setValueAt( value: Any, row: Int, col: Int) {
    rowData(row)(col) = value.toString
  }
  def addRow( data: Array[String]) {
    rowData ++= Array(data.asInstanceOf[Array[String]])
  }
  def updateRowData(rowData: Array[Array[String]]) = this.rowData = rowData
}

case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)