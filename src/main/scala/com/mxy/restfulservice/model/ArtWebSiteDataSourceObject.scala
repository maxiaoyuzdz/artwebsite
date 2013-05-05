package com.mxy.restfulservice.model

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
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



object ArtWebSiteDataSourceObject extends Schema{
  //lemmonslidertable
  val lemmonslidertabledata = table[LemmonSliderObeject]("bestlemmonslidertable")
  
  on(lemmonslidertabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querylemmonslidertable = from(lemmonslidertabledata)((item) =>  where(item.datatype === "A") select(item)).toList
  
  //bestfamouspeopeltable bestfamouspeopletable
  val bestfamouspeopletabledata = table[BestFamousPeopleObejct]("bestfamouspeopletable")
  
  on(bestfamouspeopletabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querybestfamouspeopletable = from(bestfamouspeopletabledata)((item) =>  where(item.datatype === "A") select(item)).toList
  

  

}