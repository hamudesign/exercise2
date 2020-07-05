package design.hamu

import cats.implicits._
import scala.io.Source

object FileReader {

  /**
    * Either.catchOnly[T] catches any T thrown within the block and returns them as Left
    * If no exception is thrown, the result is returned as right
    */
  def readFile(fileName: String): Either[Throwable, String] = Either.catchOnly[Throwable] {
    Source.fromResource(fileName).mkString
  }
}
