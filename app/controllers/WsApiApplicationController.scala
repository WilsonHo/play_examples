package controllers

import javax.inject.Inject

import play.api.libs.ws.WSClient
import play.api.mvc.Controller

/**
  * Created by wilson on 2/21/17.
  */
class WsApiApplicationController@Inject() (ws: WSClient) extends Controller {

}
