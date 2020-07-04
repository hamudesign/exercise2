package design.hamu.job

import io.circe.syntax._
import design.hamu.model.{GreetInput, GreetOutput}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GreetJobSpec extends AnyWordSpec with Matchers {
  "GreetJob" should {
    "fail if input is not valid json" in {
      GreetJob("foobar") mustBe a[Left[_, _]]
    }
    "fail if input is json but with missing fields" in {
      GreetJob("{}") mustBe a[Left[_, _]]
    }
    "return correct output given valid input object" in {
      val name = "foobar"
      GreetJob.run(GreetInput(name)) must equal(
        GreetOutput(s"Greetings ${name}!")
      )
    }
    "return correct output given valid input json" in {
      val name = "lorem ipsum"
      GreetJob(s"""{ "name": "${name}" }""") must equal(
        Right(GreetOutput(s"Greetings ${name}!").asJson)
      )
    }
  }
}
