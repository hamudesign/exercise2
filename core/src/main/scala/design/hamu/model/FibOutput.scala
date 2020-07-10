package design.hamu.model

import io.circe.generic.JsonCodec

/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "greeting": "some string..."
  * }
  */
@JsonCodec
case class FibOutput(
    output: String, //Array[Int],  // Should this be a String?  For easier checking?
    sum: Int
)
