<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
<style type="text/css">
* {
    background: none repeat scroll 0 0 transparent;
    border: 1 none;
    margin: 0;
    padding: 0;
    vertical-align: baseline;
	font-family:微软雅黑;
	overflow:hidden;
}
#navi{
	width:100%;
	position:relative;
	word-wrap:break-word;
	border-bottom:1px solid #065FB9;
	margin:0;
	padding:0;
	height:40px;
	line-height:40px;
	vertical-align:middle;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, 
#BFBFBF));
}
#naviDiv{
	font-size:14px;
	color:#333;
	padding-left:10px;
}
#tips{
	margin-top:10px;
	width:100%;
	height:40px;
}
#buttonGroup{
	padding-left:10px;
	float:left;
	height:35px;
}
.button{
	margin-top:20px;
	padding-left:10px;
	padding-right:10px;
	font-size:14px;
	width:70px;
	height:30px;
	line-height:30px;
	vertical-align:middle;
	text-align:center;
	cursor:pointer;
    border-color: #77D1F6;
    border-width: 1px;
    border-style: solid;
    border-radius: 6px 6px;
    -moz-box-shadow: 2px 2px 4px #282828;
    -webkit-box-shadow: 2px 2px 4px #282828;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, #BFBFBF));
}
#mainContainer{
	padding-left:10px;
	padding-right:10px;
	text-align:left;
	width:98%;
	font-size:16px;
}
</style>
<style type="text/css">
  .text {padding:5px 3px;width:260px}
  .suggest-list {border:1px solid #ddd;background:#fff;line-height: 20px}
    .suggest-list li {padding:2px 5px;border-bottom:1px solid #ddd;cursor:default;zoom:1}
    .suggest-item-even {background:#E8F2FE}
    .suggest-curr-item {background:#6399CD; color:#fff;}
    
    .c-panel {padding:30px;margin:10px 0;background:#e8e8e8;border-radius:10px;-moz-border-radius:10px}
    .c-panel p {margin:5px 0;padding:5px 10px;background:#fff;border-left:4px solid #090}
  </style>
<body>
<script type="text/javascript" src="../js/Calendar3.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-suggest-pack.1.0.js"></script>

<script type="text/javascript">
  $(function(){
      var config = {
          url: '<%=path%>/goods/Trans_autoGoods.action?',
          //url:'http://suggest.taobao.com/sug?code=utf-8&extras=1',
          queryName: 'gname', //url?queryName=value,默认为输入框的name属性
          jsonp: 'callback', //设置此参数名，将开启jsonp功能，否则使用json数据结构
          item: 'li', //下拉提示项目单位的选择器，默认一个li是一条提示，与processData写法相关
          itemOverClass: 'suggest-curr-item', //当前下拉项目的标记类，可以作为css高亮类名
          'z-index': 999, //提示层的层叠优先级设置，css，你懂的
          processData: function(data){ //自定义处理返回的数据，该方法可以return一个html字符串或jquery对象，将被写入到提示的下拉层中
          	  console.log(data);
              var template = [];
              template.push('<ul class="suggest-list">');
              var evenOdd = {'0' : 'suggest-item-even', '1': 'suggest-item-odd'}, count = 0;
              for(var key in data.result) { //添加奇数，偶数区分
                  template.push('<li class="' , evenOdd[(++count) % 2] , '">', data.result[key][0],'</li>');
              };
              template.push('</ul>');
              return template.join('');
          },
          getCurrItemValue: function($currItem){ //定义如何去取得当前提示项目的值并返回值,插件根据此函数获取当前提示项目的值，并填入input中，此方法应根据processData参数来定义
              return $currItem.text();
          },
          textchange: function($input){}, //不同于change事件在失去焦点触发，inchange依赖本插件，只要内容有变化，就会触发，并传入input对象
          onselect: function($currItem){} //当选择一个下拉项目触发，并传入这个下拉项目jquery对象
      };
      
      $("#auto-suggest").suggest(config);
	  
        config.sequential = 1;
        config.onselect = function($currItem){
            $("#selected-show").html($currItem.html());
        };
        config.textchange = function($input){
            $("#change-show").html($input.val());
        };

      
  });
  </script>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;货品管理<span>&nbsp;
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/goods/Goods_query.action">货品列表</a><span>&nbsp;
	</div>
</div>
<div id="tips">
</div>
<div id="mainContainer">
<!-- 从session中获取货品集合 -->
<strong>添加货品资料</strong>
<br>
<br>
<form name="addForm" action="<%=path%>/goods/Goods_add.action" method="post" onsubmit='if(document.getElementById("price").value==""||document.getElementById("amount").value==""||document.getElementById("name").value=="") {alert("请输入相关数据！");return false;} '>
<table width="400" >
  <tr>
    <td width="30%">名称：</td>
    <td><input id="auto-suggest" type="text" name="gname" id="name"/></td>
  </tr>
  <tr>
    <td>价格：</td>
    <td><input type="text" name="price" id="price" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/></td>
  </tr>
  <tr>
    <td>数量：</td>
    <td><input name="amount" type="text" id="amount" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input class="button" type="submit" value="添加"></td>
  </tr>
</table>
</form>


</div>
</body>
</html>