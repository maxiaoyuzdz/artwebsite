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
    val catehorypinxie:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","",0,5,"","A","some description","","","","")
}

case class HrefAndSmallImgsrcWorkShowObject(
     val id:Int,
    val href:String,
    
    val smallimgsrc:String
    )extends KeyedEntity[Int]{
  def this() = this(0,"","")
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
  
  
  // test for page
  def querytest = from(workshowtabledata)((item) =>  select(item.id, item.href) orderBy(item.id desc)).page(0, 5).toList.map(item => (item._1, item._2.substring(0,9)))
  

}