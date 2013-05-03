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
	    
	    val scriptstring = mustache("datascript.mustache","layout" -> "")
	    
	    println("scriptstring = "+scriptstring)
      
//      mustache("index.mustache","layout" -> "",
//          "whichpage" -> "indexpage",
//          "mainparameter" -> "first",
//          "replacescript" -> scriptstring)
	    mustache("index.mustache","layout" -> "",
          "whichpage" -> "indexpage",
          "mainparameter" -> "first")
          

  }
  /**
   * json data interface
   */
  get("/jsondata/queryproperty"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable

  }
  
  
/**
 * script generate interface
 * /assets/{{whichpage}}/js/datascript.js
 */
  
  
  get("/assets/:whichpage/:outparameter/datascript.js"){
    //contentType = "text/html"//text/html //application/x-javascript
    
    val whichpage = params("whichpage")
    val mainparameter = params("outparameter")
    
    println(whichpage+ " + "+ mainparameter)

    
    mustache("datascript.mustache","layout" -> "")
  }
  
  
  /**
   * format convert, anything be converted to JSON
   */
//  before() {
//    contentType = formats("json")
//  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
