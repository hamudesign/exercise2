package design.hamu.model

import io.circe.generic.JsonCodec

/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "input": "an integer in string form",
  *   "size": "an integer in string form"
  * }
  */
@JsonCodec
case class FibInput(
  input: String,
  size: String
)
