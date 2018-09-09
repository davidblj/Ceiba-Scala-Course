import com.google.inject.AbstractModule
import java.time.Clock

import repositories.{HumanRepository, HumanRepositoryImp}
import services.{ApplicationTimer, AtomicCounter, Counter}
import utils.tasks.FactoryTask

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {

    // default instatiations
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    // classes
    bind(classOf[Counter]).to(classOf[AtomicCounter])
    bind(classOf[HumanRepository]).to(classOf[HumanRepositoryImp])

    // singletons
    bind(classOf[ApplicationTimer]).asEagerSingleton()
  }
}
