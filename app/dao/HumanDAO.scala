package dao

import javax.inject.{Inject, Singleton}
import models.Human
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import tables.Humans

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HumanDAO @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val human = TableQuery[Humans]

  import dbConfig._
  import profile.api._

  def list(): Future[Seq[Human]] = db.run { human.result }
}
