package design.hamu.job

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class JobSpec extends AnyWordSpec with Matchers {
  "Job" should {
    "instantiate a GreetJob given the string 'greet'" in {
      Job("greet") mustBe a[GreetJob.type]
    }
    "instantiate a PrimeJob given the string 'prime'" in {
      Job("prime") mustBe a[PrimeJob.type]
    }
  }
}
