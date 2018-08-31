package tables

import models.Human
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

class Humans(tag: Tag) extends Table[Human](tag, "humans") {

  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("name")
  def age: Rep[Int] = column[Int]("age")
  def ethnicity: Rep[String] = column[String]("ethnicity")

  def * : ProvenShape[Human] = (id, name, age, ethnicity) <> ((Human.apply _).tupled, Human.unapply)
}
