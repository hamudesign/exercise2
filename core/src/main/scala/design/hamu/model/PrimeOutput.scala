package design.hamu.model

import io.circe.generic.JsonCodec

/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "output": "an array of integers in string form",
  *   "sum": "an integer"
  * }
  */
@JsonCodec
case class PrimeOutput(
  output: List[Int],
  sum: Int
)
