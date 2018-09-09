package utils.contexts

import akka.actor.ActorSystem
import javax.inject.{Inject}
import play.api.libs.concurrent.CustomExecutionContext

class TaskExecContextImp @Inject() (actorSystem: ActorSystem)
  extends CustomExecutionContext(actorSystem, "tasks-dispatcher"){
}
