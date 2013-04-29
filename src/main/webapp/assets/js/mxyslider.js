/**
 * Created with JetBrains WebStorm.
 * User: maxiaoyu
 * Date: 13-4-16
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
//window.onload = function(){
//
//    // slider 1
//    $( '#lemmon-slider' ).lemmonSlider({
//        infinite: true
//    });
////    alert('OK');
//
//}

window.onload = function(){

    $( '#lemmon-slider' ).lemmonSlider({
        infinite: true,
        loop: false
    });

}

$(window).load(function(){
    $('.lemmon-slider ul li').hover(function() {
        $('.lemmon-slider ul li').not($(this)).stop().animate({
            opacity: .6
        }, 200);
    }, function() {
        $('.lemmon-slider ul li').stop().animate({
            opacity: 1
        });
    }, 200);

});