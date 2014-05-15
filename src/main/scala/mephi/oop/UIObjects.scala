package mephi.oop

import scala.swing._


object UIObjects {

  lazy val doctorItem = new MenuItem("Доктора")
  lazy val patientItem = new MenuItem("Пациенты")
  lazy val wardItem = new MenuItem("Палаты")
  lazy val doctorPatientItem = new MenuItem("Доктор-пациенты")
  lazy val wardPatientsItem = new MenuItem("Палата-пациеты")


  lazy val tableMenu = new Menu("Таблица") {
    contents += doctorItem
    contents += patientItem
    contents += wardItem
    contents += doctorPatientItem
    contents += wardPatientsItem
  }


}
