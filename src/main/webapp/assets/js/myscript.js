/**
 * Created with JetBrains WebStorm.
 * User: maxiaoyu
 * Date: 13-6-4
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */


$(function(){
    $("#submitorderbtn").on("click",function(){
        var json = '{"person" : {"age" : 20, "name" : "Jack"}}';

        var json2 = '{"name" : "jack", "age" : 20}';

        var ds = "1223";

        var parsed = JSON.parse(json2);


        $.ajax(
            {
                url: "/jsondata/insertproductinfo",
                data: JSON.stringify({name: 'Jack', age : 20}),
                processData: false,
                type: 'POST',
                contentType: 'application/json',
                "success": function(data){
                    alert("ok");
                }
            }
        );


    });
});