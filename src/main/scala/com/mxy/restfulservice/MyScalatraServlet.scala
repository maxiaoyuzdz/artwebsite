package com.mxy.restfulservice

import org.scalatra._
import scalate.ScalateSupport
//==================================================
import net.liftweb.json.compact
import net.liftweb.json.render
import net.liftweb.json.JsonDSL._
import net.liftweb.json.Serialization.{read,write}
import net.liftweb.json.Serialization
import net.liftweb.json.NoTypeHints

import org.json4s._
import org.json4s.jackson.JsonMethods._
//==================================================
import org.scalatra.json._
//==================================================
import grizzled.slf4j.Logger
//==================================================
import com.mxy.restfulservice.data.DatabaseSessionSupport

//==================================================
// Data source Begin
//==================================================
import com.mxy.restfulservice.model.ArtWebSiteDataSourceObject
//import com.mxy.restfulservice.model.EbusinessStore
//==================================================
// Data source End
//==================================================



class MyScalatraServlet extends RestfulserviceStack with ScalateSupport with JacksonJsonSupport with DatabaseSessionSupport{
  
  val logger = Logger(classOf[MyScalatraServlet]);
  
  //redirect default address to "/"
  get("/index") {
    redirect("/")
  }
  get("/index.html") {
    redirect("/")
  }
  

//  proecess "/" page . one get, one page 
  get("/") {
	  	contentType = "text/html"

	    mustache("index.mustache","layout" -> "",
	        "pagename" -> "what",
	        "parametervalue" -> 33
          )
  }
  
  
  /**
   * json data interface
   */
  get("/jsondata/querylemmonsliderdata"){
    contentType = formats("json")
    ArtWebSiteDataSourceObject.querylemmonslidertable

  }
  
