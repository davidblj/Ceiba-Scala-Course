import org.scalatestplus.play.PlaySpec
import services.Calculator

class CalculatorServiceUnitSpec extends PlaySpec {

  // This is a second take. We wont extend from GuiceOneAppPerSuite.
  // Do note that 'Calculator' doesn't have dependencies, but if we had,
  // we would have to mock them first and pass them through the constructor

  "CalculatorService#sum" should {

    "sum the right amount" in {
      val a = 1
      val b = 2
      val calcService = new Calculator()

      val result = calcService.sum(a, b)
      result mustBe 3
    }
  }

  "CalculatorService#subtract" should {

    "subtrack the right amount" in {
      val a = 2
      val b = 1
      val calcService = new Calculator()

      val result = calcService.subtract(a, b)
      result mustBe 1
    }

    "throw an illegal argument when b is greater than a" in {
      val a = 1
      val b = 2
      val calcService = new Calculator()
      an [IllegalArgumentException] must be thrownBy calcService.subtract(a, b)
    }
  }

  "CalculatorService#divide" should {

    "divide the right amount" in {
      val a = 2
      val b = 1
      val calcService = new Calculator()

      val result = calcService.divide(a, b)
      result mustBe 2
    }

    "throw an illegal argument when b is equal to 0" in {
      val a = 2
      val b = 0
      val calcService = new Calculator()
      an [IllegalArgumentException] must be thrownBy calcService.divide(a, b)
    }
  }

  "CalculatorService#multiply" should {

    "multiply the right amount" in {
      val a = 2
      val b = 3
      val calcService = new Calculator()

      val result = calcService.multiply(a, b)
      result mustBe 6
    }
  }
}
