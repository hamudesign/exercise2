package design.hamu.job

import design.hamu.model.{FibInput, FibOutput}
import io.circe.Json
import io.circe.parser
import io.circe.syntax._

object FibJob extends Job {

  def apply(string: String): Either[Throwable, Json] =
    parser
      .parse(string) // returns Either[Throwable, Json] based on if string is valid json
      .flatMap { json =>
        json.as[FibInput] // returns Either[Throwable, FibInput] based on if json can be decoded to FibInput
      }
      .map { input =>
        run(input).asJson // creates output and encode into json
      }

  def run(input: FibInput): FibOutput =
    FibOutput("haha", "haha")
}
