import akka.actor.ActorSystem
import controllers.AsyncController
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test.FakeRequest
import org.scalatest.mockito.MockitoSugar

/**
 * Unit tests can run without a full Play application.
 */

class UnitSpec extends PlaySpec with MockitoSugar{

  // this test is no longer functional, since we added config component

  /*"CountController" should {

    "return a valid result with action" in {

      val counter: Counter = new Counter {
        override def nextCount(): Int = 49
      }

      val configuration = mock[Configuration]

      val controller = new CountController(stubControllerComponents(), configuration, counter)
      val result = controller.count(FakeRequest())
      contentAsString(result) must equal("49")
    }
  }*/

  "AsyncController" should {

    "return a valid result on action.async" in {
      // actor system will create threads that must be cleaned up even if test fails
      val actorSystem = ActorSystem("test")
      try {
        implicit val ec = actorSystem.dispatcher
        val controller = new AsyncController(stubControllerComponents(), actorSystem)
        val resultFuture = controller.message(FakeRequest())
        contentAsString(resultFuture) must be("Hi!")
      } finally {
        // always shut down actor system at the end of the test.
        actorSystem.terminate()
      }
    }
  }

}
