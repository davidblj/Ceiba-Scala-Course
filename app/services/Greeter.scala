package services

trait Greeter {
  def sayHello(): String
}

class SpanishGreeter extends Greeter {
  override def sayHello(): String = "Hola que tal ?"
}

class EnglishGreeter extends Greeter {
  override def sayHello(): String = "What's up?"
}
