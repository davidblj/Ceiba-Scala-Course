package controllers

import akka.util.ByteString
import javax.inject.Inject
import play.api.http.HttpEntity
import models.Owner
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.mvc.{AbstractController, ControllerComponents, ResponseHeader, Result}
import play.api.mvc.Cookie
import play.api.mvc.Results.InternalServerError

import scala.concurrent.{ExecutionContext, Future, TimeoutException}

class TestingController @Inject() (cc: ControllerComponents) (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  // actions
  def found = Action { request => Ok("look at this nice and minimalistic controller definition") }

  def badRequest = Action { request => BadRequest("Oops, page not found") }

  def internalServerError = Action { request => InternalServerError("Omg, you just broke this !") }

  def learnSomeScala = Action {
    Ok.sendFile(
      new java.io.File(
        "C:\\Users\\david.jaramillo\\Desktop\\repos\\Idea workspace\\Starter\\public\\pdf\\essential-scala.pdf"),
      false)
  }

  // routes
  def getRequest = Action { request => Ok("a get request !") }

  def postRequest = Action { request => Ok("a post request !") }

  def putRequest = Action { request => Ok("a put request !") }

  def deleteRequest = Action { request => Ok("a delete request !") }

  def patchRequest = Action { request => Ok("a patch request !") }

  def queryParameter(parameter: String) = Action { request =>
    Ok(s"a parameter with a value ${parameter} have been issued")
  }

  // response manipulation
  def customContentType = Action {
    Ok(<h1>Hello World!</h1>).as(HTML)
  }

  def customHeader = Action {
    Ok("a custom header").withHeaders(("CACHE_CONTROL", "max-age=3600"), ("ETAG", "xx"))
  }

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

  // Http Async
  def asyncRequest = Action.async {
    someCalculation().map(calculationResult => {
      Ok(s"The calculation result is ${calculationResult}")
    }).recover {
      case e: TimeoutException =>
        InternalServerError(s"Calculation timed out! exception thrown is: ${e.getMessage}")
    }
  }

  // privates
  private def someCalculation(): Future[Int] = {
    Future.successful(42)
  }

  // PlayJson
  def jsValue = Action {

    val json: JsValue = Json.obj("name" -> "david", "surname" -> "jaramillo")

    val fieldName: Option[String] = json("name").asOpt[String]
    val fieldSurname: Option[String] = json("surname").asOpt[String]
    Ok(s"the owner of this app is named: ${fieldName.get} ${fieldSurname.get}")
  }

  def jsManualWrite = Action {

    implicit val ownerWrite: Writes[Owner] = (
      (JsPath \ "name").write[String] and
        (JsPath \ "surname").write[String]
      ) (unlift(Owner.unapply))

    val owner = Owner("David", "Jaramillo")
    Ok(Json.toJson(owner))
  }

  def jsAutomaticWrite = Action {

    implicit val ownerWrite: Writes[Owner] = Json.writes[Owner]
    val owner = Owner("David", "Jaramillo")

    Ok(Json.toJson(owner))
  }

  def jsonSearch = Action {

    val json: JsValue = Json.obj(
      "name" -> "david",
      "surname" -> "jaramillo",
      "likings" -> Json.obj(
        "sport" -> "swimming training",
        "food" -> "everything you can imagine!"
      )
    )

    val favoriteSport = (json \ "likings" \ "sport").asOpt[String]
    Ok(s"David's favorite sport is:  ${favoriteSport.get}")
  }

  def jsonReadValidation = Action {

    implicit val ownerReads: Reads[Owner] = (
      (JsPath \ "name").read[String](minLength[String](2)) and
      (JsPath \ "surname").read[String](minLength[String](2))
    )(Owner.apply _)

    val json: JsValue = Json.obj(
      "name" -> "david",
      "surname" -> "jaramillo")

    val jsonResult = json.validate[Owner]

    jsonResult match {
        case JsSuccess(owner: Owner, path: JsPath) => Ok(s"The owner of this app is named: ${owner.name}")
        case e: JsError => InternalServerError("Errors: " + JsError.toJson(e).toString())
    }
  }

}