package design.hamu.job

import design.hamu.model.{PrimeInput, PrimeOutput}
import io.circe.Json
import io.circe.parser
import io.circe.syntax._

object PrimeJob extends Job {

  def apply(string: String): Either[Throwable, Json] =
    parser
      .parse(string) // returns Either[Throwable, Json] based on if string is valid json
      .flatMap { json =>
        json.as[PrimeInput] // returns Either[Throwable, PrimeInput] based on if json can be decoded to PrimeInput
      }
      .map { input =>
        run(input).asJson // creates output and encode into json
      }

  def run(input: PrimeInput): PrimeOutput =
    PrimeOutput("haha", "haha")
}
