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
				  <li class="active">编辑字典</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
				 <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">编号</label>
					<input type="text" class="form-control" readonly="readonly" id="dictionary_id" value="${dictionary.dictionary_id}" placeholder="请输入编号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">字典数据名</label><br>
					<input type="text" class="form-control" id="dictionary_value" value="${dictionary.dictionary_value}" placeholder="请输入字典数据名"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">字典名</label>
					<input type="text" class="form-control" id="dictionary_name" value="${dictionary.dictionary_name}" placeholder="请输入字典名"><br>
				  </div>
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">字典类型编码</label>
					<input type="text" class="form-control" id="dictionary_type_code" value="${dictionary.dictionary_type_code}" placeholder="字典类型编码"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">字典类型名称</label>
					<input type="text" class="form-control" id="dictionary_type_name" value="${dictionary.dictionary_type_name}" placeholder="请输入字典类型名称"><br>
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
			    //单选框点击事件
			     $(":radio").click(function(){
				   $(this).attr("checked","checked");
			   }); 
			    //更新
			    $("#updateBtn").click(function(){
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/dictionary/dictionaryEdit.do",
			    		data : {
			    			            "dictionary_id" : $("#dictionary_id").val(),
			    			             "dictionary_value" : $("#dictionary_value").val(),
			    			        "dictionary_name"   : $("#dictionary_name").val(),
			    			        "dictionary_type_code"   : $("#dictionary_type_code").val(),
			    			       "dictionary_type_name"    : $("#dictionary_type_name").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("字典信息修改成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/dictionary/toDictionaryList.do";
		                        });
			    			} else {
		                        layer.msg("字典信息修改失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

