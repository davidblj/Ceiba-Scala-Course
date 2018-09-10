package services

class Calculator {

  def sum(a: Int, b: Int) = a + b

  def subtract(a: Int, b: Int) = {
    require(a > b, "Plot twist, 'a' must be greater than 'b'")
    a - b
  }

  def divide(a: Int, b: Int) = {
    require(b > 0, "Divisions by 0 are impossible")
    a / b
  }

  def multiply(a: Int, b: Int) = {
    a * b
  }
}
