package design.hamu.model

import io.circe.generic.JsonCodec

/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "input": "some string..."
  * }
  */
@JsonCodec
case class EncodeInput(
  input: String
)
