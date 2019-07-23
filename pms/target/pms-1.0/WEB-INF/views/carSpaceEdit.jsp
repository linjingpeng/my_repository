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
				  <li class="active">编辑</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
				 <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">车位号</label>
					<input type="text" class="form-control" readonly="readonly" id="park_id" value="${carSpace.park_id}" placeholder="请输入房间号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">位置</label><br>
					<input type="text" class="form-control" id="park_location" value="${carSpace.park_location}" placeholder="请输入房屋户型"><br>
				  </div>
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">长度</label>
					<input type="text" class="form-control" id="park_length" value="${carSpace.park_length}" placeholder="请输入楼号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">宽度</label>
					<input type="text" class="form-control" id="park_width" value="${carSpace.park_width}" placeholder="请输入单元号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">费用（元/年）</label>
					<input type="text" class="form-control" id="park_charge" value="${carSpace.park_charge}" placeholder="请输入合法的时间格式, 格式为： yyyy-MM-dd"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">状态</label>
					<select  name="park_id" >
					   <c:forEach var="status" items="${requestScope.status }">
					     <c:choose>
					       <c:when test="${requestScope.carSpace.park_status==status.dictionary_value }">
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
			    		url  : "${pageContext.request.contextPath }/carSpace/carSpaceEdit.do",
			    		data : {
			    			            "park_id" : $("#park_id").val(),
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
		                        layer.msg("车位信息修改成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/carSpace/toCarSpaceList.do";
		                        });
			    			} else {
		                        layer.msg("车位信息修改失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
        </script>
  </body>
</html>

