package utils.contexts

import akka.actor.ActorSystem
import javax.inject.{Inject}
import play.api.libs.concurrent.CustomExecutionContext

import scala.concurrent.ExecutionContext

trait TaskExecContext extends ExecutionContext

class TaskExecContextImp @Inject() (actorSystem: ActorSystem)
  extends CustomExecutionContext(actorSystem, "tasks-dispatcher") with TaskExecContext {
}
