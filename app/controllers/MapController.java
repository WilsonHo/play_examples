package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wilson on 3/15/17.
 */
public class MapController extends Controller {
    public Result returnMap() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        return ok(Json.toJson(map));
    }
}
