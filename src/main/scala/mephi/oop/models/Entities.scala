package mephi.oop.models

import argonaut.CodecJson
import argonaut.Argonaut._

case class Doctor(fio: String, age: Int) {
  implicit def DoctorCodecJson: CodecJson[Doctor] =
    casecodec2(Doctor.apply, Doctor.unapply)("fio", "age")
}

case class Patient(fio: String, age: Int) {
  implicit def PatientCodecJson: CodecJson[Patient] =
    casecodec2(Patient.apply, Patient.unapply)("fio", "age")
}

case class Ward(number: Int, building: Int) {
  implicit def PatientCodecJson: CodecJson[Ward] =
    casecodec2(Ward.apply, Ward.unapply)("number", "building")
}


