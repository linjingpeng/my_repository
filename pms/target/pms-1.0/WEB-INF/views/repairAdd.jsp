<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="common/header.jsp"></jsp:include>
  </head>

  <body>
	<!-- 导航栏 start-->
   		 <jsp:include page="common/navbar.jsp"></jsp:include>
	<!-- 导航栏 end-->
	
	
    <div class="container-fluid">
      <div class="row">
		<!-- 左边菜单 start-->
       		<jsp:include page="common/sidebar.jsp"></jsp:include>
		<!-- 左边菜单 end-->
		
		<!-- 内容主体 start-->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!-- 向导栏 start-->
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增报修</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">类别</label>&nbsp;&nbsp;
					<select name="types">
						<c:forEach var="types" items="${requestScope.types }">
							<option value="${types.dictionary_value }">${types.dictionary_name }</option>
						</c:forEach>
					</select>
					<br><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">房号</label>&nbsp;&nbsp;
					<select name="house">
						<c:forEach var="house" items="${requestScope.houses }">
							<option value="${house.house_id }">${house.house_no }</option>
						</c:forEach>
					</select>
					<br><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">内容</label>
					<input type="text" class="form-control" id="repair_desc" placeholder="请输入报修内容"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">日期</label>
					<input type="text" class="form-control" id="repair_time" placeholder="请输入合法的时间格式, 格式为： yyyy-MM-dd"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">处理状态</label>&nbsp;&nbsp;
					<select name="status">
						<c:forEach var="status" items="${requestScope.status }">
							<option value="${status.dictionary_value }">${status.dictionary_name }</option>
						</c:forEach>
					</select>
					<br><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 增加报修</button>
				  </div>
				</form>
			  </div>
			</div>
			<!-- 内容页 end-->
        </div>
		<!-- 内容主体 end-->
      </div>
    </div>
	
    <script src="../lib/jquery/jquery-2.1.1.min.js"></script>
    <script src="../lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="../lib/script/docs.min.js"></script>
	<script src="../lib/layer/layer.js"></script>
	<script src="../lib/layer/laydate/laydate.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    //单选框点击事件
			     $(":radio").click(function(){
				   $(this).attr("checked","checked");
			   }); 
			    //更新
			    $("#addBtn").click(function(){
			    	//alert($("input[name=repair_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/repair/addRepair.do",
			    		data : {
				      			     "house_id" : $("select[name='house'] option:selected").val(),
		    			        "repair_type"   : $("select[name='types'] option:selected").val(),
		    			        "repair_desc"   : $("#repair_desc").val(),
		    			       "repair_time"    : $("#repair_time").val(),
		    			    "repair_status"     : $("select[name='status'] option:selected").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("报修增加成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/repair/toRepairList.do";
		                        });
			    			} else {
		                        layer.msg("报修增加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
            laydate.render({
            	  elem: '#repair_time' //指定元素
            });
        </script>
  </body>
</html>

