/**
 * Created with JetBrains WebStorm.
 * User: maxiaoyu
 * Date: 13-6-4
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */

//$('#payinfoform').showHelpOnFocus();

$('#payinfoform').validateOnBlur();

//receiveinfoform
$('#receiveinfoform').validateOnBlur();

var jQueryFormLang = {
    errorTitle : 'Form submission failed!',
    requiredFields : 'You have not answered all required fields',
    badTime : 'You have not given a correct time',
    badEmail : 'You have not given a correct e-mail address',
    badTelephone : 'You have not given a correct phone number',
    badSecurityAnswer : 'You have not given a correct answer to the security question',
    badDate : 'You have not given a correct date',
    tooLongStart : '输出长度不能超过 ',
    tooLongEnd : ' 字符',
    tooShortStart : 'You have given an answer shorter than ',
    tooShortEnd : ' characters',
    badLength : 'You have to give an answer between ',
    notConfirmed : 'Values could not be confirmed',
    badDomain : 'Incorrect domain value',
    badUrl : 'Incorrect url value',
    badFloat : 'Incorrect float value',
    badCustomVal : 'You gave an incorrect answer',
    badInt : 'Incorrect integer value',
    badSecurityNumber : 'Your social security number was incorrect',
    badUKVatAnswer : 'Incorrect UK VAT Number',
    badNumberOfSelectedOptionsStart : 'You have to choose at least ',
    badNumberOfSelectedOptionsEnd : ' answers'
};

$(function(){



    $("#submitorderbtn").on("click",function(jQueryFormLang){

        var receivevheckok = false;
        var paycheckok = false;

        if($("#payinfoform").validate()){
            paycheckok = true;
            console.log("l1");

        } else{
            console.log("l11");
        };

        if($("#receivevheckok").validate()){
            receivevheckok = true;
            console.log("l2");
        } else{
            console.log("l22");
        };

        if( receivevheckok &&  paycheckok){
            console.log("333");

            var pay_zhifubao = $("#zhifubao").val();

            var pay_beizhu = $("#beizhu").val();


            var rec_shouhuoren = $("#shouhuoren").val();


            var rec_lianxidianhua  =$("#lianxidianhua").val();

            var rec_dianziyoujian  =$("#dianziyoujian").val();

            var rec_youbian  =$("#youbian").val();

            var rec_shouhuodizhi  =$("#shouhuodizhi").val();



            $.ajax(
                {
                    url: "/jsondata/insertproductinfo",
                    data: JSON.stringify({shouhuoren: rec_shouhuoren,
                        lianxidianhua : rec_lianxidianhua,
                        dianziyoujian : rec_dianziyoujian,
                        youbian : rec_youbian,
                        shouhuodizhi : rec_shouhuodizhi,
                        zhifubao: pay_zhifubao,
                        beizhu: pay_beizhu}),
                    processData: false,
                    type: 'POST',
                    contentType: 'application/json',
                    "success": function(data){
                        alert("ok");
                    }
                }
            );
        };

    });
});