package controllers

import dao.HumanDAO
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, MessagesControllerComponents}

import scala.concurrent.ExecutionContext

class HumanController @Inject() (humanDao: HumanDAO, cc: MessagesControllerComponents )
                                (implicit  ec: ExecutionContext) extends AbstractController(cc) {

  def list = Action.async {
    humanDao.list().map { humans =>
      Ok(Json.toJson(humans))
    }
  }
}
