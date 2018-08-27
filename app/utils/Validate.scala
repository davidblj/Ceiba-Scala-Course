package utils

import javax.inject.Inject
import models.Person
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json._

import scala.concurrent.ExecutionContext

class Validate @Inject()(parser: PlayBodyParsers)
                        (implicit exec: ExecutionContext) {

  implicit val personWrites: Writes[Person] = Json.writes[Person]
  implicit val personReads: Reads[Person] = Json.reads[Person]

  def validateJson[A : Reads] = parser.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
}
