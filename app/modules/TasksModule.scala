package modules

import play.api.inject.{SimpleModule, _}
import utils.tasks.FactoryTask

class TasksModule extends SimpleModule(bind[FactoryTask].toSelf.eagerly())