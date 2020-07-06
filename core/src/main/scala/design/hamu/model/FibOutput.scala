package design.hamu.model

import io.circe.generic.JsonCodec

/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "output": "an array of integers in string form",
  *   "sum": "an integer in string form"
  * }
  */
@JsonCodec
case class FibOutput(
  output: String,
  sum: String
)
