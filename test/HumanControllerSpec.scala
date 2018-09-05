import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.http.Status
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

// como comparo jsValues

class HumanControllerSpec extends PlaySpec with GuiceOneServerPerSuite {

  "Human routes#list" should {

    "be successful (200)" in {

      val request = FakeRequest(GET, "/humans")
      val result  = route(app, request).get

      status(result) mustBe Status.OK
      contentType(result) mustBe Some("application/json")
    }

    "return an empty list" in {

      val request = FakeRequest(GET, "/humans")
      val result = route(app, request).get

      status(result) mustBe Status.OK
      contentType(result) mustBe Some("application/json")

      contentAsJson(result) mustBe Json.arr()
      /*val jsResult = contentAsJson(result).toString()
      jsResult mustBe "[]"*/
    }
  }
}