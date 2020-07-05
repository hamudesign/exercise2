package design.hamu

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileReaderSpec extends AnyWordSpec with Matchers {
  "FileReader" should {
    "successfully read file which exists" in {
      FileReader.readFile("test.txt") must equal(Right("helloworld"))
    }
    "fail to read file which dne" in {
      FileReader.readFile("dummy") mustBe a[Left[_, _]]
    }
  }
}
