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
case class GreetOutput(
  greeting: String
)
