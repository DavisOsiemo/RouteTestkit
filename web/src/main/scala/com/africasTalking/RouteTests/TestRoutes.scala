package com.africasTalking.RouteTests

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object WebServer {
    implicit val system = ActorSystem ()
    implicit val materializer = ActorMaterializer ()
    // needed for the future map/ flatmap in the end
    implicit val executionContext = system.dispatcher

    val requestHandler: HttpRequest => HttpResponse = {
      case HttpRequest(GET, Uri.Path ("/"), _, _, _) =>
        HttpResponse (entity = HttpEntity (
          ContentTypes.`text/html(UTF-8)`,
          "<html> <body> HTML content types! </body></html>"
        ))
      case HttpRequest (GET, Uri.Path ("/ping"),_ , _, _) =>
        HttpResponse (entity = "pong!")

      case HttpRequest (GET, Uri.Path ("/crash"), _, _, _) =>
        sys.error("BOOM!")

      case HttpRequest (GET, Uri.Path("/fetch"), _,_, _) =>
        HttpResponse (entity = HttpEntity (
          ContentTypes.`application/json`,
          List ("phoneNumber" -> "072324423", "phoneNumber"->"074384744").toString
        ))

      case r: HttpRequest =>
        r.discardEntityBytes()
        HttpResponse(404, entity = "Unknown resource")
    }
    val bindingFuture = Http().bindAndHandleSync (requestHandler, "localhost", 8083)
    println(s"Server online at http:localhost:8083/\n Press RETURN to stop...")
    StdIn.readLine() // Let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) //trigger unbinding from the port
      .onComplete(_ => system.terminate()) //and shutdown when done

}
