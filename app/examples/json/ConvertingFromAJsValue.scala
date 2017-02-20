package examples.json

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by wilson on 2/18/17.
  */
object ConvertingFromAJsValue {
  val json: JsValue = Json.parse(
    """
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "last_name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "last_name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
}
""")

  val minifiedString: String = Json.stringify(json)
  val readableString: String = Json.prettyPrint(json)

  case class Location(lat: Double, long: Double)

  case class Resident(lastName: String, age: Int, role: Option[String])

  case class Place(name: String, location: Location, residents: Seq[Resident])

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double] and
      (JsPath \ "long").read[Double]
    ) (Location.apply _)

  implicit val residentReads: Reads[Resident] = (
    (JsPath \ "last_name").read[String] and
      (JsPath \ "age").read[Int] and
      (JsPath \ "role").readNullable[String]
    ) (Resident.apply _)

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "location").read[Location] and
      (JsPath \ "residents").read[Seq[Resident]]
    ) (Place.apply _)


  def main(args: Array[String]): Unit = {
    println(minifiedString)
    println(readableString)

    val name = (json \ "name").as[String]
    // "Watership Down"

    val names = (json \\ "name").map(_.as[String])
    // Seq("Watership Down", "Fiver", "Bigwig")

    val nameOption = (json \ "name").asOpt[String]
    // Some("Watership Down")

    val bogusOption = (json \ "bogus").asOpt[String]
    // None

    println(name)
    println(names)
    println(nameOption)
    println(bogusOption)


    val nameResult: JsResult[String] = (json \ "name").validate[String]

    // Pattern matching
    nameResult match {
      case s: JsSuccess[String] => println("Name: " + s.get)
      case e: JsError => println("Errors: " + JsError.toJson(e).toString())
    }

    // Fallback value
    val nameOrFallback = nameResult.getOrElse("Undefined")

    // map
    val nameUpperResult: JsResult[String] = nameResult.map(_.toUpperCase())
    println("nameUpperResult: " + nameUpperResult)

    // fold
    val nameOption01: Option[String] = nameResult.fold(
      invalid = {
        fieldErrors =>
          fieldErrors.foreach(x => {
            println("field: " + x._1 + ", errors: " + x._2)
          })
          None
      },
      valid = {
        name => Some(name)
      }
    )

    val placeResult: JsResult[Place] = json.validate[Place]
    // JsSuccess(Place(...),)
    println("placeResult: " + placeResult)

    val residentResult: JsResult[Resident] = (json \ "residents") (1).validate[Resident]
    // JsSuccess(Resident(Bigwig,6,Some(Owsla)),)
    println("residentResult: " + residentResult)

    val resident: Array[Resident] = (json \ "residents") .as[Array[Resident]]
    // JsSuccess(Resident(Bigwig,6,Some(Owsla)),)
    println("resident: " + resident(1).lastName)

  }
}
