package com.mxy.restfulservice.data

import org.squeryl.Session
import org.squeryl.SessionFactory
import org.scalatra._

object DatabaseSessionSupport {
  val key = {
    val n = getClass.getName
    if (n.endsWith("$")) n.dropRight(1) else n
  }

}

trait DatabaseSessionSupport {this: ScalatraBase =>
  import DatabaseSessionSupport._

  def dbSession = request.get(key).orNull.asInstanceOf[Session]

  before() { 
    request(key) = SessionFactory.newSession 
//    println("new db session")
    dbSession.bindToCurrentThread 
  }

  after() {
    dbSession.close
//    println("close db session")
    dbSession.unbindFromCurrentThread
  }

}