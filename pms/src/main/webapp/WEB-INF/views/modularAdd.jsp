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
				  <li><a href="#">模块管理</a></li>
				  <li class="active">新增模块</li>
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
					<label class="col-sm-3" for="exampleInputPassword1">模块名</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" id="modular_name" placeholder="请输入模块名"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 增加模块</button>
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
			  //返回
			    $("#return").click(function(){
			    	 window.location.href = "${pageContext.request.contextPath }/modular/toModularList.do";
			   }); 
			    //更新
			    $("#addBtn").click(function(){
			    	//alert($("input[name=modular_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/modular/addModular.do",
			    		data : {
			    			         "modular_name"  : $("#modular_name").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("模块增加成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/modular/toModularList.do";
		                        });
			    			} else {
		                        layer.msg("模块增加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

