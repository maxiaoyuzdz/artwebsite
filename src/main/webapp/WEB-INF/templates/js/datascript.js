/**
 * Created with JetBrains WebStorm.
 * User: maxiaoyu
 * Date: 13-5-3
 * Time: 下午3:30
 * To change this template use File | Settings | File Templates.
 */
alert("okok")

var people {
    name: 'sam'
}

var target = document.getElementById('menuitem1')

var output = Mustache.render('<p>name is {{name}}</p>',people);
target.innerHTML = output;