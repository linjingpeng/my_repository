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
				  <li><a href="#">收费管理</a></li>
				  <li class="active">新增收费</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->	
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
	              	<button id="return"><i class="glyphicon glyphicon-arrow-left"></i>返回</button>
	              </div>
	           </div>
			  <div class="panel-body">
				<form id="userForm" role="form" >
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">房号</label>&nbsp;&nbsp;
					<select name="house">
						<c:forEach var="house" items="${requestScope.houses }">
							<option value="${house.house_id }">${house.house_no }</option>
						</c:forEach>
					</select>
					<br><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">年月份</label>
					<input type="text" class="form-control" id="charge_mouth"  placeholder="请输入年月份"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="exampleInputPassword1">水费</label>
					<input type="text" class="form-control" id="water_charge" placeholder="请输入水费"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">电费</label>
					<input type="text" class="form-control" id="electric_charge" placeholder="请输入电费"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">燃气费</label>
					<input type="text" class="form-control" id="gas_charge" placeholder="请输入燃气费用"><br>
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
					<label for="example1">是否已交费</label>&nbsp;&nbsp;
					<select name="pay">
					   <c:forEach var="pays" items="${requestScope.pays }">
					         <option value="${pays.dictionary_value }">${pays.dictionary_name }</option>
				       </c:forEach>
					</select>
					<br><br>					
				  </div>
				  <div class="col-sm-4 col-sm-offset-3">
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 增加收费信息</button>
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
			    //返回点击事件
			     $("#return").click(function(){
			    	 window.location.href = "${pageContext.request.contextPath }/charge/toChargeList.do";
			   }); 
			    //更新
			    $("#addBtn").click(function(){
			    	//alert($("input[name=charge_gender]:checked").val());
			    	var loadingIndex = null;
			    	$.ajax({
			    		type : "POST",
			    		url  : "${pageContext.request.contextPath }/charge/addCharge.do",
			    		data : {
			   			             "house_id" : $("select[name='house'] option:selected").val(),
			   			       "charge_mouth"   : $("#charge_mouth").val(),
			   			       "water_charge"   : $("#water_charge").val(),
			   			   "electric_charge"    : $("#electric_charge").val(),
			   			       "gas_charge"     : $("#gas_charge").val(),
			   			            "isPay"     : $("select[name='pay'] option:selected").val()
			    		},
			    		beforeSend : function() {
			    			loadingIndex = layer.msg('处理中', {icon: 16});
			    		},
			    		success : function(result) {
			    			layer.close(loadingIndex);
			    			if ( result.success ) {
		                        layer.msg("收费增加成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/charge/toChargeList.do";
		                        });
			    			} else {
		                        layer.msg("收费增加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){
		                        	
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

