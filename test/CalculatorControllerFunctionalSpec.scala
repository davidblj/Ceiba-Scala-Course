import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._

class CalculatorControllerFunctionalSpec extends PlaySpec with GuiceOneServerPerSuite {

    "Calculator routes#sum" should {
      "should return a successful (200) text response" in {
        // arr
        val request = FakeRequest(GET, "/calculator/sum?a=1&b=2")

        // act
        val result = route(app, request).get

        // assert
        status(result) mustBe Status.OK
        contentType(result) mustBe Some("text/plain")
      }

      "should return an unsuccessful response (400) when a parameter is missing" in {
        // arr
        val request = FakeRequest(GET, "/calculator/sum?a=1")

        // act
        val result = route(app, request).get

        // assert
        status(result) mustBe Status.BAD_REQUEST
      }

      "should sum the right amount" in {
        // arr
        val request = FakeRequest(GET, "/calculator/sum?a=1&b=2")

        // act
        val result = route(app, request).get

        // assert
        contentAsString(result) mustBe "The sum of 1 + 2 is 3"
      }
    }

    "Calculator routes#substract" should {
      "should return a successful (200) text response" in {
        // arr
        val request = FakeRequest(GET, "/calculator/subtract?a=3&b=1")

        // act
        val result = route(app, request).get

        // assert
        status(result) mustBe Status.OK
        contentType(result) mustBe Some("text/plain")
      }

      "should return an unsuccessful response (400) when a parameter is missing" in {
        // arr
        val request = FakeRequest(GET, "/calculator/subtract?a=1")

        // act
        val result = route(app, request).get

        // assert
        status(result) mustBe Status.BAD_REQUEST
      }

      "should return an unsuccessful response (400) when 'b' is greater than 'a'" in {
        // arr
        val request = FakeRequest(GET, "/calculator/subtract?a=1&b=2")

        // act
        val result = route(app, request).get

        // assert
        status(result) mustBe Status.BAD_REQUEST
        contentAsString(result) mustBe "requirement failed: Plot twist, 'a' must be greater than 'b'"
      }

      "should subtract the right amount" in {
        // arr
        val request = FakeRequest(GET, "/calculator/subtract?a=3&b=1")

        // act
        val result = route(app, request).get

        /// assert
        contentAsString(result) mustBe "The subtraction of 3 - 1 is 2"
      }
    }

  "Calculator routes#divide" should {
    "should return a successful (200) text response" in {
      // arr
      val request = FakeRequest(GET, "/calculator/divide?a=6&b=2")

      // act
      val result = route(app, request).get

      // assert
      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }

    "should return an unsuccessful response (400) when a parameter is missing" in {
      // arr
      val request = FakeRequest(GET, "/calculator/divide?a=1")

      // act
      val result = route(app, request).get

      // assert
      status(result) mustBe Status.BAD_REQUEST
    }

    "should return an unsuccessful response (400) when 'b' equals 0" in {
      // arr
      val request = FakeRequest(GET, "/calculator/divide?a=6&b=0")

      // act
      val result = route(app, request).get

      // assert
      status(result) mustBe Status.BAD_REQUEST
      contentAsString(result) mustBe "requirement failed: Divisions by 0 are impossible"
    }

    "should divide the right amount" in {
      // arr
      val request = FakeRequest(GET, "/calculator/divide?a=6&b=2")

      // act
      val result = route(app, request).get

      // assert
      contentAsString(result) mustBe "The division of 6 / 2 is 3"
    }
  }

  "Calculator routes#multiply" should {
    "should return a successful (200) text response" in {
      // arr
      val request = FakeRequest(GET, "/calculator/multiply?a=6&b=2")

      // act
      val result = route(app, request).get

      // assert
      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }

    "should return an unsuccessful response (400) when a parameter is missing" in {
      // arr
      val request = FakeRequest(GET, "/calculator/multiply?a=1")

      // assert
      val result = route(app, request).get

      // act
      status(result) mustBe Status.BAD_REQUEST
    }

    "should multiply the right amount" in {
      // arr
      val request = FakeRequest(GET, "/calculator/multiply?a=6&b=2")

      // act
      val result = route(app, request).get

      // assert
      contentAsString(result) mustBe "The multiplication of 6 * 2 is 12"
    }
  }

}
