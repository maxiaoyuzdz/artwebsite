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
	  	  //querylemmonslider
	  	val sliderres = ArtWebSiteDataSourceObject.querylemmonslidertable
	  	
	  	val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
	  	
	  	val famouspeopleres = ArtWebSiteDataSourceObject.querybestfamouspeopletable
	  	
	  	val indexbestworkshowres = ArtWebSiteDataSourceObject.querybestworkshowtable(9)
	  	
	  	val indexguohualistres = ArtWebSiteDataSourceObject.queryartfromworkshowtable("G",4)
	  	
	  	val indexyouhualistres = ArtWebSiteDataSourceObject.queryartfromworkshowtable("Y",4)
	  	
	  	val indexshufalistres = ArtWebSiteDataSourceObject.queryartfromworkshowtable("S",4)

	    mustache("index.mustache","layout" -> "",
	        "lemmonsliderrepo" -> sliderres,
	        "newestwork" -> newestworkres,
	        "famouspeople" -> famouspeopleres,
	        "indexbestworkshow" -> indexbestworkshowres,
	        "indexguohualist" -> indexguohualistres,
	        "indexyouhualist" -> indexyouhualistres,
	        "indexshufalist" -> indexshufalistres
	        )
  }
  
  
  
  get("/artshow/:arttype/page/:pagenumber") {
	  	contentType = "text/html"
	  	  
	  	var currentpagenumber = Integer.parseInt(params("pagenumber"))
	  	
	  	if(currentpagenumber <= 0)
	  	  currentpagenumber = 1
	  	    
	  	
	  	val showtype = params("arttype")
	  	
	  	val pageparameter = showtype match{
	  	  case "guohua" => "G"
	  	  case "youhua" => "Y"
	  	  case "shufa" => "S"
	  	  case _ => "N"
	  	}
	  	
	  	if(pageparameter == "N") redirect("/")
	  	
	  	val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
	  	
	  	
	  	val showworkres = ArtWebSiteDataSourceObject.querywork(pageparameter,currentpagenumber - 1,2)
	  	
	  	val categorylist = ArtWebSiteDataSourceObject.querycategorypinxie(pageparameter)
	  	
	  	
	  	
//	  	val tn = ArtWebSiteDataSourceObject.getpagenumberrange(pageparameter)(0)
	  	val pageamount = ArtWebSiteDataSourceObject.getpagenumberrange(pageparameter)(0).measures.toInt
	  	
	  	var pl = ArtWebSiteDataSourceObject.querypagenum(pageamount)
	  	
//	  	val p1 = pl(0)
//	  	val p2 = pl(1)
//	  	val p3 = pl(22)
//	  	val p4 = pl.length
	  	
	  	for(i <- (0 to pl.length-1)){
	  	  if(i == currentpagenumber-1){
//	  	    println(pl(i).id)
	  	    pl(i).iscurrentpage = true
	  	    pl(i).isnotcurrentpage = false
	  	  }
	  	}
	  	
	  	
	    mustache("show.mustache","layout" -> "",
	        "newestwork" -> newestworkres,
	        "gallerylist" -> showworkres,
	        "categorylist" -> categorylist,
	        
	        "menulist" -> pl
          )
  }
  
//  notFound {
//	  <h1>Not found. Bummer.</h1>
//  }
  
  
  /**
   * json data interface
   */
  /**
   * Fix data
   */
  /**
   * art
   */
  get("/jsondata/querynewestworksmallshow"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.newestworksmallshow(12)
  }
  /**
   * index page
   */
  get("/jsondata/querylemmonsliderdata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable

  }
  get("/jsondata/querylemmonsliderdata1"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable1

  }
  get("/jsondata/querylemmonsliderdata2"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable2

  }
  get("/jsondata/querylemmonsliderdata3"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable3

  }
  get("/jsondata/querylemmonsliderdata4"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable4

  }
  get("/jsondata/querylemmonsliderdata5"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable5

  }
  get("/jsondata/querylemmonsliderdata6"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable6

  }
  /**
   * 
   */
  get("/jsondata/querybestfamouspeopledata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querybestfamouspeopletable
  }
  /**
   * 
   */
  get("/jsondata/querybestworkshowdata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querybestworkshowtable(9)
  }
  /**
   * 
   */
  get("/jsondata/queryguohuadata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.queryartfromworkshowtable("G",4)
  }
  /**
   * 
   */
  get("/jsondata/queryyouhuadata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.queryartfromworkshowtable("Y",4)
  }
  /**
   * 
   */
  get("/jsondata/queryshufadata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.queryartfromworkshowtable("S",4)
  }
  /**
   * 
   */
  //newestworksmallshow
  get("/jsondata/querynewestworksmallshow"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.newestworksmallshow(12)
  }
  
  
  /**
   * show page
   */
  

  /**
   * format convert, anything be converted to JSON
   */
  get("/jsondata/artshowdata/queryartwork/:worktype/:pagenumber"){
    contentType = formats("json")

    val worktype = params("worktype")//G Y S
    val pagenumber = Integer.parseInt(params("pagenumber"))//1,2,3
    

    
    ArtWebSiteDataSourceObject.querywork(worktype,pagenumber,15)
    
  }
//  before() {
//    contentType = formats("json")
//  }
  get("/jsondata/artshowdata/test"){
    contentType = formats("json")

    
    val tn = ArtWebSiteDataSourceObject.querytest2(0)
    val tt:Int = tn.measures.toInt
//    ArtWebSiteDataSourceObject.querypagenum
	
    
  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
