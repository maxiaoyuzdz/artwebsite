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
import com.mxy.restfulservice.model.OrderFormObj
//=========================================
// email
//=========================================




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
	  	  
	  	

	  	val sliderres = ArtWebSiteDataSourceObject.queryLemmonSliderDataForShowingGoodWork
	  	
	  	val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
//	  	
	  	val famouspeopleres = ArtWebSiteDataSourceObject.queryNewestPeople
//	  	
//	  	
//	  	
	  	val indexguohualistres = ArtWebSiteDataSourceObject.queryArtWorkByTypeAndLength("G",4)
//	  	
	  	val indexyouhualistres = ArtWebSiteDataSourceObject.queryArtWorkByTypeAndLength("Y",4)
//	  	
	  	val indexshufalistres = ArtWebSiteDataSourceObject.queryArtWorkByTypeAndLength("S",4)

	  	
	  	val ng = scala.util.Random
	    mustache("index.mustache","layout" -> "",
	        "randomversion" -> ng.nextInt,
	        
	        "lemmonsliderrepo" -> sliderres,
	        "newestwork" -> newestworkres,
	        "famouspeople" -> famouspeopleres,
//	        
	        
	        
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
	  	val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
	  	/**
	  	 * main content
	  	 */
	  	val showworkres = ArtWebSiteDataSourceObject.queryArtWorkByTypeAndPageNumberAndLength(pagecontentcategory,currentpagenumber - 1,eachpagehasnum)
	  	/**
	  	 * category list match main content
	  	 */
	  	val categorylist = ArtWebSiteDataSourceObject.queryArtWorkCategoryPinxie(pagecontentcategory)
	  	
	  	/**
	  	 * make the page number list
	  	 */
	  	//get the entercounter worktype has how many works
	  	val workamount = ArtWebSiteDataSourceObject.queryPageNumberRangeOfArtWork(pagecontentcategory)(0).measures.toInt
	  	
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
	  	
	  	
	  	 
	  	val ng = scala.util.Random
	  	
	    mustache("artshowlist.mustache","layout" -> "",
	        "randomversion" -> ng.nextInt,
	        
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
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
	  
      
      val workid = Integer.parseInt(params("workid"))
      
      
      
      val reslist = ArtWebSiteDataSourceObject.queryWorkById(workid)
      
      if(reslist.length == 0) redirect("/error")
      

      val res = reslist(0)
      
      val authorpinxie = res.authorpinxie
      
      
      val authoreslist = ArtWebSiteDataSourceObject.queryartistbyauthorpinxie(authorpinxie)
      
      var authorid = 0
      
      if(authoreslist.length != 0) authorid = authoreslist(0).id
      
      
      val ng = scala.util.Random
      
      
      mustache("artwork.mustache","layout" -> "",
          "randomversion" -> ng.nextInt,
          
          
          "newestwork" -> newestworkres,
	        "picname" -> res.name,
	        "imgsrc" -> res.imgsrc,
	        "desc" -> res.desc,
	        "author" -> res.author,
	        "category" -> res.category,
	        "price" -> res.price,
	        "picid" -> res.id,
	        "id" -> authorid
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
	  	val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
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
	  	
	  	
	  	 
	  	val ng = scala.util.Random
	  	
	  	mustache("peopleshowlist.mustache","layout" -> "",
	  	    "randomversion" -> ng.nextInt,
	  	    
	  	    
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
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
      
      
      val artistid = Integer.parseInt(params("id"))
      
      
      val reslist = ArtWebSiteDataSourceObject.queryartistbyid(artistid)
      
      if(reslist.length == 0) redirect("/notfoundartist")
      
    
      val artist = reslist(0)
      
      val authorpinxie = artist.authorpinxie
      
      
      
      
      val artistworklist = ArtWebSiteDataSourceObject.queryartistworkbyatuhorpinxie(authorpinxie)
      
      /**
       * peopleintroduce.mustache
       */
      val ng = scala.util.Random
      mustache("peopleintroduce.mustache","layout" -> "",
          "randomversion" -> ng.nextInt,
          
          "newestwork" -> newestworkres,
          "title" -> artist.title,
          "author" -> artist.author,
          "authorpinxie" -> artist.authorpinxie,
          "desc" -> artist.desc,
          "videoref" -> artist.videoref,
          "artworklist" -> artistworklist
          
	  	    
          )
  }
  
  get("/artist/searchby/:authorpinxie"){
    contentType = "text/html"
      val authorpinxieparameter = params("authorpinxie")
      
      //queryartistbyauthorpinxie
      val reslist = ArtWebSiteDataSourceObject.queryartistbyauthorpinxie(authorpinxieparameter)
      if(reslist.length == 0) redirect("/notfoundartist")
      val artist = reslist(0)
      
      val authorpinxie = artist.authorpinxie
      
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
	  
	  val artistworklist = ArtWebSiteDataSourceObject.queryartistworkbyatuhorpinxie(authorpinxie)
	  
	  /**
       * peopleintroduce.mustache
       */
	  val ng = scala.util.Random
	  
      mustache("peopleintroduce.mustache","layout" -> "",
          "randomversion" -> ng.nextInt,
          
          "newestwork" -> newestworkres,
          "title" -> artist.title,
          "author" -> artist.author,
          "authorpinxie" -> artist.authorpinxie,
          "desc" -> artist.desc,
          "artworklist" -> artistworklist
	  	    
          )
  }
  
  
  get("/buywork/:workid"){
    contentType = "text/html"
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
	  
      
      val workid = Integer.parseInt(params("workid"))
      
      
      
      val reslist = ArtWebSiteDataSourceObject.queryWorkById(workid)
      
      if(reslist.length == 0) redirect("/error")
      
      val res = reslist(0)
      
      
      val ng = scala.util.Random
      
      
      mustache("buywork.mustache","layout" -> "",
          "randomversion" -> ng.nextInt,
          
          
          "newestwork" -> newestworkres,
	        "picname" -> res.name,
	        "imgsrc" -> res.imgsrc,
	        "desc" -> res.desc,
	        "author" -> res.author,
	        "category" -> res.category,
	        "price" -> res.price,
	        "picid" -> res.id
          )
      
  }
  
  
  get("/error"){
    contentType = "text/html"
      
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
      
	  val ng = scala.util.Random

	  
      
    mustache("error.mustache","layout" -> "",
        "randomversion" -> ng.nextInt,
        
        "newestwork" -> newestworkres
          
          )
  }
  
    get("/notfoundartist"){
    contentType = "text/html"
      
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
      
	  val ng = scala.util.Random

	  
      
    mustache("notfoundartist.mustache","layout" -> "",
        "randomversion" -> ng.nextInt,
        
        "newestwork" -> newestworkres
          
          )
  }
    
    get("/buysuccess"){
    contentType = "text/html"
      
      /**
	  	 * fix data for the footer
	  	 */
	  val newestworkres = ArtWebSiteDataSourceObject.queryNewestWorkForSmallShowing(5)
      
	  val ng = scala.util.Random

	  
      
    mustache("buysuccessful.mustache","layout" -> "",
        "randomversion" -> ng.nextInt,
        
        "newestwork" -> newestworkres
          
          )
  }
    
    
    //===========================================================================================
    case class	TempRes(var res:Boolean)
  post("/jsondata/insertworkorder"){
    contentType = formats("json")
    val insertres = new TempRes(true)
    
    try{

      
    	val p1 = parse(request.body).extract[OrderFormObj]
    	
    	ArtWebSiteDataSourceObject.workordertable_insert(p1)

      
    }catch {
      case e:Exception =>
        e.printStackTrace()
        
        insertres.res = false
        
    }
    
    insertres
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
  

  /**
   * format convert, anything be converted to JSON
   */
  
//  before() {
//    contentType = formats("json")
//  }
  
    get("/jsondata/test1"){
	    contentType = formats("json")
	
	    ArtWebSiteDataSourceObject.queryArtWorkByTypeAndPageNumberAndLength("G",0,16)

    }
    
    get("/jsondata/test2"){
	    contentType = formats("json")
	
	    ArtWebSiteDataSourceObject.queryArtWorkByTypeAndPageNumberAndLength("G",1,16)

    }
    get("/jsondata/test3"){
	    contentType = formats("json")
	
	    ArtWebSiteDataSourceObject.queryArtWorkByTypeAndPageNumberAndLength("G",2,16)
	    
	    

    }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
