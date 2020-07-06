package design.hamu.job

import io.circe.syntax._
import design.hamu.model.{PrimeInput, PrimeOutput}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PrimeJobSpec extends AnyWordSpec with Matchers {
  "PrimeJob" should {
    "fail if input is not valid json" in {
      PrimeJob("foobar") mustBe a[Left[_, _]]
    }
    "fail if input is json but with missing fields" in {
      PrimeJob("{}") mustBe a[Left[_, _]]
    }
    "return correct output given valid input object" in {
      val name = "foobar"
      PrimeJob.run(PrimeInput(name,"")) must equal(
        PrimeOutput("haha", "haha")
      )
    }
    "return correct output given valid input json" in {
      val name = "lorem ipsum"
      PrimeJob(s"""{ "input": "${name}", "size": "${name}" }""") must equal(
        Right(PrimeOutput("haha", "haha").asJson)
      )
    }
  }
}
