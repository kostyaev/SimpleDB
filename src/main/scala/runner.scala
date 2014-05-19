
import mephi.oop.gui._
import scala.swing._

object runner extends App {

  val frame = new MainFrame with IOHelper {
    thisFrame: MainFrame =>
    title = "Редактор БД"
    menuBar = new MenuBar {
      contents += new Menu("Файл") {
        contents += new MenuItem(Action("Открыть") {
          loadDBFile()
          thisFrame.contents = new SpecificBoxPanel(Orientation.Vertical) with DoctorElems
          thisFrame.title = "Таблица докторов"
        })
        contents += new MenuItem(Action("Сохранить") {
          saveDBFile()
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
      contents += new Menu("Запросы") {
        contents += new MenuItem(Action("Запрос 1") {
          thisFrame.contents = new QueryBoxPanel(Orientation.Vertical)
          thisFrame.title = "Запрос 1"
        })
      }
    }
    contents = new SpecificBoxPanel(Orientation.Vertical) with DoctorElems
    title = "Таблица докторов"
    //size = new Dimension(600,600)
    centerOnScreen()
  }
  frame.visible = true

}
