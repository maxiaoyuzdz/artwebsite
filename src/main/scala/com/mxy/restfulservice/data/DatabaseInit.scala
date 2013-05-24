package com.mxy.restfulservice.data

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.slf4j.LoggerFactory


trait DatabaseInit {
  
  val logger = LoggerFactory.getLogger(getClass)

//  val databaseUsername = "root"
//  val databasePassword = "root"
//  val databaseConnection = "jdbc:mysql://localhost:3306/artwebsitedb"
    
    val databaseUsername = "root"
    val databasePassword = "zdz5973781"
    val databaseConnection = "jdbc:mysql://10.10.3.25:3306/artwebsitedb"
      
      
//  val databaseConnection = "jdbc:mysql://218.245.3.163:3306/artwebsitedb"
//    val databaseConnection = "jdbc:mysql://localhost:3306/artwebsitedb"
    //artwebsitedb

  var cpds = new ComboPooledDataSource

  def configureDb() {
    cpds.setDriverClass("com.mysql.jdbc.Driver")
    cpds.setJdbcUrl(databaseConnection)
    cpds.setUser(databaseUsername)
    cpds.setPassword(databasePassword)

    cpds.setMinPoolSize(1)
    cpds.setAcquireIncrement(1)
    cpds.setMaxPoolSize(50)

    SessionFactory.concreteFactory = Some(() => connection)

    def connection = {
      logger.info("Creating connection with c3po connection pool")
      Session.create(cpds.getConnection, new MySQLAdapter)
    }
  }

  def closeDbConnection() {
    logger.info("Closing c3po connection pool")
    cpds.close()
  }

}