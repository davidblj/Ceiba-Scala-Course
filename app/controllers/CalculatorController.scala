package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.Calculator

class CalculatorController @Inject() (cc: ControllerComponents, calculator: Calculator)
  extends AbstractController(cc) {

  def sum(a: Int, b: Int) = Action {
    val sum = calculator.sum(a,b)
    Ok(s"The sum of $a + $b is $sum")
  }

  def subtract(a: Int, b: Int) = Action {

    try {
      val subtraction = calculator.subtract(a,b)
      Ok(s"The subtraction of $a - $b is $subtraction")
    } catch {
      case e: IllegalArgumentException => BadRequest(s"${e.getMessage}")
    }
  }

  def divide(a: Int, b: Int) = Action {

    try {
      val division = calculator.divide(a, b)
      Ok(s"The division of $a / $b is $division")
    } catch {
      case e: IllegalArgumentException => BadRequest(s"${e.getMessage}")
    }
  }

  def multiply(a: Int, b: Int) = Action {
    val multiplication = calculator.multiply(a,b)
    Ok(s"The multiplication of $a * $b is $multiplication")
  }
}
