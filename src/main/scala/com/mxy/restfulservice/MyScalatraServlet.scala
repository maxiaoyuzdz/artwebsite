package com.mxy.restfulservice

import org.scalatra._
import scalate.ScalateSupport
//==================================================
import net.liftweb.json.compact
import net.liftweb.json.render
import net.liftweb.json.JsonDSL._
import net.liftweb.json.Serialization.{read,write}
import net.liftweb.json.Serialization
import net.liftweb.json.NoTypeHints

import org.json4s._
import org.json4s.jackson.JsonMethods._
//==================================================
import org.scalatra.json._
//==================================================
import grizzled.slf4j.Logger
//==================================================
import com.mxy.restfulservice.data.DatabaseSessionSupport

class MyScalatraServlet extends RestfulserviceStack with ScalateSupport with JacksonJsonSupport with DatabaseSessionSupport{
  
  val logger = Logger(classOf[MyScalatraServlet]);
  
  //redirect default address to "/"
  get("/index") {
    redirect("/")
  }
  get("/index.html") {
    redirect("/")
  }
  

  get("/") {
    contentType = "text/html"
    mustache("index.mustache",
        "title" -> "泽雅斋--专业的艺术作品交流平台",
        "repo" -> List(
    Map("name" -> "resque","hreftarget" -> "login"),
    Map("name" -> "hub","hreftarget" -> "login"),
    Map("name" -> "rip","hreftarget" -> "login")
  )
    )
  }
  /**
   * format convert, anything be converted to JSON
   */
//  before() {
//    contentType = formats("json")
//  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
