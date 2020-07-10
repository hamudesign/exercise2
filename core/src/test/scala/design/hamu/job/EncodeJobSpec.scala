package design.hamu.job

import io.circe.syntax._
import design.hamu.model.{EncodeInput, EncodeOutput}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EncodeJobSpec extends AnyWordSpec with Matchers {
  "EncodeJob" should {
    "fail if input is not valid json" in {
      EncodeJob("foobar") mustBe a[Left[_, _]]
    }
    "fail if input is json but with missing fields" in {
      EncodeJob("{}") mustBe a[Left[_, _]]
    }
    "return correct output given valid input object" in {
      val input = "foobar"
      EncodeJob.run(EncodeInput(input)) must equal(
        EncodeOutput(s"f1o2b1a1r1", 5)
      )
    }
    "return correct output given valid input json" in {
      val input = "lorem ipsum"
      EncodeJob(s"""{ "input": "$input" }""") must equal(
        Right(EncodeOutput(s"l1o1r1e1m1 1i1p1s1u1m1", 10).asJson)
      )
    }
  }
}
