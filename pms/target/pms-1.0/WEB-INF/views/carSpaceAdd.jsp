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
				  <li class="active">新增车位</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">位置</label><br>
					<input type="text" class="form-control" id="park_location" placeholder="请输入车位位置"><br>
				  </div>
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">长度</label>
					<input type="text" class="form-control" id="park_length" placeholder="请输入车位长度"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">宽度</label>
					<input type="text" class="form-control" id="park_width" placeholder="请输入车位宽度"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">费用（元/年）</label>
					<input type="text" class="form-control" id="park_charge" placeholder="请输入费用"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">状态</label>
					<select  name="park_id" >
					   <c:forEach var="status" items="${requestScope.status }">
					         <option value="${status.dictionary_value }">${status.dictionary_name }</option>
				       </c:forEach>
					</select>
					<br><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 增加车位</button>
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
			    	//alert($("input[name=carSpace_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/carSpace/addCarSpace.do",
			    		data : {
		    			      "park_location" : $("#park_location").val(),
		    			      "park_length"   : $("#park_length").val(),
		    			       "park_width"   : $("#park_width").val(),
		    			     "park_charge"    : $("#park_charge").val(),
		    			    "park_status"     : $("select option:selected").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("车位增加成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/carSpace/toCarSpaceList.do";
		                        });
			    			} else {
		                        layer.msg("车位增加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

