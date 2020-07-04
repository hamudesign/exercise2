package design.hamu.job

import design.hamu.model.{GreetInput, GreetOutput}
import io.circe.Json
import io.circe.parser
import io.circe.syntax._

object GreetJob extends Job {

  def apply(string: String): Either[Throwable, Json] =
    parser
      .parse(string) // returns Either[Throwable, Json] based on if string is valid json
      .flatMap { json =>
        json.as[GreetInput] // returns Either[Throwable, GreetInput] based on if json can be decoded to GreetInput
      }
      .map { input =>
        run(input).asJson // creates output and encode into json
      }

  def run(input: GreetInput): GreetOutput =
    GreetOutput(s"Greetings ${input.name}!")
}
