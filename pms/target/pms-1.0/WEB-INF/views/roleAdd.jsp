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
				  <li class="active">新增</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				 ${requestScope.role_add_msg }
				<form id="roleForm" class="form-horizontal" role="form">
					<input name="token" type="hidden" value="${sessionScope.token }">
					<input name="token.invoke" type="hidden" value="/role/toRoleList.do">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 角色名 </label>

						<div class="col-sm-9">
							<input name="role_name" type="text" id="form-field-1" placeholder="角色名" class="col-xs-10 col-sm-5" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 全部权限 <input id="chkAllPowers"  type="checkbox" /></label>

					</div>
					
					<c:forEach var="modular" items="${requestScope.modulars }">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> ${modular.modular_name }<input onclick="chkModularPower(this)"  type="checkbox" /></label>
                                                               <c:set var="count" value="0"></c:set>
						<div class="col-sm-6">
						    <table class="table">
						     
						        <%--每四条权限就换行
						               思路：声明一个计数，从0开始，每次被4整除，增加一个 <tr/> <tr>
						          --%>
						      	<c:forEach var="power" items="${requestScope.powers }">
						      	  <c:if test="${count%4==0 }">
						        	<tr>
						       	</c:if>
						      	  <c:if test="${modular.modular_id==power.modular_id   }">
							      	<td>${ power.power_name}<input name="rolePower" value="${power.power_id }"  type="checkbox" /></td>
							      	<c:set var="count" value="${count+1}"></c:set>
							      
							      </c:if>
							    <c:if test="${count%4==0 }">
								 </tr>
								</c:if>
							   	</c:forEach>
							  
							</table>
							
						</div>
					</div>
					</c:forEach>

					<div class="form-group">
						
						<div class="col-sm-7 text-right">
							<button id="addBtn" type="button" class="btn btn-primary">增加角色</button>
						</div>
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
			     //更新
			    $("#addBtn").click(function(){
			    	//alert($("input[name=role_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/role/addRole.do",
			    		data :  $("#roleForm").serialize(),
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("角色增加成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/role/toRoleList.do";
		                        });
			    			} else {
		                        layer.msg("角色增加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    }) 
            });
            //1.实现全选以及返回所有的权限
			   $("#chkAllPowers").click(function(){
				   if($(this).prop("checked")==true){
					   $("input[type='checkbox']").prop("checked",true);
				   }else{
					   $("input[type='checkbox']").prop("checked",false);
				   }
			   });
			   
			   //2.勾选模块，仅仅只选中模块的权限。
			   var chkModularPower=function(obj){
				   //ojb是一个Document对象，如果转成jQuery对象,$(obj);
				  var parentDiv=  $(obj).parents(".form-group");
				 
				  var chkObject=parentDiv.find("input[type='checkbox']");
				  if($(obj).prop("checked")==true){
					  chkObject.prop("checked",true);
				  }else{
					  chkObject.prop("checked",false);
				  }
				  
			   } 
        </script>
  </body>
</html>

