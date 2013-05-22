package com.mxy.restfulservice.model

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import org.squeryl.dsl.ast.LogicalBoolean

import java.util.Date
import java.sql.Timestamp
import org.squeryl.KeyedEntity


case class LemmonSliderObeject(
    val id:Int,
    val href:String,
    val imgsrc:String,
    val h:String,
    val p:String,
    val datatype:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","","A")
}

case class BestFamousPeopleObejct(
    val id:Int,
    val h0:String,
    val h1:String,
    val imgsrc:String,
    val p:String,
    val href:String,
    val datatype:String) extends KeyedEntity[Int]{
  
  def this() = this(0,"","","","","","A")
  
}

case class WorkShowObject(
    val id:Int,
    val href:String,
    val imgsrc:String,
    val smallimgsrc:String,
    val name:String,
    val price:Int,
    val level:Int,
    val worktype:String,
    val datatype:String,
    val desc:String,
    val category:String,
    val author:String,
    val authorpinxie:String,
    val categorypinxie:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","",0,5,"","A","some description","","","","")
}

case class HrefAndSmallImgsrcWorkShowObject(
     val id:Int,
    val href:String,
    
    val smallimgsrc:String
    )extends KeyedEntity[Int]{
  def this() = this(0,"","")
}

case class PagenumberControlObject(
    val id:Int,
    var iscurrentpage:Boolean,
    var isnotcurrentpage:Boolean,
    var href:String) extends KeyedEntity[Int]{
  def this() = this(0,false,true,"")
}


case class PeopleObject(
    val id:Int,
    val author:String,
    val authorpinxie:String,
    val worktype:String,
    val desc:String,
    val title:String,
    val category:String,
    val categorypinxie:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","","","","")
  
}


object ArtWebSiteDataSourceObject extends Schema{
  //lemmonslidertable
  val lemmonslidertabledata = table[LemmonSliderObeject]("bestlemmonslidertable")
  
  on(lemmonslidertabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querylemmonslidertable = from(lemmonslidertabledata)((item) =>  where(item.datatype === "A") select(item)).toList
  def querylemmonslidertable1 = from(lemmonslidertabledata)((item) =>  where(item.id === 1) select(item)).toList
  def querylemmonslidertable2 = from(lemmonslidertabledata)((item) =>  where(item.id === 2) select(item)).toList
  def querylemmonslidertable3 = from(lemmonslidertabledata)((item) =>  where(item.id === 3) select(item)).toList
  def querylemmonslidertable4 = from(lemmonslidertabledata)((item) =>  where(item.id === 4) select(item)).toList
  def querylemmonslidertable5 = from(lemmonslidertabledata)((item) =>  where(item.id === 5) select(item)).toList
  def querylemmonslidertable6 = from(lemmonslidertabledata)((item) =>  where(item.id === 6) select(item)).toList
  
  //bestfamouspeopeltable bestfamouspeopletable
  val bestfamouspeopletabledata = table[BestFamousPeopleObejct]("bestfamouspeopletable")
  
  on(bestfamouspeopletabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querybestfamouspeopletable = from(bestfamouspeopletabledata)((item) =>  where(item.datatype === "A") select(item)).toList
  
  //
  val workshowtabledata = table[WorkShowObject]("workshowtable")
  
  
  
  on(workshowtabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querybestworkshowtable(rlevel:Int) = from(workshowtabledata)((item) =>  where((item.datatype === "A") and (item.level === rlevel) ) select(item)).toList
  
  
  def queryartfromworkshowtable(arttype:String,length:Int) = from(workshowtabledata)((item) => where(item.worktype === arttype) select(item) orderBy(item.id desc)).page(0, length).toList
  
  //
  val hrefandsmallimgsrcworkshowobjecttabledata = table[HrefAndSmallImgsrcWorkShowObject]("workshowtable")
  
  on(lemmonslidertabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def newestworksmallshow(length:Int) = from(hrefandsmallimgsrcworkshowobjecttabledata)((w) =>  select((w)) orderBy(w.id desc)).page(0, length).toList
  
  //
  def querywork(worktype:String,pagenumber:Int,pagelength:Int) = from(workshowtabledata)((item) => where(item.worktype === worktype) select(item) orderBy(item.id desc)).page(pagenumber, pagelength).toList
  
  def querycategorypinxie(worktype:String) = from(workshowtabledata)((item) => where(item.worktype === worktype) select(item.category,item.categorypinxie)).distinct.toList
  
  //page control
  val pagenumbercontroldata = table[PagenumberControlObject]("workshowpagenumcontroltable")
  on(pagenumbercontroldata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  def querypagenum(limit:Int) = from(pagenumbercontroldata)(item => where(item.id lte limit) select(item)).toList
  
  def queryartworkpagenumberrange(worktype:String) = from(workshowtabledata)(item => where(item.worktype === worktype) compute(count(item.id))).toList
  //work show
  def getworkbyid(id:Int) = from(workshowtabledata)(item => where(item.id === id) select(item)).toList
      
  //people
  val peopleobjecttabledata = table[PeopleObject]("peopletable")
  
  on(peopleobjecttabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querypeoplelistbyworktype(worktype:String,pagenumber:Int,pagelength:Int) = from(peopleobjecttabledata)((item => where(item.worktype === worktype) select(item))).page(pagenumber, pagelength).toList
  def querypeoplecategorylist(worktype:String) = from(peopleobjecttabledata)((item) => where(item.worktype === worktype) select(item.category,item.categorypinxie)).distinct.toList
  def querypeoplelistpagenumberamount(worktype:String) = from(peopleobjecttabledata)(item => where(item.worktype === worktype) compute(count(item.id))).toList
  def querypeoplepagenumberrange(worktype:String) = from(peopleobjecttabledata)(item => where(item.worktype === worktype) compute(count(item.id))).toList
  
  // test for page
  def querytest = from(workshowtabledata)((item) =>  select(item.id, item.href) orderBy(item.id desc)).page(0, 5).toList.map(item => (item._1, item._2.substring(0,9)))
  def querytest2 = from(workshowtabledata)(item => where(item.worktype === "G") compute(count(item.id))).toList
  def querytest3 = from(peopleobjecttabledata)((item => select(item))).toList
  
  

}