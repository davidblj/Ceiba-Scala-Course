
import models.Human
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import repositories.HumanRepository
import services.HumanService

import scala.concurrent.{ExecutionContext, Future}

// todo: solve questions
// como puedo modificar el contexto de Play para cada prueba ? (sin instanciar una app en cada test) https://www.playframework.com/documentation/2.6.x/ScalaFunctionalTestingWithScalaTest
// como evaluo un futuro sin el futureResult ?
// una prueba unitaria no puede utilizar GuiceOneApp ?
// que son los fixtures

class HumanServiceUnitSpec extends PlaySpec with GuiceOneAppPerSuite with MockitoSugar with ScalaFutures {

  // we are adding a custom HumanRepo mock implementation
  // note that you will need to specify this every time
  // if you would need an specialized humanRepo implementation for each
  // test case
  override def fakeApplication(): Application = new GuiceApplicationBuilder()
    .overrides(bind[HumanRepository].to[humanRepoMock])
    .build()

  // we are getting an instance of HumanService. This would be more useful if we have
  // implicits and multiple injects that would be a pain to mock
  val humanService = app.injector.instanceOf(classOf[HumanService])
  // implicit val ec: ExecutionContext = ExecutionContext.Implicits.global

  "HumanService#list" should {
    "return no humans" in {

      val futureResult: Future[Seq[Human]]  = humanService.listHumans()

      val humans: Seq[Human] = futureResult.futureValue

      // We are using "should" on this test
      humans should have length 0

      /*futureResult.map(humans =>
        assert(humans.toList.isEmpty)
      )*/
    }
  }

  "HumanService#create" should {
    "save humans successfully" in {

      val human = mock[Human]
      val futureResult: Future[Int]  = humanService.saveHuman(human)

      val resultCode = futureResult.futureValue

      // We are using "asserts" on this test
      assert( resultCode == 1)

      /*futureResult.map(resultCode =>
        assert(resultCode == 2)
      )*/
    }
  }
}

class humanRepoMock extends HumanRepository {
  override def create(human: Human): Future[Int] = Future.successful(1)
  override def list(): Future[Seq[Human]] = Future.successful(List())
  override def del(id: Long): Future[Int] = Future.successful(1)
  override def update(human: Human): Future[Int] = Future.successful(1)
  override def find(id: Long): Future[Option[Human]] = ???
  override def findByName(name: String): Future[Seq[Human]] = ???
}