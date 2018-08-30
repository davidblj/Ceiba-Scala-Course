package tables

import models.Human
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

class Humans(tag: Tag) extends Table[Human](tag, "Humans") {

  def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("NAME")
  def age: Rep[Int] = column[Int]("AGE")
  def ethnicity: Rep[String] = column[String]("ETHNICITY")

  def * : ProvenShape[Human] = (id, name, age, ethnicity) <> ((Human.apply _).tupled, Human.unapply)
}
