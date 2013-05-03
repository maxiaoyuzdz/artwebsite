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
    val slidertype:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","","A")
}


//case class ProductCategoryTable(val id:Int, 
//    val name:String,
//    val parentId:Int, 
//    val parentName:String) extends KeyedEntity[Int]{
//  def this() = this(0,"",0,"")
//}


object ArtWebSiteDataSourceObject extends Schema{
  //lemmonslidertable
  val lemmonslidertabledata = table[LemmonSliderObeject]("lemmonslidertable")
  
  on(lemmonslidertabledata)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def querylemmonslidertable = from(lemmonslidertabledata)((item) =>  where(item.slidertype === "A") select(item)).toList
  
  ///
//  val productCategories = table[ProductCategoryTable]("productcategorytable")
//  
//  
//  
//  on(productCategories)(item =>declare(
//      item.id	is(primaryKey, autoIncremented)
//      )
//  )
//  
//  def querycategory = from(productCategories)((item) => select(item)).toList
  

}