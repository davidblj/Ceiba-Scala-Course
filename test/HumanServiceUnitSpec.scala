
import models.Human
import org.scalatest.Assertion
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import repositories.{HumanRepository, HumanRepositoryImp}
import services.HumanService

import scala.concurrent.{ExecutionContext, Future}

// todo: solve questions
// como puedo modificar el contexto de Play para cada prueba ? (sin instanciar
// una app en cada test)
// como evaluo un futuro sin el futureResult ?
// una prueba unitaria no puede utilizar GuiceOneApp ?
// que es un fixture

class HumanServiceSpec extends PlaySpec with GuiceOneAppPerSuite with MockitoSugar with ScalaFutures {

  // add a custom HumanRepo mock implementation
  // note that you will need to specify this every time
  // if you would need an specialized humanRepo implementation for each
  // test case
  override def fakeApplication(): Application = new GuiceApplicationBuilder()
    .overrides(bind[HumanRepository].to[humanRepoMock])
    .build()

  // get an instance of HumanService. This would be more useful if we have
  // implicits and multiple injects that would be a pain to mock
  val humanService = app.injector.instanceOf(classOf[HumanService])
  // implicit val ec: ExecutionContext = ExecutionContext.Implicits.global

  "HumanService#list" should {
    "return no humans" in {

      val futureResult: Future[Seq[Human]]  = humanService.listHumans()

      val humans: Seq[Human] = futureResult.futureValue
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
}