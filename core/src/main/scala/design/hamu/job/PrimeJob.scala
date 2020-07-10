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

  def run(inputJson: PrimeInput): PrimeOutput = {
    def nPrimeNumbers = determinePrimeNumbers(inputJson.input).takeRight(inputJson.size)

    PrimeOutput(formatResponse(nPrimeNumbers), nPrimeNumbers.reduce(sum))
  }

  // list is the list of all prime numbers found.
  // count is the targeted length of that list.
  // num is the current number that we're checking if it is a prime.
  // itr the the iterator that is looking for num's factors
  def determinePrimeNumbers(count: Int, list: Vector[Int] = Vector[Int](), num: Int = 2, itr: Int = 2): Vector[Int] = {
    if (list.length == count) {
      list
    } else if (num % itr == 0) { // if number is divisible by itr
      if (num == itr) { // if num IS itr, we didn't find any other factors - thus it is prime
        determinePrimeNumbers(count, list :+ num, num + 1) // try the next num. add this num to the list.
      } else { // we found a factor and it's NOT num - thus it is composite.
        determinePrimeNumbers(count, list, num + 1) // try the next num. don't interact with the list.
      }
    } else { // number isn't divisible by itr
      determinePrimeNumbers(count, list, num, itr + 1) // try the next itr
    }
  }

  def formatResponse(list: Vector[Int]): String = {
    "[" + formatResponseRecur(list.toList) + "]"
  }

  def formatResponseRecur(list: List[Int], str: String = ""): String = list match {
    case Nil => str
    case head :: Nil => formatResponseRecur(Nil, str + head)
    case head :: tail => formatResponseRecur(tail, str + head + ",")
  }

  def sum(x: Int, y: Int): Int = { x + y }
}
