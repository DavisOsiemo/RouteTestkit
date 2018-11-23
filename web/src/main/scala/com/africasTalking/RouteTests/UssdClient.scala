package com.africasTalking.RouteTests

import akka.actor.{ActorSystem, Actor}

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpCharsets._

import akka.stream.ActorMaterializer

class UssdClient {

  def main(args: Array[String]): Unit = {
    implicit val system       = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val dispatcher   = system.dispatcher

    val url     = "api.sandbox.com"
    val apiKey  = ""

    val request = HttpRequest(
      POST,
      uri = url,
      entity = FormData( Map(
        ""  ->  "",
        ""  ->  ""
      )).toEntity(`UTF-8`),
      headers = List(headers.RawHeader ("", apiKey))
    )
  }
}
