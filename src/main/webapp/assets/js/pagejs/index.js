
var people = {
    name:'sam',
    age:18
};

var output = Mustache.render('name is : {{name}} and age is {{age}}', people);
var target = document.getElementById('menuitem1');
target.innerHTML = output;
alert('ok1234');

var pagenamevalue = document.getElementById('pagename').value;
var pageparemetervalue = document.getElementById('pageparameter').value;

var t1 = $('#pagename').value;

alert(pagenamevalue + ' '+ pageparemetervalue+ ' '+ t1);
alert('4321');


