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
      val input = 4
      val size = 3
      PrimeJob.run(PrimeInput(input,size)) must equal(
        PrimeOutput("[3,5,7]", 15)
      )
    }
    "return correct output given valid input json" in {
      val input = 3
      val size = 2
      PrimeJob(s"""{ "input": "${input}", "size": "${size}" }""") must equal(
        Right(PrimeOutput("[3,5]", 8).asJson)
      )
    }
  }
}
