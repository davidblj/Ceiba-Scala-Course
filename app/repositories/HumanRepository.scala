package repositories

import javax.inject.{Inject, Singleton}
import models.Human
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import tables.Humans

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HumanRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val humans = TableQuery[Humans]

  import dbConfig._
  import profile.api._

  // todo: handle deletes and updates on nonexistent values

  def create(human: Human): Future[Int] = db.run(
    humans.map(h =>
      (h.name, h.age, h.ethnicity)) += (human.name, human.age, human.ethnicity)
  )

  def list(): Future[Seq[Human]] = db.run { humans.result }

  def del(id: Long) : Future[Int] = db.run {
    humans.filter(_.id === id).delete
  }

  def update(human: Human): Future[Int] = {

    val query = for { h <- humans
                      if h.id === human.id } yield h
    db.run(query.update(human))
  }
}