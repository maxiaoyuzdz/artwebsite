import com.mxy.restfulservice._
import org.scalatra._
import javax.servlet.ServletContext

import com.mxy.restfulservice.data.DatabaseInit

class ScalatraBootstrap extends LifeCycle with DatabaseInit{
  override def init(context: ServletContext) {
    configureDb()
    context.mount(new MyScalatraServlet, "/*")
  }
  
  override def destroy(context:ServletContext) {
    closeDbConnection()
  }
}
