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
    border: 0 none;
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
	float:left;
	margin-right:10px;
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
	text-align:center;
	width:98%;
	font-size:12px;
}
</style>
<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;订单管理<span>&nbsp;
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/orders/Orderout_query.action?oo_pageLast=0&oo_pageNext=1">售货订单列表</a><span>&nbsp;
	</div>
</div>
<div id="tips">
	<div id="buttonGroup">
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/orders/Orderout_add.jsp">添加进货</a>
		</div>
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a  href="<%=path%>/orders/Orderout_find.jsp">查找进货</a>
		</div>
	</div>
</div>
<div id="mainContainer">
<!-- 从session中获取货品集合 -->

<table class="default" width="100%">
	<tr class="title">
		<td>订单号</td>
		<td>货品清单</td>
		<td>客户</td>
		<td>应付款</td>
		<td>实付款</td>
		<td>时间</td>
		<td>经手人</td>
		<td>备注</td>
	</tr>
	
	<!-- 遍历开始 -->
	<s:iterator value="#session.order_detail" var="order">
	<tr class="list">
		<td><s:property value="#order.oid"/></td>
		<td>
			<table class="default" width="100%">
				<tr class="title">
					<td>货品名</td>
					<td>价格</td>
					<td>数量</td>
				</tr>
				<s:iterator value="#session.orderout_detail" var="orderout">
				<tr>
					<td><s:property value="#orderout.gname"/></td>
					<td><s:property value="#orderout.price"/></td>
					<td><s:property value="#orderout.amount"/></td>
				</tr>			
				</s:iterator>
			</table>
		</td>
		<td><s:property value="#order.customer"/></td>
		<td><s:property value="#order.price_sh"/></td>
		<td><s:property value="#order.price_ac"/></td>
		<td><s:date name="#order.date" format="yyyy.MM.dd-HH:mm:ss"/></td>
		<td><s:property value="#order.user"/></td>
		<td><s:property value="#order.note"/></td>
	</tr>
	</s:iterator>
	<!-- 遍历结束 -->
</table>
</div>
</body>
</html>