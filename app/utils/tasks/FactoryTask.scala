package utils.tasks

import akka.actor.ActorSystem
import javax.inject._
import play.api.Logger
import utils.contexts.TaskExecContextImp

import scala.concurrent.duration._

@Singleton
class FactoryTask @Inject() (actorSystem: ActorSystem, taskec: TaskExecContextImp) {

  actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 2.seconds) {
    Logger.info("Executing something")
  }(taskec)
}

// Variant using a custom execution context from an actor system
/*
class FactoryTask @Inject() (actorSystem: ActorSystem, taskec: TaskExecContext) {

  val taskec: ExecutionContext = actorSystem.dispatchers.lookup("tasks-dispatcher")
  actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 2.seconds) {
    Logger.info("Executing something")
  }(taskec)
}
*/