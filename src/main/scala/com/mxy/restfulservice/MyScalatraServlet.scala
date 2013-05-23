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
import com.mxy.restfulservice.model.WorkShowObject


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
	  	
	  	
	  	
	  	val indexguohualistres = ArtWebSiteDataSourceObject.queryartfromworkshowtable("G",4)
	  	
	  	val indexyouhualistres = ArtWebSiteDataSourceObject.queryartfromworkshowtable("Y",4)
	  	
	  	val indexshufalistres = ArtWebSiteDataSourceObject.queryartfromworkshowtable("S",4)

	    mustache("index.mustache","layout" -> "",
	        "lemmonsliderrepo" -> sliderres,
	        "newestwork" -> newestworkres,
	        "famouspeople" -> famouspeopleres,
	        
	        "indexguohualist" -> indexguohualistres,
	        "indexyouhualist" -> indexyouhualistres,
	        "indexshufalist" -> indexshufalistres
	        )
  }
  
  
  
  get("/artshow/:category/listpage/:pagenumber") {
	  	contentType = "text/html"
	  	  
	  	val eachpagehasnum = 16
	  	
	  	val pagerefname = "listpage"
	  	val pagetyperefname = "artshow"
	  	  
	  	val showtype = params("category")
	  	  
	  	val currentpagenumber = Integer.parseInt(params("pagenumber"))
	  	
	  	if(currentpagenumber <= 0){
	  	  redirect("/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/1")
	  	}
	  	
	  	
	  	val pagecontentcategory = showtype match{
	  	  case "guohua" => "G"
	  	  case "youhua" => "Y"
	  	  case "shufa" => "S"
	  	  case _ => "N"
	  	}
	  	
	  	if(pagecontentcategory == "N") redirect("/")
	  	
	  	
	  	/**
	  	 * fix data for the footer
	  	 */
	  	val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
	  	/**
	  	 * main content
	  	 */
	  	val showworkres = ArtWebSiteDataSourceObject.querywork(pagecontentcategory,currentpagenumber - 1,eachpagehasnum)
	  	/**
	  	 * category list match main content
	  	 */
	  	val categorylist = ArtWebSiteDataSourceObject.querycategorypinxie(pagecontentcategory)
	  	
	  	/**
	  	 * make the page number list
	  	 */
	  	//get the entercounter worktype has how many works
	  	val workamount = ArtWebSiteDataSourceObject.queryartworkpagenumberrange(pagecontentcategory)(0).measures.toInt
	  	
	  	val pageamountyushu = workamount % eachpagehasnum match{
	  	  case 0 => 0
	  	  case _ => 1
	  	}
	  	
	  	
	  	var pageamountzongshu = workamount / eachpagehasnum + pageamountyushu
	  	if(pageamountzongshu == 0) pageamountzongshu = 1
	  	
	  	if(currentpagenumber > pageamountzongshu) redirect("/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/1")
	  	
	  	/**
	  	 * last page href
	  	 */
	  	
	  	val lastpagenumber = (currentpagenumber - 1)match{
	  	  case 0 => 1
	  	  case _ => currentpagenumber - 1
	  	} 
	  	val lastpagehref = "/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/"+ lastpagenumber
	  	
	  	/**
	  	 * next page href
	  	 */
	  	var nextpagenumber = currentpagenumber + 1
	  	if(nextpagenumber > pageamountzongshu) 
	  	  nextpagenumber = pageamountzongshu
	  	
	  	
	  	
	  	
	  	val nextpagehref = "/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/"+ nextpagenumber
	  	
	  	
	  	 
	  	
	  	
	    mustache("artshowlist.mustache","layout" -> "",
	        "newestwork" -> newestworkres,
	        "gallerylist" -> showworkres,
	        "categorylist" -> categorylist,
	        //page control
	        "pageamountzongshu" -> pageamountzongshu,
	        "currentpagenumber" -> currentpagenumber,
	        "lastpagehref" -> lastpagehref,
	        "nextpagehref" -> nextpagehref
	        
          )
  }
  
  get("/workshow/:workid"){
    contentType = "text/html"
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
	  	
      
      val workid = Integer.parseInt(params("workid"))
      
      
      
      val reslist = ArtWebSiteDataSourceObject.getworkbyid(workid)
      
      if(reslist.length == 0) redirect("/error")
      

      val res = reslist(0)
      
      
      
      
      
      
      mustache("artwork.mustache","layout" -> "",
          "newestwork" -> newestworkres,
	        "picname" -> res.name,
	        "imgsrc" -> res.imgsrc,
	        "desc" -> res.desc,
	        "author" -> res.author,
	        "category" -> res.category,
	        "price" -> res.price
          )
    
  }
  /**
   * 
   */
  get("/peopleshow/:category/listpage/:pagenumber"){
	  	contentType = "text/html"
      
      
      /**
	  	 * fix data for the footer
	  	 */
	  	val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
	  	/**
       * fix end
       */
	  	  
	  	val eachpagehasnum = 16
	  	
	  	val pagerefname = "listpage"
	  	val pagetyperefname = "peopleshow"
	  	  
	  	val showtype = params("category")
	  	  
	  	val currentpagenumber = Integer.parseInt(params("pagenumber"))
	  	
	  	if(currentpagenumber <= 0){
	  	  redirect("/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/1")
	  	}
	  	    
	  	
	  	
	  	
	  	val pagecontentcategory = showtype match{
	  	  case "guohua" => "G"
	  	  case "youhua" => "Y"
	  	  case "shufa" => "S"
	  	  case _ => "N"
	  	}
	  	
	  	if(pagecontentcategory == "N") redirect("/")
    	
    	
	  	
	  	/**
	  	 * main content
	  	 */
	  	val showpeoples = ArtWebSiteDataSourceObject.querypeoplelistbyworktype(pagecontentcategory,currentpagenumber - 1,eachpagehasnum)
	  	
	  	/**
	  	 * category list match main content
	  	 */
	  	val categorylist = ArtWebSiteDataSourceObject.querypeoplecategorylist(pagecontentcategory)
	  	
	  	/**
	  	 * make the page number list
	  	 */
	  	//get the entercounter worktype has how many works
	  	val workamount = ArtWebSiteDataSourceObject.querypeoplepagenumberrange(pagecontentcategory)(0).measures.toInt
	  	
	  	val pageamountyushu = workamount % eachpagehasnum match{
	  	  case 0 => 0
	  	  case _ => 1
	  	}
	  	
	  	var pageamountzongshu = workamount / eachpagehasnum + pageamountyushu
	  	if(pageamountzongshu == 0) pageamountzongshu = 1
	  	
	  	if(currentpagenumber > pageamountzongshu) redirect("/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/1")
//	  		redirect("/")
	  	
	  	/**
	  	 * last page href
	  	 */
	  	
	  	val lastpagenumber = (currentpagenumber - 1)match{
	  	  case 0 => 1
	  	  case _ => currentpagenumber - 1
	  	} 
	  	val lastpagehref = "/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/"+ lastpagenumber
	  	
	  	/**
	  	 * next page href
	  	 */
	  	var nextpagenumber = currentpagenumber + 1
	  	if(nextpagenumber > pageamountzongshu) 
	  	  nextpagenumber = pageamountzongshu
	  	
	  	
	  	
	  	
	  	val nextpagehref = "/"+pagetyperefname+"/"+showtype+"/"+pagerefname+"/"+ nextpagenumber
	  	
	  	
	  	 
	  	
	  	mustache("peopleshowlist.mustache","layout" -> "",
	  	    "newestwork" -> newestworkres,
	  	    "categorylist" -> categorylist,
	  	    "peoplelist" -> showpeoples,
	  	    //page control
	  	    "pageamountzongshu" -> pageamountzongshu,
	        "currentpagenumber" -> currentpagenumber,
	        "lastpagehref" -> lastpagehref,
	        "nextpagehref" -> nextpagehref
          
          )
    
  }
  
  get("/artist/:id"){
    contentType = "text/html"
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
      
      
      val artistid = Integer.parseInt(params("id"))
      
      
      val reslist = ArtWebSiteDataSourceObject.queryartistbyid(artistid)
      
      if(reslist.length == 0) redirect("/error")
      
    
      val artist = reslist(0)
      
      val authorpinxie = artist.authorpinxie
      
      
      
      
      val artistworklist = ArtWebSiteDataSourceObject.queryartistworkbyatuhorpinxie(authorpinxie)
      
      /**
       * peopleintroduce.mustache
       */
      mustache("peopleintroduce.mustache","layout" -> "",
          "newestwork" -> newestworkres,
          "title" -> artist.title,
          "author" -> artist.author,
          "authorpinxie" -> artist.authorpinxie,
          "desc" -> artist.desc,
          "artworklist" -> artistworklist
	  	    
          )
  }
  
  get("/error"){
    contentType = "text/html"
      
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.newestworksmallshow(15)
      
      
    mustache("error.mustache","layout" -> "",
        "newestwork" -> newestworkres
          
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
    get("/jsondata/test3"){
    contentType = formats("json")

    
//    ArtWebSiteDataSourceObject.querytest3
    ArtWebSiteDataSourceObject.queryartistworkbyatuhorpinxie("maxiaoyu")
	
    
  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
