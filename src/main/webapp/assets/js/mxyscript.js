/**
 * Created with JetBrains WebStorm.
 * User: maxiaoyu
 * Date: 13-4-15
 * Time: 上午8:00
 * To change this template use File | Settings | File Templates.
 */
//ddlevelsmenu.setup("main_menu", "topbar")
/*================================================================*/
/*	DESKTOP MENU
 /*================================================================*/
if (document.documentElement.clientWidth > 767) { //if client width is greater than 767px

    ddsmoothmenu.init({
        mainmenuid: "main_menu",
        orientation: 'h',
        contentsource: "markup",
        showhidedelay: {showdelay: 300, hidedelay: 100} //set delay in milliseconds before sub menus appear and disappear, respectively
    })

}

window.onload = function(){

    // slider 1
    $( '#lemmon-slider' ).lemmonSlider({
        infinite: true
    });
//    alert('OK');

}

