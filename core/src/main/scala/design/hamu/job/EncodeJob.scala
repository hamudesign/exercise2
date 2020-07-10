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
      .flatMap { json: Json =>
        json.as[EncodeInput] // returns Either[Throwable, EncodeInput] based on if json can be decoded to EncodeInput
      }
      .map { input: EncodeInput =>
        run(input).asJson // creates output and encode into json
      }

  def run(input: EncodeInput): EncodeOutput = {
    EncodeOutput(countContiguousChars(input.input.head, input.input, 0), input.input.distinct.length)
  }

  def countContiguousChars(char: Char, input: String, count: Int): String = {
    if (input.isEmpty) s"$char${count.toString}"
    else {
      if (char.equals(input.head)) countContiguousChars(char, input.tail, count + 1)
      else s"$char${count.toString}${countContiguousChars(input.head, input.tail, 1)}"
    }
  }
}
