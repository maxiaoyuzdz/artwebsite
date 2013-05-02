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
    val slidertype:String){
  def this() = this(0,"","","","","A")
}

object ArtWebSiteDataSourceObject extends Schema{
  //lemmonslidertable
  val lemmonslidertable = table[LemmonSliderObeject]("lemmonslidertable")
  
  on(lemmonslidertable)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querylemmonslidertable = from(lemmonslidertable)((item) => where(item.slidertype === "A") select(item)).toList
  

}