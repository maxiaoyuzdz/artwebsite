package com.mxy.restfulservice.model

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import org.squeryl.dsl.ast.LogicalBoolean

import java.util.Date
import java.sql.Timestamp
import org.squeryl.KeyedEntity




//case class BestFamousPeopleObejct(
//    val id:Int,
//    val h0:String,
//    val h1:String,
//    val imgsrc:String,
//    val p:String,
//    val href:String,
//    val datatype:String) extends KeyedEntity[Int]{
//  
//  def this() = this(0,"","","","","","A")
//  
//}
/**
 * common work show
 */
case class WorkShowObject(
    val id:Int,
    val imgsrc:String,
    val name:String,
    val price:String,
    val worktype:String,
    val datatype:String,
    val desc:String,
    val category:String,
    val author:String,
    val authorpinxie:String,
    val categorypinxie:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","","A","some description","","","","")
}

/**
 * used for newest work
 */
case class HrefAndSmallImgsrcWorkShowObject(
     val id:Int,    
    val imgsrc:String
    )extends KeyedEntity[Int]{
  def this() = this(0,"")
}

//case class PagenumberControlObject(
//    val id:Int,
//    var iscurrentpage:Boolean,
//    var isnotcurrentpage:Boolean,
//    var href:String) extends KeyedEntity[Int]{
//  def this() = this(0,false,true,"")
//}

/**
 * used for people
 */
case class PeopleObject(
    val id:Int,
    val author:String,
    val authorpinxie:String,
    val worktype:String,
    val desc:String,
    val title:String,
    val category:String,
    val categorypinxie:String,
    val shortdesc:String,
    val videoref:String) extends KeyedEntity[Int]{
  def this() = this(0,"","","","","","","","","XNTczMDc2NTA4")
  
}
/**
 * used for order
 */
case class OrderFormObj(shouhuoren:String, 
    lianxidianhua:String,
    dianziyoujian:String,
    youbian:String,
    shouhuodizhi:String,
    zhifubao:String,
    beizhu:String,
    workid:Int)
    
case class WorkOrderObject(id:Int,
    shouhuoren:String, 
    lianxidianhua:String,
    dianziyoujian:String,
    youbian:String,
    shouhuodizhi:String,
    zhifubao:String,
    beizhu:String,
    workid:Int) extends KeyedEntity[Int]{
  def this() = this(0,"","","","","","","",0)
  
  def this(obj:OrderFormObj) = this(0,
      obj.shouhuoren,
      obj.lianxidianhua,
      obj.dianziyoujian,
      obj.youbian,
      obj.shouhuodizhi,
      obj.zhifubao,
      obj.beizhu,
      obj.workid)
}


object ArtWebSiteDataSourceObject extends Schema{
  
  
//  val bestfamouspeopletabledata = table[BestFamousPeopleObejct]("bestfamouspeopletable")
//  
//  on(bestfamouspeopletabledata)(item =>declare(
//      item.id	is(primaryKey, autoIncremented)
//      )
//  )
  
  /**
   * show art work
   */
  val workshowtable_data = table[WorkShowObject]("workshowtable")
  
  
  
  on(workshowtable_data)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  /**
   * show the newest work
   */
  val newestartworktable_data = table[HrefAndSmallImgsrcWorkShowObject]("workshowtable")
  
  on(newestartworktable_data)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  /**
   * show people
   */
  val peopletable_data = table[PeopleObject]("peopletable")
  
  on(peopletable_data)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  /**
   * table define end
   */
  /**
   * fix data
   */
  def queryNewestWorkForSmallShowing(length:Int) = from(newestartworktable_data)((w) =>  select((w)) orderBy(w.id desc)).page(0, length).toList
  
  
  /**
   * page: index
   * lemmon slider data
   */
  
