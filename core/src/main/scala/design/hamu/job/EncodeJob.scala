package design.hamu.job

import design.hamu.model.{EncodeInput, EncodeOutput}

import io.circe.Json
import io.circe.parser
import io.circe.syntax._
import scala.collection.{mutable => m}


object EncodeJob extends Job {

  def apply(string: String): Either[Throwable, Json] =
    parser
      .parse(string) // returns Either[Throwable, Json] based on if string is valid json
      .flatMap {json : Json =>
        json.as[EncodeInput] // returns Either[Throwable, EncodeInput] based on if json can be decoded to EncodeInput
      }
      .map {input: EncodeInput =>
        run(input).asJson // creates output and encode into json
      }

  def run(input: EncodeInput): EncodeOutput = {
    val (output, uniqueChar) = convertToOutput(input.input)
    EncodeOutput(output, uniqueChar)
  }

  def convertToOutput(inputArray: String): (String, Int) = {
    (countContiguousChars(inputArray.head, inputArray, 0), inputArray.distinct.length)
  }

  def countContiguousChars(char: Char, inputArray: String, count: Int): String = {
    if(inputArray.isEmpty) s"$char${count.toString}"
    else {
      if (char.equals(inputArray.head)) countContiguousChars(char, inputArray.reverse.dropRight(1).reverse, count + 1)
      else s"$char${count.toString}${countContiguousChars(inputArray.head, inputArray.reverse.dropRight(1).reverse, 1)}"
    }
  }
}

