package examples.json

import play.api.libs.json.{JsValue, Json}

/**
  * Created by wilson on 2/18/17.
  */
object TraversingAJsValueStructure {
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

  def main(args: Array[String]): Unit = {
    val lat = (json \ "location" \ "lat").get
    println(lat)
    // returns JsNumber(51.235685)

    val names = json \\ "name"
    // returns Seq(JsString("Watership Down"), JsString("Fiver"), JsString("Bigwig"))
    println(names)

    val bigwig = (json \ "residents") (1)
    // returns {"name":"Bigwig","age":6,"role":"Owsla"}
    println(bigwig)

  }
}
