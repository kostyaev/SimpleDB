
import mephi.oop.gui.table.TableColumnHeaderSelected
import mephi.oop.gui.{WardElems, PatientsElems, DoctorElems, SpecificBoxPanel}
import mephi.oop.models._
import mephi.oop.models.Doctor
import mephi.oop.UIObjects
import scala.swing._
import scala.swing.event.{ButtonClicked, TableRowsSelected, TableColumnsSelected}


object runner extends App {

  val id1 = DoctorTable.add(Doctor("House", 54))
  val id2 = DoctorTable.add(Doctor("Dorian", 28))

  val id3 = PatientTable.add(Patient("John", 54))
  val id4 = PatientTable.add(Patient("James", 28))

  val frame = new MainFrame {
    title = "DB Manager"
    menuBar = new MenuBar {
      contents += new Menu("Файл") {
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
      contents += UIObjects.tableMenu

    }
    listenTo(UIObjects.doctorItem, UIObjects.patientItem, UIObjects.wardItem)

    reactions += {
      case ButtonClicked(UIObjects.doctorItem) => contents = new SpecificBoxPanel[Doctor](Orientation.Vertical) with DoctorElems
      case ButtonClicked(UIObjects.patientItem) => contents = new SpecificBoxPanel[Patient](Orientation.Vertical) with PatientsElems
      case ButtonClicked(UIObjects.wardItem) => contents = new SpecificBoxPanel[Ward](Orientation.Vertical) with WardElems

    }
    size = new Dimension(600,600)
    centerOnScreen


  }
  frame.visible = true


  def getContent(input: BoxPanel, table: Table) = new BoxPanel(Orientation.Vertical) {
    val bottom = new BoxPanel(Orientation.Horizontal) {
      contents += new Button("Edit row")
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

    contents += input
    contents += new ScrollPane(table)
    contents += bottom
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
