package examples.json;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.typesafe.config.ConfigFactory;

import play.libs.ws.*;
import play.libs.ws.ahc.*;

import java.util.concurrent.CompletionStage;

/**
 * Created by wilson on 3/16/17.
 */
public class WsClientTest  {

//    WSClient wsClient;
//
//    public static void main(String[] args) {
//
//        String rUrl = "localhost:8091/notify/affiliates?&click_id=7a1e14209a5bd824b9ac75108bc9da45&sub_id=&cid=665c2ce4-dd52-4770-b39c-12eacdc56ef6&rotate_id=64857687-a1ed-4b0e-865f-bf89ee9a3151&type=SIMULATION";
//        CompletionStage<WSResponse> stage = wsClient.url(rUrl).get();
//    }
}
