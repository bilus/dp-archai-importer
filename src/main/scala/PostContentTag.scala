import org.json4s.jackson.JsonMethods
import org.json4s._
import scala.util.Try

case class PostContentTag(
  channel:      String,
  content:      Array[ContentTag],
  description:  String,
  objectId:     String,
  permalinkUrl: String,
  postId:       String,
  readTime:     String
)


object PostContentTag {
  def extract(json: String) : Option[PostContentTag] = {
    implicit val formats = org.json4s.DefaultFormats
    val result = Try(JsonMethods.parse(json, true).camelizeKeys.extract[PostContentTag])
    if (result.isFailure) {
      println(result.failed.get.getMessage) // TODO:  Need proper error handling -> send to DD (not here!).
    }
    result.toOption
  }
}
