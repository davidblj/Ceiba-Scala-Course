package controllers

import repositories.HumanRepository
import models.Human.humanFormat
import javax.inject.Inject
import models.Human
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import utils.Validate

import scala.concurrent.ExecutionContext

class HumanController @Inject() (humanRepo: HumanRepository, cc: MessagesControllerComponents, parserValidation: Validate )
                                (implicit  ec: ExecutionContext) extends AbstractController(cc) {

  def list = Action.async {

    humanRepo.list().map { humans =>
      Ok(Json.toJson(humans))
    }
  }

  def create = Action.async(parserValidation.validateJson[Human]) {

    request => {
      humanRepo.create(request.body).map(code => {
        Ok(s"A human spawned")
      })
    }
  }

  def delete(id: Long) = Action.async {

    humanRepo.del(id).map(code => {
      Ok(s"A human disappeared !")
    })
  }

  def update() = Action.async(parserValidation.validateJson[Human]) {

    request => {
      humanRepo.update(request.body).map(code => {
        Ok("Your human has been updated !")
      })
    }
  }
}
