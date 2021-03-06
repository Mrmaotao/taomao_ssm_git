<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑属性</title>
</head>

<body>
    <div class="workingArea">
      <ol class="breadcrumb">
      <li><a href="admin_category_list">所有分类</a></li>
      <li><a href="admin_property_list?cid=${p.category.id}">${p.category.name}</a></li>
      <li class="active">编辑属性</li>
    </ol>
     <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑属性</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_property_update">
                <table class="editTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input id="name" name="name" "
                            type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="hidden" name="cid" value="${p.category.id}">
                        <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    </div>
<!-- 以json格式传输数据，接受也必须用bean对象接受 -->
<script type="text/javascript">
    $(document).ready(function() {
    	var idd = $("#name").val();
    	var a = {
    		name : idd
    	};
    })
</script>
<!--value="${p.name}  
-->