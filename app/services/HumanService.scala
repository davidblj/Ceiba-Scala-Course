package services

import javax.inject.Inject
import models.Human
import repositories.{HumanRepository}

import scala.concurrent.Future

// this is just a wrapper. and for testing purposes
// we won't code any logic here
class HumanService @Inject()(humanRepo: HumanRepository) {

    def listHumans(): Future[Seq[Human]] = {
      humanRepo.list()
    }

    def saveHuman(human: Human): Future[Int]  = {
      humanRepo.create(human)
    }

    def findHuman(id: Long): Future[Option[Human]] = {
      humanRepo.find(id)
    }

    def findHumanByName(name: String): Future[Seq[Human]] = {
      humanRepo.findByName(name)
    }
}
