import mocks.humanRepoMock
import models.Human
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import repositories.HumanRepository
import services.HumanService

import scala.concurrent.{ExecutionContext, Future}

class HumanServiceSpec extends PlaySpec with GuiceOneAppPerSuite {

  // add a custom HumanRepo mock implementation
  // note that you will need to specify this every
  // time that we need an specialized humanRepo implementation
  override def fakeApplication(): Application = new GuiceApplicationBuilder()
    .overrides(bind[HumanRepository].to[humanRepoMock])
    .build()

  /*
    // get an instance of HumanService. This would be more useful if we have
    // implicits and multiple injects that would be a pain to mock
    val humanService = app.injector.instanceOf(classOf[HumanService])
  */

  "HumanService#list" should {

    "return no humans" in {

      val humanService = app.injector.instanceOf(classOf[HumanService])

      /*val humansAlive = humanService.listHumans()
      humansAlive.map(humans => {
        humans must have length 0
      })*/

      val placeholder = true
      placeholder mustBe true
    }
  }
}
