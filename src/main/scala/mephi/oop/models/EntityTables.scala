package mephi.oop.models

import mephi.oop.IEntityTable

object DoctorTable extends IEntityTable[Doctor] {
  def findByAttributes(fio: String, age: Int): Option[Int] = {
    val result = for((id,Doctor(`fio`,`age`)) <- storage) yield id
    result.toList match {
      case (x :: xs) => Some(x)
      case _ => None
    }
  }
}

object PatientTable extends IEntityTable[Patient] {
  def findByAttributes(fio: String, age: Int): Option[Int] = {
    val result = for((id,Patient(`fio`,`age`)) <- storage) yield id
    result.toList match {
      case (x :: xs) => Some(x)
      case _ => None
    }
  }
}

object WardTable extends IEntityTable[Ward] {
  def findByAttributes(number: Int, building: Int): Option[Int] = {
    val result = for((id,Ward(`number`,`building`)) <- storage) yield id
    result.toList match {
      case (x :: xs) => Some(x)
      case _ => None
    }
  }
}


