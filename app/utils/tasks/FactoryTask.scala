package utils.tasks

import akka.actor.ActorSystem
import javax.inject._
import play.api.Logger
import utils.contexts.TaskExecContext

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

@Singleton
class FactoryTask @Inject() (actorSystem: ActorSystem)
                            (implicit ec: ExecutionContext) {
//class FactoryTask @Inject() (actorSystem: ActorSystem, taskec: TaskExecContext) {

  actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 2.seconds) {
    Logger.info("Executing something")
  }
//  }(taskec)
}
