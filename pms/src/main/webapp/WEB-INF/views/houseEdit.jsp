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
				  <li><a href="#">住房管理</a></li>
				  <li class="active">编辑</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
					<button id="return"><i class="glyphicon glyphicon-arrow-left"></i>返回</button>
				</div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
					<input type="hidden" id="house_id" value="${house.house_id}">
				 <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">房号</label>
					<input type="text" class="form-control" name="house_no" id="house_no" value="${house.house_no}" placeholder="请输入房间号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">户型</label><br>
					<input type="text" class="form-control" name="house_shape" id="house_shape" value="${house.house_shape}" placeholder="请输入房屋户型"><br>
				  </div>
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">楼号</label>
					<input type="text" class="form-control" name="house_floor_id" id="house_floor_id" value="${house.house_floor_id}" placeholder="请输入楼号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">单元号</label>
					<input type="text" class="form-control" name="house_cell_id" id="house_cell_id" value="${house.house_cell_id}" placeholder="请输入单元号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">房屋面积</label>
					<input type="text" class="form-control" name="house_area" id="house_area" value="${house.house_area}" placeholder="请输入房屋面积"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">户主</label>&nbsp;&nbsp;
					<select  name="owner_id" >
					   <c:forEach var="owner" items="${requestScope.owners }">
					     <c:choose>
					       <c:when test="${requestScope.house.owner_id==owner.owner_id }">
					          <option selected="selected" value="${owner.owner_id }">${owner.owner_name }</option>
					       </c:when>
					     <c:otherwise>
					         <option value="${owner.owner_id }">${owner.owner_name }</option>
					     </c:otherwise>
					       </c:choose>
				       </c:forEach>
					</select>
					<br><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
				  <button id="updateBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i> 修改</button>
				  <button id="resetBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
			    //重置
			    $("#resetBtn").click(function(){
			    	$("#userForm")[0].reset();
			    })
			    //返回
			    $("#return").click(function(){
			    	 window.location.href = "${pageContext.request.contextPath }/house/toHouseList.do";
			   }); 
			    //更新
			    $("#updateBtn").click(function(){
			    	//alert($("select option:selected").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/house/houseEdit.do",
			    		data : {
			    			            "house_id" : $("#house_id").val(),
			    			            "house_no" : $("#house_no").val(),
			    			         "house_shape" : $("#house_shape").val(),
			    			    "house_floor_id"   : $("#house_floor_id").val(),
			    			     "house_cell_id"   : $("#house_cell_id").val(),
			    			       "house_area"    : $("#house_area").val(),
			    			        "owner_id"     : $("select option:selected").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("用户信息修改成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/house/toHouseList.do";
		                        });
			    			} else {
		                        layer.msg("用户信息修改失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

