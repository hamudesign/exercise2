package design.hamu.job

import io.circe.syntax._
import design.hamu.model.{FibInput, FibOutput}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FibJobSpec extends AnyWordSpec with Matchers {
  "FibJob" should {
    "fail if input is not valid json" in {
      FibJob("foobar") mustBe a[Left[_, _]]
    }
    "fail if input is json but with missing fields" in {
      FibJob("{}") mustBe a[Left[_, _]]
    }
    "return correct output given valid input object" in {
      val name = "foobar"
      FibJob.run(FibInput(name,"")) must equal(
        FibOutput("haha", "haha")
      )
    }
    "return correct output given valid input json" in {
      val name = "lorem ipsum"
      FibJob(s"""{ "input": "${name}", "size": "${name}" }""") must equal(
        Right(FibOutput("haha", "haha").asJson)
      )
    }
  }
}
