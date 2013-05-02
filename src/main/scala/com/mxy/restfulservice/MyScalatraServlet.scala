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

//==================================================
// Data source Begin
//==================================================
import com.mxy.restfulservice.model.ArtWebSiteDataSourceObject
//import com.mxy.restfulservice.model.EbusinessStore
//==================================================
// Data source End
//==================================================



class MyScalatraServlet extends RestfulserviceStack with ScalateSupport with JacksonJsonSupport with DatabaseSessionSupport{
  
  val logger = Logger(classOf[MyScalatraServlet]);
  
  //redirect default address to "/"
  get("/index") {
    redirect("/")
  }
  get("/index.html") {
    redirect("/")
  }
  

//  proecess "/" page . one get, one page 
  get("/") {
    contentType = "text/html"
      
      mustache("index2.mustache","layout" -> "")
      
      
//      val lemonsliderdata = ArtWebSiteDataSourceObject.querylemmonslidertable
//      println("ok")
//    
//    val t = lemonsliderdata(3)
//    
//      val slidermaplist = List[Map(String -> Any)]
      
//      lemonsliderdata.foreach(f => slidermaplist + "imgsrc" -> f.imgsrc )

//    mustache("index.mustache",
//        "title" -> "泽雅斋--专业的艺术作品交流平台",
//        slidermaplist
//        "lemmon_slider_repo" -> List(
//            Map("imgsrc" -> t.imgsrc)
//            )
            
//        List(
//	    Map("name" -> "resque","hreftarget" -> "login"),
//	    Map("name" -> "hub","hreftarget" -> "login"),
//	    Map("name" -> "rip","hreftarget" -> "login")
//	  )
//    )
    

  }
  
  get("/jsondata/queryproperty"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable
//    EbusinessStore.querycategory
  }
  /**
   * format convert, anything be converted to JSON
   */
//  before() {
//    contentType = formats("json")
//  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
