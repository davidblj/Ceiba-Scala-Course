package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class TestingController @Inject() (cc: ControllerComponents) (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  def found = Action { request => Ok("look at this nice and minimalistic controller definition") }
  def badRequest = Action { request => BadRequest("Oops, page not found")}
  def internalServerError = Action { request => InternalServerError("Omg, you just broke this !")}
  def learnSomeScala = Action { Ok.sendFile(
    new java.io.File(
      "C:\\Users\\david.jaramillo\\Desktop\\repos\\Idea workspace\\Starter\\public\\pdf\\essential-scala.pdf"),
      false)}

  def getRequest = Action { request => Ok("a get request !")}
  def postRequest = Action { request => Ok("a post request !")}
  def putRequest = Action { request => Ok("a put request !")}
  def deleteRequest = Action { request => Ok("a delete request !")}
  def patchRequest = Action { request => Ok("a patch request !")}
}
