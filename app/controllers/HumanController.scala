package controllers

import repositories.HumanRepositoryImp
import javax.inject.Inject
import models.Human
import play.api.cache._
import play.api.libs.json.Json
import play.api.mvc._
import services.HumanService
import utils.Validate

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class HumanController @Inject() (humanRepo: HumanRepositoryImp, humanService: HumanService, parser: PlayBodyParsers,
                                 cc: MessagesControllerComponents, cache: AsyncCacheApi, cached: Cached, parserValidation: Validate)
                                (implicit  ec: ExecutionContext) extends AbstractController(cc) {

  def list = Action.async {

    humanService.listHumans().map { humans =>
      Ok(Json.toJson(humans))
    }
  }

  def create = Action.async(parserValidation.validateJson[Human]) {

    request => {
      humanService.saveHuman(request.body).map(code => {
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

  def find(id: Long) = Action.async {

    humanRepo.find(id).map(human => {
      if (human.isEmpty) NotFound("resource not found")
      else Ok(Json.toJson(human.get))
    })
  }

  def search() = Action.async { request =>

    val name = request.getQueryString("name")
    if (name.isDefined) {
      humanService.findHumanByName(name.get).map(humans => Ok(Json.toJson(humans)))
    } else {
      humanService.listHumans().map(humans =>Ok(Json.toJson(humans)))
    }
  }

  // play cache
  def listCached = Action.async {

    val humans: Future[Seq[Human]] = cache.getOrElseUpdate[Seq[Human]]("humans", Duration(15, MINUTES)) {
      println("take notes, this wont print a second time (for 15 minutes, that is)!")
      humanRepo.list()
    }

    humans
      .map(humans => {
        Ok(Json.toJson(humans))
      }).recover {
      case _ => InternalServerError("this wont execute")
    }
  }

  // def listHttpCached = cached(_ => "humans-http") {
  def listHttpCached = cached("humans-http").default(1) {

    Action.async {
      println("take notes, this wont print a second time !")
      humanRepo.list().map { humans =>
        Ok(Json.toJson(humans))
      }
    }
  }
}
