package examples.json

/**
  * Created by wilson on 2/18/17.
  */
object ConvertingToAJsValue {
  import play.api.libs.json._

  case class Location(lat: Double, long: Double)

  case class Resident(lastName: String, age: Int, role: Option[String])

  case class Place(name: String, location: Location, residents: Seq[Resident])

  import play.api.libs.functional.syntax._

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
      (JsPath \ "long").write[Double]
    )(unlift(Location.unapply))

  implicit val residentWrites: Writes[Resident] = (
    (JsPath \ "last_name").write[String] and
      (JsPath \ "age").write[Int] and
      (JsPath \ "role").writeNullable[String]
    )(unlift(Resident.unapply))

  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "location").write[Location] and
      (JsPath \ "residents").write[Seq[Resident]]
    )(unlift(Place.unapply))

//  implicit val locationWrites = new Writes[Location] {
//    def writes(location: Location) = Json.obj(
//      "lat" -> location.lat,
//      "long" -> location.long
//    )
//  }
//
//  implicit val residentWrites = new Writes[Resident] {
//    def writes(resident: Resident) = Json.obj(
//      "last_name" -> resident.lastName,
//      "age" -> resident.age,
//      "role" -> resident.role
//    )
//  }
//
//  implicit val placeWrites = new Writes[Place] {
//    def writes(place: Place) = Json.obj(
//      "name" -> place.name,
//      "location" -> place.location,
//      "residents" -> place.residents)
//  }


  val json01: JsValue = Json.parse(
    """
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
}
""")

  val json02: JsValue = JsObject(Seq(
    "name" -> JsString("Watership Down"),
    "location" -> JsObject(Seq("lat" -> JsNumber(51.235685), "long" -> JsNumber(-1.309197))),
    "residents" -> JsArray(Seq(
      JsObject(Seq(
        "name" -> JsString("Fiver"),
        "age" -> JsNumber(4),
        "role" -> JsNull
      )),
      JsObject(Seq(
        "name" -> JsString("Bigwig"),
        "age" -> JsNumber(6),
        "role" -> JsString("Owsla")
      ))
    ))
  ))

  val json03: JsValue = Json.obj(
    "name" -> "Watership Down",
    "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197),
    "residents" -> Json.arr(
      Json.obj(
        "name" -> "Fiver",
        "age" -> 4,
        "role" -> JsNull
      ),
      Json.obj(
        "name" -> "Bigwig",
        "age" -> 6,
        "role" -> "Owsla"
      )
    )
  )

  val place = Place(
    "Watership Down",
    Location(51.235685, -1.309197),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  )

  val json04 = Json.toJson(place)

  def main(args: Array[String]): Unit = {
    println(json01)
    println(json02)
    println(json03)
    println(json04)
  }
}
