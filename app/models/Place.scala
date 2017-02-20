package models

import play.api.libs.json.{JsPath, Reads, Writes}
import play.api.libs.functional.syntax._
/**
  * Created by wilson on 2/18/17.
  */
case class Location(lat: Double, long: Double)

case class Place(lastName: String, location: Location)

object Place {

  var list: List[Place] = {
    List(
      Place(
        "Sandleford",
        Location(51.377797, -1.318965)
      ),
      Place(
        "Watership Down",
        Location(51.235685, -1.309197)
      )
    )
  }

  def save(place: Place) = {
    list = list ::: List(place)
  }

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
      (JsPath \ "long").write[Double]
    )(unlift(Location.unapply))

  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "last_name").write[String] and
      (JsPath \ "location").write[Location]
    )(unlift(Place.unapply))

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double] and
      (JsPath \ "long").read[Double]
    )(Location.apply _)

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "last_name").read[String] and
      (JsPath \ "location").read[Location]
    )(Place.apply _)
}