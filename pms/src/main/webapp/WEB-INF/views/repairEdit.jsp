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
				  <li><a href="#">报修管理</a></li>
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
				 <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">编号</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" readonly="readonly" id="repair_id" value="${repair.repair_id}" placeholder="请输入房间号"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">类别</label>
					<div class="col-sm-9">
					<select name="types">
						<c:forEach var="types" items="${requestScope.types }">
							<c:choose>
								<c:when test="${repair.repair_type==types.dictionary_value }">
									<option selected="selected" value="${types.dictionary_value }">${types.dictionary_name }</option>
								</c:when>
								<c:otherwise>
									<option value="${types.dictionary_value }">${types.dictionary_name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<br><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">房号</label>
					<div class="col-sm-9">
					<select name="house">
						<c:forEach var="house" items="${requestScope.houses }">
							<c:choose>
								<c:when test="${repair.house_id==house.house_id }">
									<option selected="selected" value="${house.house_id }">${house.house_no }</option>
								</c:when>
								<c:otherwise>
									<option value="${house.house_id }">${house.house_no }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<br><br>
					</div>
				  </div>
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="exampleInputPassword1">内容</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" id="repair_desc" value="${repair.repair_desc}" placeholder="请输入单元号"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="example1">日期</label>
					<div class="col-sm-9">
					<input type="text" class="form-control" id="repair_time" value="${repair.repair_time}" placeholder="请输入合法的时间格式, 格式为： yyyy-MM-dd"><br>
					</div>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label class="col-sm-3" for="example1">处理状态</label>
					<div class="col-sm-9">
					<select name="status">
						<c:forEach var="status" items="${requestScope.status }">
							<c:choose>
								<c:when test="${repair.repair_status==status.dictionary_value }">
									<option selected="selected" value="${status.dictionary_value }">${status.dictionary_name }</option>
								</c:when>
								<c:otherwise>
									<option value="${status.dictionary_value }">${status.dictionary_name }</option>
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
			    //重置
			    $("#resetBtn").click(function(){
			    	$("#userForm")[0].reset();
			    })
			    //返回
			    $("#return").click(function(){
			    	 window.location.href = "${pageContext.request.contextPath }/repair/toRepairList.do";
			   }); 
			    //更新
			    $("#updateBtn").click(function(){
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/repair/repairEdit.do",
			    		data : {
			    			            "repair_id" : $("#repair_id").val(),
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
		                        layer.msg("报修信息修改成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/repair/toRepairList.do";
		                        });
			    			} else {
		                        layer.msg("报修信息修改失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
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

