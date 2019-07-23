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
					<label for="exampleInputPassword1">编号</label>
					<input type="text" class="form-control" readonly="readonly" id="charge_id" value="${charge.charge_id}" placeholder="请输入房间号"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">房号</label>&nbsp;&nbsp;
					<select name="house">
						<c:forEach var="house" items="${requestScope.houses }">
							<c:choose>
								<c:when test="${charge.house_id==house.house_id }">
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
				 
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">年月份</label>
					<input type="text" class="form-control" id="charge_mouth" value="${charge.charge_mouth}" placeholder="请输入年月份"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">水费</label>
					<input type="text" class="form-control" id="water_charge" value="${charge.water_charge}" placeholder="请输入水费"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">电费</label>
					<input type="text" class="form-control" id="electric_charge" value="${charge.electric_charge}" placeholder="请输入电费"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">燃气费</label>
					<input type="text" class="form-control" id="gas_charge" value="${charge.gas_charge}" placeholder="请输入燃气费用"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">是否已交费</label>&nbsp;&nbsp;
					<select >
					   <c:forEach var="pays" items="${requestScope.pays }">
					     <c:choose>
					       <c:when test="${requestScope.charge.isPay==pays.dictionary_value }">
					          <option selected="selected" value="${pays.dictionary_value }">${pays.dictionary_name }</option>
					       </c:when>
					     <c:otherwise>
					         <option value="${pays.dictionary_value }">${pays.dictionary_name }</option>
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
			    //单选框点击事件
			     $(":radio").click(function(){
				   $(this).attr("checked","checked");
			   }); 
			    //更新
			    $("#updateBtn").click(function(){
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/charge/chargeEdit.do",
			    		data : {
			    			            "charge_id" : $("#charge_id").val(),
			    			             "house_id" : $("select[name='house'] option:selected").val(),
			    			       "charge_mouth"   : $("#charge_mouth").val(),
			    			       "water_charge"   : $("#water_charge").val(),
			    			   "electric_charge"    : $("#electric_charge").val(),
			    			       "gas_charge"     : $("#gas_charge").val(),
			    			            "isPay"     : $("select option:selected").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("收费信息修改成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/charge/toChargeList.do";
		                        });
			    			} else {
		                        layer.msg("收费信息修改失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
		                        });
			    			}
			    		}
			    	});
			    })
            });
            laydate.render({
            	  elem: '#charge_mouth' //指定元素
            		  ,type: 'month'
            });
        </script>
  </body>
</html>

