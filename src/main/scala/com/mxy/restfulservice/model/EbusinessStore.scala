package com.mxy.restfulservice.model

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import java.util.Date
import java.sql.Timestamp
import org.squeryl.KeyedEntity


//case class ProductCategoryTable(val id:Int, 
//    val name:String,
//    val parentId:Int, 
//    val parentName:String) extends KeyedEntity[Int]{
//  def this() = this(0,"",0,"")
//}

case class PropertyCategoryTable(val id:Int, 
    val name:String,
    val productCategoryId:Int, 
    val productCategoryName:String) extends KeyedEntity[Int]{
  def this() = this(0,"",0,"")
}

case class ProductPropertyTable(val id:Long, 
    val name:String, 
    val propertyId:Int, 
    val propertyName:String, 
    val propertyValue:String, 
    val categoryId:Int, 
    val categoryName:String) extends KeyedEntity[Long] {
  def this() = this(0,"",0,"","",0,"")
}


case class ProductInfoTable(val id:Int,
    val name:String,
    val info:String) extends KeyedEntity[Int]{
  def this() = this(0,"","")
}
case class ProductInfoTableChange(id: Int, name: String, info: String)


object EbusinessStore extends Schema{
  /**
   * Table define
   */
  /**
   * 
   */
//  val productCategories = table[ProductCategoryTable]("productcategorytable")
  
  
  
//  on(productCategories)(item =>declare(
//      item.id	is(primaryKey, autoIncremented)
//      )
//  )
  
  /**
   * 
   */
  val propertyCategories = table[PropertyCategoryTable]("propertycategorytable")
  
  on(propertyCategories)(item => declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  /**
   * 
   */
  val productPropeties = table[ProductPropertyTable]("productpropertytable")
  
  on(productPropeties)(item => declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  /**
   * 
   */
  val productInfos = table[ProductInfoTable]("productinfotable")
  on(productInfos)(item => declare(
      item.id	is(primaryKey, autoIncremented)
      ))
  
  
  /**
   * Table define end
   */
  
  
  
//  def insertcategory = {
////    println("step2")
//    productCategories.insert(new ProductCategoryTable(0,"c1",0,"c2"))
//  }
  //from(categorylisttable)((item) => select(item) orderBy(item.parentId asc)).toList
  
//  def querycategory = from(productCategories)((item) => select(item)).toList
  
  //===========================================================================================
  def inserproperty = {
    propertyCategories.insert(new PropertyCategoryTable(0,"p1",21,"c1"))
  }
  
  def queryproperty = from(propertyCategories)((item) => select(item)).toList
  
  //===========================================================================================
  def insertproductproperty = {
    productPropeties.insert(new ProductPropertyTable(0,"p1",0,"p1","w3e4r",1,"c1"))
  }
  
  def queryproductproperty = from(productPropeties)(item => select(item)).toList
  
  //===========================================================================================

  
  def insertproductinfo(pingo:ProductInfoTable) = {
    productInfos.insert(pingo)
  }
  
  def queryproductinfo = from(productInfos)(item => select(item)).toList
  //===========================================================================================

}