package models

import play.api.libs.json.{Json, OFormat}

case class Human(id: Long, name: String, age: Int, ethnicity: String)

object Human {
  implicit val humanFormat: OFormat[Human] = Json.format[Human]
}
