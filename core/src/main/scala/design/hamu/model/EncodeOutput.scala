package design.hamu.model

import io.circe.generic.JsonCodec
/**
  * @JsonCodec creates json encoders/decoders out of the box
  * Json equivalent is:
  * {
  *   "output": "some string..."
  * }
  */
@JsonCodec
case class EncodeOutput(
  output: String,
  numUniqueCharacters: Int,
)
