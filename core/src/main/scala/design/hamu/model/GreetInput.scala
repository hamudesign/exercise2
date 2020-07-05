package design.hamu.model

import io.circe.generic.JsonCodec

/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "name": "some string..."
  * }
  */
@JsonCodec
case class GreetInput(
  name: String
)
