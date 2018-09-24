package repositories

import javax.inject.{Inject, Singleton}
import models.Human
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import tables.Humans
import scala.concurrent.{ExecutionContext, Future}

// como puedo navegar la documentacion para mirar los operacionse en los queries.
// navegar las acciones que puedo ejecutar

trait HumanRepository {
  def create(human: Human): Future[Int]
  def list(): Future[Seq[Human]]
  def del(id: Long): Future[Int]
  def update(human: Human): Future[Int]
  def find(id: Long): Future[Option[Human]]
  def findByName(name: String): Future[Seq[Human]]
  }

@Singleton
class HumanRepositoryImp @Inject()(dbConfigProvider: DatabaseConfigProvider)
                                  (implicit ec: ExecutionContext) extends HumanRepository {

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

  def find(id: Long): Future[Option[Human]] = {
    val query = humans.filter(_.id === id).take(1)
    db.run(query.result.headOption)
  }

  def findByName(name: String): Future[Seq[Human]] = {
    val query = humans.filter(_.name like s"$name%")
    db.run(query.result)
  }
}
