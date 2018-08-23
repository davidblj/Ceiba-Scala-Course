package controllers

import akka.util.ByteString
import javax.inject.Inject
import play.api.http.HttpEntity
import play.api.mvc.{AbstractController, ControllerComponents, ResponseHeader, Result}
import play.api.mvc.Cookie

import scala.concurrent.ExecutionContext

class TestingController @Inject() (cc: ControllerComponents) (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  // actions
  def found = Action { request => Ok("look at this nice and minimalistic controller definition") }
  def badRequest = Action { request => BadRequest("Oops, page not found")}
  def internalServerError = Action { request => InternalServerError("Omg, you just broke this !")}
  def learnSomeScala = Action { Ok.sendFile(
    new java.io.File(
      "C:\\Users\\david.jaramillo\\Desktop\\repos\\Idea workspace\\Starter\\public\\pdf\\essential-scala.pdf"),
      false)}

  // routes
  def getRequest = Action { request => Ok("a get request !")}
  def postRequest = Action { request => Ok("a post request !")}
  def putRequest = Action { request => Ok("a put request !")}
  def deleteRequest = Action { request => Ok("a delete request !")}
  def patchRequest = Action { request => Ok("a patch request !")}

  def queryParameter(parameter: String) = Action { request =>
    Ok(s"a parameter with a value ${parameter} have been issued")
  }

  // response manipulation
  def customContentType = Action { Ok(<h1>Hello World!</h1>).as(HTML) }

  def customHeader = Action { Ok("a custom header").withHeaders(("CACHE_CONTROL", "max-age=3600"),("ETAG", "xx")) }

  // response configuration with a custom header
  // and an explicit content type
  def customHeader_v2 = Action {
    Result(header =
      ResponseHeader(401, Map.empty),
      body = HttpEntity.Strict(ByteString("Hello world!"), Some("text/plain")))
  }

  def cookieResponse = Action {
    Ok("Here, take a cookie").withCookies(Cookie("Taste", "Strawberry"))
  }

  // action composition


  // ErrorHandling
  def handle = Action {
    throw new IllegalStateException("Exception thrown")
    Ok("This wont actually execute")
  }
}
