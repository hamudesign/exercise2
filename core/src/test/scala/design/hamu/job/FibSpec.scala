package design.hamu.job

import io.circe.syntax._
import design.hamu.model.{FibInput, FibOutput}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FibSpec extends AnyWordSpec with Matchers {
    "FibJob" should {
        "fail if input is not valid json" in {
            FibJob("foobar") mustBe a[Left[_, _]]
        }
        "fail if input is json but with missing fields" in {
            FibJob("{}") mustBe a[Left[_, _]]
        }
        // Don't think this will work since it's an array and we'd be checking memory not value?
        "return correct output given valid input object" in {
            val input = 5
            val isEven = false
            FibJob.run(FibInput(input, isEven)) must equal(
                FibOutput(Array(1, 1, 2, 3, 5).mkString(","), 12)
            )
        }
        "return correct output given valid input json" in {
            val input = 6
            val isEven = true
            FibJob(s"""{ "input": "${input}", "isEven": "${isEven}""") must equal(
                Right(FibOutput(Array(2,8).mkString(","), 10).asJson)
            )
        }
    }
}
