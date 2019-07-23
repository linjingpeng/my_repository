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
				  <li><a href="#">用户管理</a></li>
				  <li class="active">新增</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
					<button id="return"><i class="glyphicon glyphicon-arrow-left"></i>返回</button>
				</div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">用户名</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" name="admin_name" placeholder="请输入用户名"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">账号名</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" name="admin_account" placeholder="请输入账号名"><br>
					</div>
				  </div>
				 <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">密码</label>
					<div class="col-sm-9">
					<input type="password" class="form-control" name="admin_pwd" placeholder="请输入密码"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">用户状态</label>
					<div class="col-sm-9">
					<select name="admin_status">
						<c:forEach var="status" items="${requestScope.status }">
							<option value="${status.dictionary_value }">${status.dictionary_name }</option>
						</c:forEach>
					</select>
					<br><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">角色</label>
					<div class="col-sm-9">
					<select name="role_id">
						<c:forEach var="role" items="${requestScope.roles }">
							<option value="${role.role_id }">${role.role_name }</option>
						</c:forEach>
					</select>
					<br><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 增加用户</button>
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
			    $("#return").click(function(){
			    	 window.location.href = "${pageContext.request.contextPath }/admin/toAdminList.do";
			   });
			    //更新
			    $("#addBtn").click(function(){
			    	//alert($("input[name=admin_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/admin/addAdmin.do",
			    		data : $("#userForm").serialize(),
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("用户增加成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/admin/toAdminList.do";
		                        });
			    			} else {
		                        layer.msg("用户增加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