  /**
   * Original Html Code output
   */
  get("/testhtml"){
    contentType = "text/html"
  /**
   * Html Begin
   */

<html lang="zh-CN">
<head>
    <title>泽雅斋--专业的艺术作品交流平台</title>

    <!-- Meta Data ================ -->
    <meta charset="UTF-8" />
    <meta name="description" content="This is a art work website" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <!-- CSS ================ -->
    <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="assets/css/bootstrap-responsive.css" type="text/css"/>
    <!-- CSS ================ -->
    <!--<link rel="stylesheet" href="assets/css/style.css" type="text/css"/>-->
    <!--<link rel="stylesheet" href="assets/css/header-1.css" type="text/css"/>-->
    <!-- CSS ================ -->
    <link rel="stylesheet" href="assets/css/global.css" type="text/css"/>
    <link rel="stylesheet" href="assets/css/globalmobile.css" type="text/css"/>
    <link rel="stylesheet" href="assets/css/globalheader.css" type="text/css"/>
    <link rel="stylesheet" href="assets/css/globalheadermobile.css" type="text/css"/>
    <!-- CSS ================ -->


    <!-- lemmon slider variable width slider for images -->
    <link rel="stylesheet" href="assets/css/lemmon-slider.css" type="text/css" media="screen" />

    <!-- Icons ================ -->
    <!-- For non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->
    <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png" />
    <!-- For first- and second-generation iPad: -->
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="apple-touch-icon-72x72-precomposed.png" />
    <!-- For iPhone with high-resolution Retina display: -->
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="apple-touch-icon-114x114-precomposed.png" />
    <!-- For third-generation iPad with high-resolution Retina display: -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="apple-touch-icon-144x144-precomposed.png" />
    <link rel="shortcut icon" href="favicon.ico" />




    <!-- JS JQuery in the head you can use ajax if you want ================ -->
    <script src="assets/js/jquery.js"></script>
    <!-- Add to Head after Style sheet http://modernizr.com/docs/#installing  === 87724 ============= modernizr.custom.62887 -->
    <script src="assets/js/modernizr.custom.87724.js"></script><!-- js multiple backgrounds svg background size -->

</head>
<body class="tall-logo">



<!-- mobile navigation trigger-->
<h5 class="mobile_nav"><a href="javascript:void(0)">&nbsp;<span></span></a></h5>
<!--end mobile navigation trigger-->

<section class="container preheader">

    <!--this is the login for the user-->
    <nav class="user clearfix">
        <a href="login.html">用户登陆</a>
    </nav>
    <!--close user nav-->
    <!-- need to fix -->
    <div class="search-wrapper">
        <form class="search" method="post" action="someaction.php">
            <div id="search-trigger">搜索:</div>
            <input id="search-box" type="text" placeholder="搜索 + 回车" />
        </form>
    </div>

    <div class="phone "><a href="tel:05318888888" class="tele">联系电话：05318888888</a></div>
    <!---->



</section>


<!-- begin .header-->
<header class="header  clearfix">
    <img src="assets/images/print-logo.png" class="print logo" alt="name of company" />
    <div class="container">

        <!-- begin #main_menu -->
        <nav id="main_menu">
            <ul class="accordmobile">
                <li class="active"><a href="indexmain.html">首页</a></li>
                <li class="parent"><a href="javascript:void(0)">作品展销<i></i></a>
                    <ul>
                        <li><a href="tall-logo.html">国画</a></li>
                        <li><a href="alternative-header.html">书法</a></li>
                        <li><a href="columns.html">油画</a></li>

                    </ul>
                </li>
                <li class="parent"><a href="javascript:void(0)">名家介绍<i></i></a>
                    <ul>
                        <li><a href="portfolio-masonry.html">国画家</a></li>
                        <li><a href="portfolio-lightbox.html">书法家</a></li>
                        <li><a href="portfolio-single.html">油画家</a></li>

                    </ul>
                </li>
                <li class="parent"><a href="javascript:void(0)">媒体文章<i></i></a>
                    <ul>
                        <li><a href="blog-traditional-summary.html">Traditional Summary</a></li>

                    </ul>
                </li>
                <li class="parent"><a href="javascript:void(0)">展览活动<i></i></a>
                    <ul>
                        <li class="active"><a href="indexmain.html">Home 1 Lemmon</a></li>

                    </ul>
                </li>
                <li class="parent"><a href="javascript:void(0)">特色服务<i></i></a>
                    <ul>
                        <!--<li><a href="blog-traditional-summary.html">退货服务</a></li>-->
                        <li><a href="blog-traditional-summary-3col.html">鉴宝服务</a></li>
                        <!--<li><a href="blog-traditional-summary-3col.html">收购服务</a></li>-->
                    </ul>
                </li>
                <li><a href="contact.html">联系方式</a></li>
            </ul>
        </nav>
        <!-- close / #main_menu -->

        <!-- begin #logo -->
        <div id="logo"> <a href="indexmain.html"><img alt="" src="assets/images/trans.gif" /><em>Crisp Responsive HTML Retina Ready Bootstrap Goodness</em><!--effing ie7 support--></a> </div>
        <!-- end #logo -->

    </div>
    <!-- close / .container-->
</header>
<!-- close /.header -->
<!---->

<div class="hero-unit center no-border no-padding-bottom ">

    <div class="row-fluid clearfix small_content_show_area ">
        <div class="span4">
            <h3>最新展览</h3>
        </div>
        <div class="span4">
            <h3>最新展览</h3>
        </div>
        <div class="span4">
            <h3>最新展览</h3>
        </div>
    </div>
    <div class="row-fluid clearfix small_content_show_area">
        <div class="span4">
            <h3>最新展览</h3>
        </div>
        <div class="span4">
            <h3>最新展览</h3>
        </div>
        <div class="span4">
            <h3>最新展览</h3>
        </div>
    </div>

</div>
<!-- begin #page - the container for everything but header -->
<!---->
<div id="page">


<!--begin slider -->
<div class="lemmon-wrap clearfix">
    <div id="lemmon-slider" class="lemmon-slider">
        <ul>
            <li> <a href="portfolio-single.html">
                <!--link to article/post/portfolio entry for the entire image-->
                <img src="/assets/showwork/drawshow1.jpg" alt="" /> </a>
                <!--end image link-->
                <div class="summary">
                    <!--begin summary-->
                    <a href="portfolio-single.html">
                        <!--begin link to article post or portfolio entry for the caption-->
                        <h3>大灰狼</h3>
                        <p>国画</p>
                    </a>
                    <!--end link to article post or portfolio entry for the caption-->
                </div>
                <!--end summary-->
            </li>
            <li> <a href="portfolio-single.html"> <img src="/assets/showwork/drawshow2.jpg" alt="" /></a>
                <div class="summary"> <a href="portfolio-single.html">
                    <h3>马晓宇</h3>
                    <p>书法</p>
                    </a>
                </div>
            </li>
            <li> <a href="portfolio-single.html"> <img src="/assets/showwork/drawshow3.jpg" alt="" /></a>
                <div class="summary"> <a href="portfolio-single.html">
                    <h3>战神</h3>
                    <p>油画</p>
                    </a>
                </div>
            </li>
            <li> <a href="portfolio-single.html"> <img src="/assets/showwork/drawshow4.jpg" alt="" /></a>
                <div class="summary"> <a href="portfolio-single.html">
                    <h3>Mona Lisa</h3>
                    <p>油画</p>
                    </a>
                </div>
            </li>
            <li> <a href="portfolio-single.html"> <img src="/assets/showwork/drawshow5.jpg" alt="" /></a>
                <div class="summary"> <a href="portfolio-single.html">
                    <h3>大灰狼</h3>
                    <p>油画</p>
                    </a>
                </div>
            </li>

            <li> <a href="portfolio-single.html"> <img src="/assets/showwork/drawshow7.jpg" alt="" /></a>
                <div class="summary"> <a href="portfolio-single.html">
                    <h3>大灰狼</h3>
                    <p>油画</p>
                    </a>
                </div>
            </li>
            <li>
                <a href="portfolio-single.html"> <img src="/assets/showwork/drawshow8.jpg" alt="" /></a>
                <div class="summary"> <a href="portfolio-single.html">
                    <h3>大灰狼</h3>
                    <p>油画</p>
                    </a>
                </div>
            </li>
        </ul>
    </div>

    <div class="controls">
        <a href="#" class="prev-page">Prev Page</a>
        <a href="#" class="next-page">Next Page</a>
    </div>
    <!-- / .controls -->

</div>
<!-- / end slider -->

<!--notice how sliders are outside the this main content div-->
<div class="container clearfix" id="main-content" >
    <h3 class="short_headline"><span>签约名家推荐</span></h3>
    <div class="row-fluid">


        <div class="span3">
            <div class="well">
                <h2>著名国画家</h2>
                <h3>马晓宇</h3>
                <p> <img class="alignleft" src="../demo/80x80-Diego_Velazquez_028.png" alt="Velazquez" /> 字什么，山东某某人，耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么</p>
                <p class="right"><a href="#" class="btn-primary btn no-under-line-btn">详细资料 &nbsp;<i class="icon-circle-arrow-right icon-white"></i></a></p>
            </div>
            <!--close .well -->
        </div>

        <div class="span3">
            <div class="well">
                <h2>著名书法家</h2>
                <h3>马晓宇</h3>
                <p> <img class="alignleft" src="../demo/80x80-Diego_Velazquez_028.png" alt="Velazquez" /> 字什么，山东某某人，耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么</p>
                <p class="right"><a href="#" class="btn-primary btn no-under-line-btn">详细资料 &nbsp;<i class="icon-circle-arrow-right icon-white"></i></a></p>
            </div>
            <!--close .well -->
        </div>
        <!--close span4-->
        <div class="span3">
            <div class="well">
                <h2>著名瓷器家</h2>
                <h3>马晓宇</h3>
                <p> <img class="alignleft" src="../demo/80x80-Diego_Velazquez_028.png" alt="Velazquez" /> 字什么，山东某某人，耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么</p>
                <p class="right"><a href="#" class="btn-primary btn no-under-line-btn">详细资料 &nbsp;<i class="icon-circle-arrow-right icon-white"></i></a></p>
            </div>
            <!--close .well -->
        </div>
        <!--close span4-->
        <div class="span3">
            <div class="well">
                <h2>著名油画家</h2>
                <h3>马晓宇</h3>
                <p> <img class="alignleft" src="../demo/80x80-Diego_Velazquez_028.png" alt="Velazquez" />字什么，山东某某人，耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么耕耘什么什么什么</p>
                <p class="right"><a href="#" class="btn-primary btn no-under-line-btn">详细资料 &nbsp;<i class="icon-circle-arrow-right icon-white"></i></a></p>
            </div>
            <!--close .well -->
        </div>
        <!--close span4-->




    </div>
    <!-- end row-fluid-->

    <h3 class="short_headline"><span>力作推荐</span></h3>
    <!-- begin equalHeights columns 3 -->
    <div class="row-fluid equalHero">
        <div class="span4"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
        <div class="span4"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4-->
        <div class="span4"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>
        </div>
        <!-- close .span4 -->
    </div>
    <!-- close #equalHeights .featured 3 columns -->

    <h3 class="short_headline"><span>精品展销</span></h3>


    <div class="row-fluid equalHero">
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4-->
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
    </div>

    <!-- close #equalHeights .featured 3 columns -->
    <div class="row-fluid equalHero">
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <div></div>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4-->
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
    </div>

    <!-- close #equalHeights .featured 3 columns -->
    <div  class="row-fluid equalHero">
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <div></div>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4-->
        <div class="span3"> <a href="about.html"><img src="/assets/showwork/drawshow1.jpg" class="aligncenter" alt="" /></a>
            <h3><a href="about.html">兰花图</a></h3>
            <p>价格：56000RMB</p>

        </div>
        <!-- close .span4 -->
    </div>

</div>
<!--close .container role="main-content" -->

<!--begin footer -->
<footer id="footer" class="clearfix">
    <div class="container"><!--footer container-->

        <div class="row-fluid">
            <div class="span4">
                <section>
                    <h4>联系我们</h4>
                    <p>Crisp Creative<br />
                        1255 Nowhere Street<br />
                        Tampa, FL 33655<br />
                        <strong>电话:</strong> <a href="tel:8135551234" class="tele">813.555.1234</a><br />
                        <strong>传真:</strong> 813.555.1235<br />
                        <strong>电子邮件:</strong>
                        <a href="mailto:email@domain.com">crisp@companydomain.com</a>
                    </p>
                </section>
                <!--close section-->


            </div>
            <!-- close .span4 -->

            <!--section containing newsletter signup and recent images-->
            <div class="span4">
                <section>
                    <h4>订阅更新</h4>
                    <p>输入邮箱地址订阅本站最新内容. 我们严格遵守国家法律不会泄漏您的个人信息.</p>
                    <form action="yourscript.php" method="post">
                        <div class="input-append">
                            <input type="email" placeholder="Email Address" class="span6" name="email" />
                            <button class="btn btn-primary">Sign Up</button>
                        </div>
                        <!--close input-append-->
                    </form>
                </section>
                <!--close section-->

                <section>
                    <h4>最新的作品</h4>
                    <ul class="image-widget clearfix">
                        <li><a href="#"><img src="../demo/image-widget/1.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/2.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/3.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/4.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/5.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/6.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/7.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/8.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/9.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/10.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/11.png" alt="" /></a></li>
                        <li><a href="#"><img src="../demo/image-widget/12.png" alt="" /></a></li>
                    </ul>
                </section>
                <!--close section-->
            </div>
            <!-- close .span4 -->

            <!--section containing blog posts-->
            <div class="span4">
                <section>
                    <h4>最新的相关文章</h4>
                    <ul class="footerPosts">
                        <li><a href="blog-single.html">著名艺术家巴拉巴拉</a> <span class="meta">2012/01/23</span> </li>
                        <li><a href="blog-single.html">某某艺术节开幕</a> <span class="meta">2012/01/23</span> </li>
                        <li><a href="blog-single.html">产销开始</a> <span class="meta">2012/01/23</span> </li>
                        <li><a href="blog-single.html">卖大白菜</a> <span class="meta">2012/01/23</span> </li>
                        <li><a href="blog-single.html">不卖给你</a> <span class="meta">2012/01/23</span> </li>
                        <li><a href="blog-single.html">收钱了</a> <span class="meta">2012/01/23</span> </li>
                    </ul>
                </section>
            </div>
            <!-- close .span4 -->
        </div>
        <!-- close .row-fluid-->
    </div>
    <!-- close footer .container-->

    <!--change this to your stuff-->
    <section class="footerCredits">
        <div class="container">
            <ul class="clearfix">
                <li>? 2013 道雅斋艺术有限公司. 保有本站所有权利.</li>
                <li><a href="sitemap.html">本站地图</a></li>
                <li><a href="privacy.html">隐私声明</a></li>
            </ul>
        </div>
        <!--footerCredits container-->
    </section>
    <!--close section-->
</footer>
<!--/.footer-->

<span class="backToTop"><a href="#top">back to top</a></span>



</div>
<!-- close #page-->
<!-- JS JQuery ================ -->
<script src="assets/js/bootstrap.js"></script><!--transitions, tooltips, popover, buttons, modals, alert messages-->
<script src="assets/js/ddsmoothmenu-min.js"></script><!-- desktop edge detect menu -->
<script src="assets/js/jquery.dcjqaccordion.2.7.min.js"></script><!-- mobile multi-level accordion menu -->
<script src="assets/js/jquery.easytabs.min.js"></script><!-- tabs/testimonials -->
<script src="assets/js/slide-to-top-accordion-min.js"></script><!-- slide to top accordion toggle -->
<script src="assets/js/jquery.easing-1.3.min.js"></script><!--easing-->
<script src="assets/js/jquery.flexslider-min.js"></script><!--flexslider content slider twitter slider and initializations-->
<script src="assets/js/responsive-tables.js"></script><!--responsive table-->
<script src="assets/js/jquery.fitvid.js"></script><!-- responsive videos -->

<script src="assets/js/lemmon-slider-min.js"></script><!-- lemmon slider js -->

<!--initialize scripts / custom scripts all pages-->
<script src="assets/js/scripts.js"></script>
</body>

</html>
      
/**
 * Html end
 */
  }
  
  

  
  
  /**
   * format convert, anything be converted to JSON
   */
//  before() {
//    contentType = formats("json")
//  }
  
  protected implicit val jsonFormats: Formats = DefaultFormats
}
