package mephi.oop.gui.table

import javax.swing.table.AbstractTableModel
import scala.swing.Table
import scala.swing.event.TableEvent
import scala.collection.mutable.ArrayBuffer

case class SpecificTableModel(var rowData: Array[Array[String]], columnNames: Seq[String]) extends AbstractTableModel {
  override def getColumnName( column: Int) = columnNames(column).toString
  def getRowCount() = rowData.length
  def getColumnCount() = columnNames.length
  def getValueAt(row: Int, col: Int): String = rowData(row)(col)
  override def isCellEditable( row: Int, column: Int) = false
  override def setValueAt( value: Any, row: Int, col: Int) {
    rowData(row)(col) = value.toString
  }
  def addRow(data: Array[String]) {
    rowData ++= Array(data.asInstanceOf[Array[String]])
    this.fireTableDataChanged()
  }
  def delRows(ids: Seq[String]) =  {
    rowData = rowData.filter(row => !ids.contains(row(0)))
    this.fireTableDataChanged()
  }
  def updateRowData(rowData: Array[Array[String]]) = this.rowData = rowData

  def update(rowData: Array[Array[String]]) = {
    this.rowData = rowData
    this.fireTableDataChanged()
  }
}

case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)