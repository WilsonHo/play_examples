import akka.event.slf4j.Logger
import play.http.websocket.Message.Ping
import scalikejdbc.config.DBs

/**
  * Created by wilson on 3/16/17.
  */
trait AppComponents {
//  applicationLifecycle.addStopHook { () =>
//    Logger.info("The app is about to stop")
//    DBs.closeAll()
//    Future.successful(Unit)
//  }
//
//  val onStart = {
//    Logger.info("The app is about to start")
//    DBs.setupAll()
//    statsActor ! Ping
//  }
}
