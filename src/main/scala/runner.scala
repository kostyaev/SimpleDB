
import scala.swing._
import scala.swing.event.{TableRowsSelected, TableColumnsSelected, TableEvent}

case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)

object runner extends App {

  val headers = Array.tabulate(10) {"Col-" + _}.toSeq
  val rowData = Array.tabulate[Any](10, 10) {"" + _ + ":" + _}


  val frame = new MainFrame {
    title = "DB Manager"
    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("Open") {
          //openFile
        })
        contents += new MenuItem(Action("Save") {
          //saveFile
        })
        contents += new Separator
        contents += new MenuItem(Action("Exit") {
          sys.exit(0)
        })
      }
    }
    contents = mainSection
    size = new Dimension(500,500)
    centerOnScreen
  }
  frame.visible = true


  lazy val mainSection = new BoxPanel(Orientation.Horizontal) {

    val rightSide = new BoxPanel(Orientation.Vertical) {
      contents += new Button("Add row")
      contents += new Button("Delete row")
    }
    contents += leftSide
    contents += rightSide

  }


  lazy val leftSide = new BoxPanel(Orientation.Vertical) {
    val table  =  new Table(rowData, headers) {
      selection.elementMode = Table.ElementMode.Cell
      //selection.intervalMode = Table.IntervalMode.Single

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

    val output = new TextArea(6, 40) { editable = false }

    listenTo(table.selection)

    reactions += {
      case TableRowsSelected(source, range, false) =>
        outputSelection(source, "Rows selected, changes: %s" format range)
      case TableColumnsSelected(source, range, false) =>
        outputSelection(source, "Columns selected, changes: %s" format range)
      case TableColumnHeaderSelected(source, column) =>
        outputSelection(source, "Column header %s selected" format column)
      case e => println("%s => %s" format(e.getClass.getSimpleName, e.toString))
    }

    contents += new ScrollPane(table)
    contents += new ScrollPane(output)

    def outputSelection(table: Table, msg: String) {
      val rowId = table.selection.rows.leadIndex
      val colId = table.selection.columns.leadIndex
      val rows = table.selection.rows.mkString(", ")
      val cols = table.selection.columns.mkString(", ")
      output.append("%s\n  Lead: %s, %s; Rows: %s; Columns: %s\n" format (msg, rowId, colId, rows, cols))
    }
  }


}