  def queryLemmonSliderDataForShowingGoodWork = from(workshowtable_data)((item) =>  where(item.datatype === "A") select(item)).toList
  
  
  //bestfamouspeopeltable bestfamouspeopletable
  
  
  def queryNewestPeople = from(peopletable_data)((item) =>   select(item) orderBy(item.id desc)).page(0, 4).toList
  
  //
  
  
//  def querybestworkshowtable(rlevel:Int) = from(workshowtabledata)((item) =>  where((item.datatype === "A") and (item.level === rlevel) ) select(item)).toList
  
  /**
   * index
   * show 4 work for each catehory
   */
  def queryArtWorkByTypeAndLength(arttype:String, length:Int) = from(workshowtable_data)((item) => where(item.worktype === arttype) select(item) orderBy(item.id desc)).page(0, length).toList
  
  //
  
  
  
  
  //
  def queryArtWorkByTypeAndPageNumberAndLength(worktype:String, pagenumber:Int, pagelength:Int) = from(workshowtable_data)((item) => where(item.worktype === worktype) select(item) orderBy(item.id desc)).page(pagenumber * pagelength, pagelength).toList
  
  def queryArtWorkCategoryPinxie(worktype:String) = from(workshowtable_data)((item) => where(item.worktype === worktype) select(item.category,item.categorypinxie)).distinct.toList
  
  //page control
//  val pagenumbercontroldata = table[PagenumberControlObject]("workshowpagenumcontroltable")
//  on(pagenumbercontroldata)(item =>declare(
//      item.id	is(primaryKey, autoIncremented)
//      )
//  )
//  def querypagenum(limit:Int) = from(pagenumbercontroldata)(item => where(item.id lte limit) select(item)).toList
  
  def queryPageNumberRangeOfArtWork(worktype:String) = from(workshowtable_data)(item => where(item.worktype === worktype) compute(count(item.id))).toList
  //work show
  def queryArtWorkById(id:Int) = from(workshowtable_data)(item => where(item.id === id) select(item)).toList
      
  def queryArtistByAuthorPinxie(pinxie:String) = from(peopletable_data)(item => where(item.authorpinxie === pinxie) select(item)).toList
  
  def queryPeopleListByWorkType(worktype:String,pagenumber:Int,pagelength:Int) = from(peopletable_data)((item => where(item.worktype === worktype) select(item))).page(pagenumber * pagelength, pagelength).toList
  def queryPeopleCategoryList(worktype:String) = from(peopletable_data)((item) => where(item.worktype === worktype) select(item.category,item.categorypinxie)).distinct.toList
//  def queryPageNumberAmountOfPeopleList(worktype:String) = from(peopletable_data)(item => where(item.worktype === worktype) compute(count(item.id))).toList
  def queryPageNumberRangeOfPeopleList(worktype:String) = from(peopletable_data)(item => where(item.worktype === worktype) compute(count(item.id))).toList
  
  /**
   * show artist by id
   */
  def queryArtistById(id:Int) = from(peopletable_data)(item => where(item.id === id) select(item)).toList
  def queryArtistWorkByAtuhorPinxie(pinxie:String) = from(workshowtable_data)(item => where(item.authorpinxie === pinxie) select(item)).toList 
  
  
  //workordertable
  val workordertable_data = table[WorkOrderObject]("WorkOrderTable")
  
  on(workordertable_data)(item =>declare(
      item.id	is(primaryKey, autoIncremented)
      )
  )
  
  def insertWorkOrderTable(orderobj:OrderFormObj) = {

    
    workordertable_data.insert(new WorkOrderObject(orderobj))
        
    
  }
  
  
  // test for page
//  def querytest = from(workshowtabledata)((item) =>  select(item.id, item.href) orderBy(item.id desc)).page(0, 5).toList.map(item => (item._1, item._2.substring(0,9)))
  def querytest2 = from(workshowtable_data)(item => where(item.worktype === "G") compute(count(item.id))).toList
  def querytest3 = from(peopletable_data)((item => select(item))).toList
  
  

}