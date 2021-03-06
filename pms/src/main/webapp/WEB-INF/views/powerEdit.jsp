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
				  <li><a href="#">权限管理</a></li>
				  <li class="active">编辑权限</li>
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
					<label class="col-sm-3" for="exampleInputPassword1">权限名</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" id="power_name" value="${power.power_name}" placeholder="请输入权限名"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">请求路径</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" id="power_action" value="${power.power_action}" placeholder="请输入请求路径"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">所属模块</label>
					<div class="col-sm-9">
					<select name="modular_id">
						<c:forEach var="modular" items="${requestScope.modulars }">
							<c:choose>
								<c:when test="${power.modular_id==modular.modular_id }">
									<option selected="selected" value="${modular.modular_id }">${modular.modular_name }</option>
								</c:when>
								<c:otherwise>
									<option value="${modular.modular_id }">${modular.modular_name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<br><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">是否显示</label>
					<div class="col-sm-9">
					<select name="power_is_show">
					   <c:forEach var="isShow" items="${requestScope.isShow }">
					     <c:choose>
							<c:when test="${power.power_is_show==isShow.dictionary_value }">
								<option selected="selected" value="${isShow.dictionary_value }">${isShow.dictionary_name }</option>
							</c:when>
							<c:otherwise>
								<option value="${isShow.dictionary_value }">${isShow.dictionary_name }</option>
							</c:otherwise>
						</c:choose>
					   </c:forEach>
					</select>
					<br><br>
					</div>
				  </div>
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">父权限</label>
					<div class="col-sm-9">
					<select name="power_parent">
						<option value="0">顶级菜单</option>
						   <c:forEach var="power" items="${requestScope.powers }">
						     <c:choose>
						        <c:when test="${requestScope.power.power_parent==pageScope.power.power_id }">
						          	  <option selected="selected" value="${pageScope.power.power_id }">${pageScope.power.power_name }</option>
						        </c:when>
						        <c:otherwise>
						               <option value="${pageScope.power.power_id }">${pageScope.power.power_name }</option>
						        </c:otherwise>
						     </c:choose>
						   </c:forEach>
						</select>
					<br><br>
					</div>
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
			    	 window.location.href = "${pageContext.request.contextPath }/power/toPowerList.do";
			   }); 
			    //更新
			    $("#updateBtn").click(function(){
			    	//alert($("input[name=power_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/power/powerEdit.do",
			    		data : {
			    			            "power_id" : "${power.power_id}",
			    			         "power_name"  : $("#power_name").val(),
			    			    "power_action"     : $("#power_action").val(),
			    			     "power_parent"     : $("select[name='power_parent'] option:selected").val(),
			    			"power_is_show"     : $("select[name='power_is_show'] option:selected").val(),
			    			"modular_id"        : $("select[name='modular_id'] option:selected").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("权限信息修改成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/power/toPowerList.do";
		                        });
			    			} else {
		                        layer.msg("权限信息修改失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

