
import mephi.oop.gui._
import mephi.oop.gui.table.TableColumnHeaderSelected
import mephi.oop.models._
import mephi.oop.models.Doctor
import mephi.oop.models.Patient
import scala.swing._
import scala.swing.event.{TableRowsSelected, TableColumnsSelected}


object runner extends App {
  val id1 = DoctorTable.add(Doctor("House", 54))
  val id2 = DoctorTable.add(Doctor("Dorian", 28))

  val id3 = PatientTable.add(Patient("John", 54))
  val id4 = PatientTable.add(Patient("James", 28))

  val frame = new MainFrame {
    thisFrame: MainFrame =>
    title = "Редактор БД"
    menuBar = new MenuBar {
      contents += new Menu("Файл") {
        contents += new MenuItem(Action("Открыть") {
          //openFile
        })
        contents += new MenuItem(Action("Сохранить") {
          //saveFile
        })
        contents += new Separator
        contents += new MenuItem(Action("Выйти") {
          sys.exit(0)
        })
      }
      contents += new Menu("Таблица") {
        contents += new MenuItem(Action("Доктора") {
          thisFrame.contents = new SpecificBoxPanel(Orientation.Vertical) with DoctorElems
          thisFrame.title = "Таблица докторов"
        })
        contents += new MenuItem(Action("Пациенты") {
          thisFrame.contents = new SpecificBoxPanel(Orientation.Vertical) with PatientElems
          thisFrame.title = "Таблица пациентов"
        })
        contents += new MenuItem(Action("Палаты") {
          thisFrame.contents = new SpecificBoxPanel(Orientation.Vertical) with WardElems
          thisFrame.title = "Таблица палат"
        })
        contents += new MenuItem(Action("Доктор-Пациенты") {
          thisFrame.contents = new SpecificBoxPanel(Orientation.Vertical) with DoctorPatientsElems
          thisFrame.title = "Таблица связей доктор-пациенты"
        })
        contents += new MenuItem(Action("Палата-Пациенты") {
          thisFrame.contents = new SpecificBoxPanel(Orientation.Vertical) with WardPatientsElems
          thisFrame.title = "Таблица связей палаты-пациенты"
        })
      }
    }
    size = new Dimension(600,600)
    centerOnScreen()
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
