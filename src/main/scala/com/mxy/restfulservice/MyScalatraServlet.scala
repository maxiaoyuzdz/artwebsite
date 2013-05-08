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

	    mustache("index.mustache","layout" -> "",
	        "pagenumber" -> 0,
	        "parametervalue" -> 2344567
        )
  }
  
  get("/artshow/:arttype") {
	  	contentType = "text/html"
	  	  
	  	println(params("arttype"))
	  	
	  	val showtype = params("arttype")
	  	val pagenumber = showtype match{
	  	  case "guohua" => 2
	  	  case "youhua" => 3
	  	  case "shufa" => 4
	  	  //if no match
//	  	  case _ => 0
	  	}
	  	println(pagenumber)
	  	
	    mustache("show.mustache","layout" -> "",
	        "pagenumber" -> pagenumber,
	        "parametervalue" -> 0
          )
  }
  
  
  /**
   * json data interface
   */
  get("/jsondata/querylemmonsliderdata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable

  }
  get("/jsondata/querybestfamouspeopledata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querybestfamouspeopletable
  }
  
  get("/jsondata/querybestworkshowdata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querybestworkshowtable(9)
  }
  //
  get("/jsondata/queryguohuadata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.queryartfromworkshowtable("G",4)
  }
  //
  get("/jsondata/queryyouhuadata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.queryartfromworkshowtable("Y",4)
  }
  //
  get("/jsondata/queryshufadata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.queryartfromworkshowtable("S",4)
  }
  //newestworksmallshow
  get("/jsondata/querynewestworksmallshow"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.newestworksmallshow(12)
  }
  
  
  get("/jsondata/querytest"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querytest
  }

  /**
   * format convert, anything be converted to JSON
   */
//  before() {
//    contentType = formats("json")
//  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
