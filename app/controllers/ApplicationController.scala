package controllers

import models.Place
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{Action, BodyParsers, Controller}

/**
  * Created by wilson on 2/18/17.
  */
class ApplicationController extends Controller {
  def listPlaces = Action {
    val json = Json.toJson(Place.list)
    Ok(json)
  }

  def savePlace = Action(BodyParsers.parse.json) { request =>
    val placeResult = request.body.validate[Place]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
      },
      place => {
        Place.save(place)
        Ok(Json.obj("status" -> "OK", "message" -> ("Place '" + place.lastName + "' saved.")))
      }
    )
  }
}
