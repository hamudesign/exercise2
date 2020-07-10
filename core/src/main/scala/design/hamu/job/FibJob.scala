package design.hamu.job

import design.hamu.model.{FibInput, FibOutput }
import io.circe.Json
import io.circe.parser
import io.circe.syntax._

object FibJob extends Job {

    def apply(string: String): Either[Throwable, Json] =
        parser
          .parse(string) // returns Either[Throwable, Json] based on if string is valid json
          .flatMap { json =>
              json.as[FibInput] // returns Either[Throwable, GreetInput] based on if json can be decoded to GreetInput
          }
          .map { input =>
              run(input).asJson // creates output and encode into json
          }

    def run(input: FibInput): FibOutput =
        FibOutput({
            filterToEven(input.isEven, fib(input.input)).mkString(",")
        },
            filterToEven(input.isEven, fib(input.input)).sum
        )


    def fib (input : Int): Array[Int] = input match {
        case 1 =>  Array(1)
        case 2 => fib(input - 1) ++ Array(1)
        case x =>
            val index = fib(input - 1).length
            // Array concatenate (Array(last two values from prior fib array)
            fib(input-  1) ++ Array(fib(input - 1)(index - 1) + fib(input - 1)(index - 2))
    }

    // Is there a ternary filter?
    def filterToEven(isEven : Boolean, arr : Array[Int]) : Array[Int] = {
        if (isEven)
            arr.filter( n => n % 2 == 0)
        else
            arr
    }


}
