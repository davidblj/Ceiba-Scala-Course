package services

import javax.inject.Inject
import models.Human
import repositories.{HumanRepository}

import scala.concurrent.Future

class HumanService @Inject() (humanRepo: HumanRepository) {

    def listHumans(): Future[Seq[Human]] = {

      // this is just a wrapper. and for testing purposes
      // we won't code any logic here
      humanRepo.list()
    }

    def saveHuman(human: Human): Future[Int]  = {
      humanRepo.create(human)
    }
}
