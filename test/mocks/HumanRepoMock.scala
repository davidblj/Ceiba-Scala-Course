package mocks

import models.Human
import repositories.HumanRepository

import scala.concurrent.Future

class humanRepoMock extends HumanRepository {
  override def create(human: Human): Future[Int] = Future.successful(1)
  override def list(): Future[Seq[Human]] = Future.successful(List())
  override def del(id: Long): Future[Int] = Future.successful(1)
  override def update(human: Human): Future[Int] = Future.successful(1)
}
