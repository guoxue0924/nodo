/**
 * Created by George on 15/4/16.
 */
/* $Id: index.js 15469 2008-12-19 06:34:44Z testyang $ */
//var best_str = new Object();
//var new_str = new Object();
//var hot_str = new Object();
//
//function init_rec_data()
//{
//    best_str[0] = document.getElementById("show_best_area") == null ? '' : document.getElementById("show_best_area").innerHTML;
//    new_str[0] = document.getElementById("show_new_area") == null ? '' : document.getElementById("show_new_area").innerHTML;
//    hot_str[0] = document.getElementById("show_hot_area") == null ? '' : document.getElementById("show_hot_area").innerHTML;
//}
//
//function get_cat_recommend(rec_type, cat_id)
//{
//    if (rec_type == 1)
//    {
//        if (typeof(best_str[cat_id]) == "string")
//        {
//            document.getElementById("show_best_area").innerHTML = best_str[cat_id];
//            return;
//        }
//    }
//    else if (rec_type == 2)
//    {
//        if (typeof(new_str[cat_id]) == "string")
//        {
//            document.getElementById("show_new_area").innerHTML = new_str[cat_id];
//            return;
//        }
//    }
//    else
//    {
//        if (typeof(hot_str[cat_id]) == "string")
//        {
//            document.getElementById("show_hot_area").innerHTML = hot_str[cat_id];
//            return;
//        }
//    }
//    Ajax.call('index.php?act=cat_rec', 'rec_type=' + rec_type + '&cid=' + cat_id, cat_rec_response, "POST", "TEXT");
//}
//
//function cat_rec_response(result)
//{
//    var res = result.parseJSON();
//    if (res.type == 1)
//    {
//        var ele = document.getElementById("show_best_area");
//        best_str[res.cat_id] = res.content;
//    }
//    else if (res.type == 2)
//    {
//        var ele = document.getElementById("show_new_area");
//        new_str[res.cat_id] = res.content;
//    }
//    else
//    {
//        var ele = document.getElementById("show_hot_area");
//        hot_str[res.cat_id] = res.content;
//    }
//    ele.innerHTML = res.content;
//}
//
//if (document.all)
//{
//    window.attachEvent("onload", init_rec_data);
//}
//else
//{
//    window.addEventListener("load", init_rec_data, false);
//}
//
//function change_tab_style(item, elem, obj)
//{
//    elem = elem.toUpperCase();
//    var itemObj = document.getElementById(item);
//    var elems = itemObj.getElementsByTagName(elem);
//    var _o = obj.parentNode;
//    while(_o.nodeName != elem)
//    {
//        _o = _o.parentNode;
//    }
//    for (var i=0,l=elems.length; i<l; i++)
//    {
//        elems[i].className = 'h2bg';
//    }
//    _o.className = '';
//}
//
///*???????????????*/
//function bookmark() {
//    var httpUrl="http://"+location.hostname;
//    var c;
//    var a = /^http{1}s{0,1}:\/\/([a-z0-9_\\-]+\.)+(yihaodian|1mall|111|yhd){1}\.(com|com\.cn){1}\?(.+)+$/;
//    if (a.test(httpUrl)) {
//        c = "&amp;ref=favorite"
//    } else {
//        c = "?ref=favorite"
//    }
//    var d = httpUrl + c;
//    if ('undefined' == typeof (document.body.style.maxHeight)) {
//        d = httpUrl
//    }
//    try {
//        if (document.all) {
//            window.external.AddFavorite(d, favorite)
//        } else {
//            try {
//                window.sidebar.addPanel(favorite, d, "")
//            } catch(b) {
//                alert("?????????????????????????????????????????????????????????\n\n??????????????????????????????Ctrl+D????????????")
//            }
//        }
//    } catch(b) {
//        alert("?????????????????????????????????????????????????????????\n\n??????????????????????????????Ctrl+D????????????")
//    }
//}
//function deleteCartGoods(rec_id)
//{
//    Ajax.call('delete_cart_goods.php', 'id='+rec_id, deleteCartGoodsResponse, 'POST', 'JSON');
//}
///**
// * ?????????????????????
// */
//function deleteCartGoodsResponse(res)
//{
//    if (res.error)
//    {
//        alert(res.err_msg);
//    }
//    else
//    {
//        $("#shoppingCarNone").html(res.content);
//    }
//}
//
////??????????????????
//function sw_nav2(obj,tag)
//{
//    var DisSub2 = document.getElementById("DisSub2_"+obj);
//    var HandleLI2= document.getElementById("HandleLI2_"+obj);
//    if(tag==1)
//    {
//        DisSub2.style.display = "block";
//        HandleLI2.className="current";
//    }
//    else
//    {
//        DisSub2.style.display = "none";
//        HandleLI2.className="";
//    }
//}
//
//var $ = function (id) {
//    return "string" == typeof id ? document.getElementById(id) : id;
//};
//var Class = {
//    create: function () {
//        return function () {
//            this.initialize.apply(this, arguments);
//        }
//    }
//}
//Object.extend = function (destination, source) {
//    for (var property in source) {
//        destination[property] = source[property];
//    }
//    return destination;
//}
//var TransformView = Class.create();
//TransformView.prototype = {
//    //????????????,????????????,????????????,????????????
//    initialize: function (container, slider, parameter, count, options) {
//        if (parameter &lt;= 0 || count &lt;= 0) return;
//        var oContainer = $(container), oSlider = $(slider), oThis = this;
//        this.Index = 0;//????????????
//
//        this._timer = null;//?????????
//        this._slider = oSlider;//????????????
//        this._parameter = parameter;//????????????
//        this._count = count || 0;//????????????
//        this._target = 0;//????????????
//
//        this.SetOptions(options);
//
//        this.Up = !!this.options.Up;
//        this.Step = Math.abs(this.options.Step);
//        this.Time = Math.abs(this.options.Time);
//        this.Auto = !!this.options.Auto;
//        this.Pause = Math.abs(this.options.Pause);
//        this.onStart = this.options.onStart;
//        this.onFinish = this.options.onFinish;
//
//        oContainer.style.overflow = "hidden";
//        oContainer.style.position = "relative";
//
//        oSlider.style.position = "absolute";
//        oSlider.style.top = oSlider.style.left = 0;
//    },
//    //??????????????????
//    SetOptions: function (options) {
//        this.options = {//?????????
//            Up: true,//????????????(????????????)
//            Step: 5,//???????????????
//            Time: 10,//????????????
//            Auto: true,//??????????????????
//            Pause: 2000,//????????????(Auto???true?????????)
//            onStart: function () {
//            },//?????????????????????
//            onFinish: function () {
//            }//?????????????????????
//        };
//        Object.extend(this.options, options || {});
//    },
//    //??????????????????
//    Start: function () {
//        if (this.Index &lt; 0) {
//            this.Index = this._count - 1;
//        } else if (this.Index &gt;= this._count) {
//            this.Index = 0;
//        }
//
//        this._target = -1 * this._parameter * this.Index;
//        this.onStart();
//        this.Move();
//    },
//    //??????
//    Move: function () {
//        clearTimeout(this._timer);
//        var oThis = this, style = this.Up ? "top" : "left", iNow = parseInt(this._slider.style[style]) || 0, iStep = this.GetStep(this._target, iNow);
//
//        if (iStep != 0) {
//            this._slider.style[style] = (iNow + iStep) + "px";
//            this._timer = setTimeout(function () {
//                oThis.Move();
//            }, this.Time);
//        } else {
//            this._slider.style[style] = this._target + "px";
//            this.onFinish();
//            if (this.Auto) {
//                this._timer = setTimeout(function () {
//                    oThis.Index++;
//                    oThis.Start();
//                }, this.Pause);
//            }
//        }
//    },
//    //????????????
//    GetStep: function (iTarget, iNow) {
//        var iStep = (iTarget - iNow) / this.Step;
//        if (iStep == 0) return 0;
//        if (Math.abs(iStep) &lt; 1) return (iStep &gt; 0 ? 1 : -1);
//        return iStep;
//    },
//    //??????
//    Stop: function (iTarget, iNow) {
//        clearTimeout(this._timer);
//        this._slider.style[this.Up ? "top" : "left"] = this._target + "px";
//    }
//};
//window.onload = function () {
//    function Each(list, fun) {
//        for (var i = 0, len = list.length; i &lt; len; i++) {
//            fun(list[i], i);
//        }
//    };
//
//    var objs = $("idNum").getElementsByTagName("li");
//
//    var tv = new TransformView("idTransformView", "idSlider", 410, 5, {
//        onStart: function () {
//            Each(objs, function (o, i) {
//                o.className = tv.Index == i ? "on" : "";
//            })
//        }//????????????
//    });
//
//    tv.Start();
//
//    Each(objs, function (o, i) {
//        o.onmouseover = function () {
//            o.className = "on";
//            tv.Auto = false;
//            tv.Index = i;
//            tv.Start();
//        }
//        o.onmouseout = function () {
//            o.className = "";
//            tv.Auto = true;
//            tv.Start();
//        }
//    })
//
//    ////////////////////////test2
//
//    var objs2 = $("idNum2").getElementsByTagName("li");
//
//    var tv2 = new TransformView("idTransformView2", "idSlider2", 994, 5, {
//        onStart: function () {
//            Each(objs2, function (o, i) {
//                o.className = tv2.Index == i ? "on" : "";
//            })
//        },//????????????
//        Up: false
//    });
//
//    tv2.Start();
//
//    Each(objs2, function (o, i) {
//        o.onmouseover = function () {
//            o.className = "on";
//            tv2.Auto = false;
//            tv2.Index = i;
//            tv2.Start();
//        }
//        o.onmouseout = function () {
//            o.className = "";
//            tv2.Auto = true;
//            tv2.Start();
//        }
//    })
//
//    $("idStop").onclick = function () {
//        tv2.Auto = false;
//        tv2.Stop();
//    }
//    $("idStart").onclick = function () {
//        tv2.Auto = true;
//        tv2.Start();
//    }
//    $("idNext").onclick = function () {
//        tv2.Index++;
//        tv2.Start();
//    }
//    $("idPre").onclick = function () {
//        tv2.Index--;
//        tv2.Start();
//    }
//    $("idFast").onclick = function () {
//        if (--tv2.Step &lt;= 0) {
//            tv2.Step = 1;
//        }
//    }
//    $("idSlow").onclick = function () {
//        if (++tv2.Step &gt;= 10) {
//            tv2.Step = 10;
//        }
//    }
//    $("idReduce").onclick = function () {
//        tv2.Pause -= 1000;
//        if (tv2.Pause &lt;= 0) {
//            tv2.Pause = 0;
//        }
//    }
//    $("idAdd").onclick = function () {
//        tv2.Pause += 1000;
//        if (tv2.Pause &gt;= 5000) {
//            tv2.Pause = 5000;
//        }
//    }
//
//    $("idReset").onclick = function () {
//        tv2.Step = Math.abs(tv2.options.Step);
//        tv2.Time = Math.abs(tv2.options.Time);
//        tv2.Auto = !!tv2.options.Auto;
//        tv2.Pause = Math.abs(tv2.options.Pause);
//    }
//
//}
//
////&lt;![CDATA[
//var tips;
//var theTop = 120/*??????????????????,???????????????*/;
//var old = theTop;
//function initFloatTips() {
//    tips = document.getElementById('divQQbox');
//    moveTips();
//}
//;
//function moveTips() {
//    var tt = 50;
//    if (window.innerHeight) {
//        pos = window.pageYOffset
//    }
//    else if (document.documentElement &amp;&amp; document.documentElement.scrollTop) {
//        pos = document.documentElement.scrollTop
//    }
//    else if (document.body) {
//        pos = document.body.scrollTop;
//    }
//    pos = pos - tips.offsetTop + theTop;
//    pos = tips.offsetTop + pos / 10;
//    if (pos &lt; theTop) pos = theTop;
//    if (pos != old) {
//        tips.style.top = pos + "px";
//        tt = 10;
////alert(tips.style.top);
//    }
//    old = pos;
//    setTimeout(moveTips, tt);
//}
////!]]&gt;
//initFloatTips();
//function OnlineOver() {
//    document.getElementById("divMenu").style.display = "none";
//    document.getElementById("divOnline").style.display = "block";
//    document.getElementById("divQQbox").style.width = "170px";
//}
//function OnlineOut() {
//    document.getElementById("divMenu").style.display = "block";
//    document.getElementById("divOnline").style.display = "none";
//}
//if (typeof(HTMLElement) != "undefined")    //???firefox??????contains()?????????ie???????????????
//{
//    HTMLElement.prototype.contains = function (obj) {
//        while (obj != null &amp;&amp; typeof(obj.tagName) != "undefind") { //????????????????????????????????????obj????????????
//            if (obj == this) return true;
//            obj = obj.parentNode;
//        }
//        return false;
//    };
//}
//function hideMsgBox(theEvent) { //theEvent?????????????????????Firefox?????????
//
//    if (theEvent) {
//        var browser = navigator.userAgent;//?????????????????????
//        if (browser.indexOf("Firefox") &gt; 0) { //?????????Firefox
//            if (document.getElementById('divOnline').contains(theEvent.relatedTarget)) { //??????????????????
//                return; //????????????
//            }
//        }
//        if (browser.indexOf("MSIE") &gt; 0) { //?????????IE
//            if (document.getElementById('divOnline').contains(event.toElement)) { //??????????????????
//                return; //????????????
//            }
//        }
//    }
//    /*??????????????????*/
//    document.getElementById("divMenu").style.display = "block";
//    document.getElementById("divOnline").style.display = "none";
//}
//var _hmt = _hmt || [];
//(function () {
//    var hm = document.createElement("script");
//    hm.src = "//hm.baidu.com/hm.js?d7e112eaeea65921aeaadcc0af8f2853";
//    var s = document.getElementsByTagName("script")[0];
//    s.parentNode.insertBefore(hm, s);
//})();

$(function () {
    $('#category_tree .dt').hover(function () {
        var index = $(this).index();
        $(this).find(".DisSub2").show();
        $(this).find(".HandleLI2").addClass('current');
    }, function () {
        $(this).find(".DisSub2").hide();
        $(this).find(".HandleLI2").removeClass('current');
    });

    $("#carouselAdvert").owlCarousel({"items": 1});

    $("#com_b h2").click(function(){
        console.log($(this).index);
    });
    $(".sliderBtn").click(function(){
        $(this).next("ul").toggle();
    });
});