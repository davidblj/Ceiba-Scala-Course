package utils

import javax.inject.Inject
import models.{Human, Person}
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.concurrent.ExecutionContext

class Validate @Inject()(parser: PlayBodyParsers)
                        (implicit exec: ExecutionContext) {

  implicit val personReads: Reads[Person] = Json.reads[Person]
  implicit val humanReads: Reads[Human] = (
    (JsPath \ "id").readNullable[Long] and
    (JsPath \ "name").read[String] and
    (JsPath \ "age").read[Int] and
    (JsPath \ "ethnicity").read[String]
  )(Human.apply _)

  def validateJson[A : Reads] = parser.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
}
