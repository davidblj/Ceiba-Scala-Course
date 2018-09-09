package modules

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import services.{EnglishGreeter, Greeter, SpanishGreeter}

class LanguageModule extends AbstractModule {

  override def configure(): Unit = {
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.

    bind(classOf[Greeter])
      .annotatedWith(Names.named("es"))
      .to(classOf[SpanishGreeter])

    bind(classOf[Greeter])
      .annotatedWith(Names.named("en"))
      .to(classOf[EnglishGreeter])
  }
}
