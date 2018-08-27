package models

import play.api.libs.json.{Json, Reads, Writes}

case class Person (name: String, surname: String)

object PersonList {

  implicit val personWrites: Writes[Person] = Json.writes[Person]
  implicit val personReads: Reads[Person] = Json.reads[Person]

  var list: List[Person]  =  List(
    Person("david", "Jaramillo"),
    Person("Jaime", "Jaramillo"),
    Person("Angela", "Bolivar"),
  )

  def save(person: Person) = {
    list = list :+ person
  }
}
