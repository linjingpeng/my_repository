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
				  <li class="active">住房管理</li>
				</ol>
				<!-- 向导栏 end-->
				
			<!-- 内容页 start-->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="btnDeleteChkRepair" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='${pageContext.request.contextPath}/repair/toRepairAdd.do'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<!-- <th width="30">#</th> -->
										<th width="30"><input id="chkAllRepair" type="checkbox"></th>
										<th>编号</th>
										<th>类别</th>
										<th>房号</th>
										<th>内容</th>
										<th>日期</th>
										<th>处理状态</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.page.data }" var="repair" varStatus="status">
									<tr>
										<%-- <td>${status.count }</td> --%>
										<td><input name="repairId" value="${repair.repair_id }" type="checkbox"></td>
										<td>${repair.repair_id }</td>
										<td>${repair.types.dictionary_name }</td>
										<td>${repair.house.house_no }</td>
										<td>${repair.repair_desc }</td>
										<td>${repair.repair_time }</td>
										<td>${repair.status.dictionary_name }</td>
										<td>
											<button type="button" onclick="toRepairEdit(${repair.repair_id})" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" onclick="deleteRepair(${repair.repair_id})" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
									</c:forEach>
			
								</tbody>
								<tfoot>
									<tr>
										 <td colspan="9" align="center">
											<ul class="pagination">
											   <c:choose>
										         <c:when test="${requestScope.page.previous==true }">
										             <li>
										               <a href="${pageContext.request.contextPath }/repair/toRepairList.do?index=${requestScope.page.index-1}" aria-label="Previous">
										       			 <span aria-hidden="true">&laquo;</span>
										      			</a>
										      		 </li>
										         </c:when>
										         <c:otherwise>
										             <li class="disabled">
											            <a href="#" aria-label="Previous">
											       			 <span aria-hidden="true">&laquo;</span>
											      		 </a>
										      		 </li>
										         </c:otherwise>
										      </c:choose>

										    <c:forEach begin="1" varStatus="status" end="${requestScope.page.pageNum }">
										       <c:choose>
										          <%--需求：选中当前页
										            	思路：如果 status.count=index+1 选中
										           --%>
										          <c:when test="${status.count==requestScope.page.index+1}">
                                                         <%-- 注意事项：数据库的索引是从0开始的，而页面索引是从1开始的。索引数据索引=页面索引-1 --%>
										      			<li class="active">
										      			<a href="${pageContext.request.contextPath }/repair/toRepairList.do?index=${status.count-1}">${status.count }</a>
										      			</li>										          
										          </c:when>
										          <c:otherwise>
										                <li>
										                <a href="${pageContext.request.contextPath }/repair/toRepairList.do?index=${status.count-1}">${status.count }</a>
										                </li>
										          </c:otherwise>
										       </c:choose>
										    </c:forEach>
										    
										    <c:choose>
										      <c:when test="${requestScope.page.next==true }">
									           <li>
										      	  <a href="${pageContext.request.contextPath }/repair/toRepairList.do?index=${requestScope.page.index+1}" 
										      	      aria-label="Next">
										        	<span aria-hidden="true">&raquo;</span>
										      	  </a>
										    	</li>
										      </c:when>
										      <c:otherwise>
										         <li class="disabled">
										      		<a href="#" aria-label="Next">
										        	<span aria-hidden="true">&raquo;</span>
										      		</a>
										    	</li>
										      </c:otherwise>
										  
										    </c:choose>
										 </ul>
									  </td>
									</tr>

								</tfoot>
							</table>
						</div>
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
            });
            function toRepairEdit(repairId){
            	window.location.href="${pageContext.request.contextPath }/repair/toRepairEdit.do?repairId="+repairId;
            }
            function deleteRepair(repairId){
            	//alert(carspace+houName);
            	//window.location.href="${pageContext.request.contextPath }/repair/deleteRepair.do?repairId="+carspace;
				layer.confirm("删除报修信息【"+repairId+"】, 是否继续",  {icon: 3, title:'提示'}, function(cindex){
    			    
    				// 删除报修信息
    				$.ajax({
    					type : "POST",
    					url  : "${pageContext.request.contextPath }/repair/deleteRepair.do",
    					data :  {"repairId":repairId },
    					success : function(result) {
    						if ( result.success ) {
    							layer.msg("报修信息删除成功", {time:1000, icon:6}, function(){
		                        	window.location.href = "${pageContext.request.contextPath }/repair/toRepairList.do";
		                        });
    						} else {
                                layer.msg("报修信息删除失败", {time:2000, icon:5, shift:6}, function(){
                                	
                                });
    						}
    					}
    				});
    				
    				layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            $("#chkAllRepair").click(function(){
		    	   // alert("-test-"+$(this).prop("checked"));
		    	   //1.当前点击是复选框选中，其他的复选框也选中，
		    	   
		    	   if($(this).prop("checked")==true){
		    		   //注意：attr与prop的区别，attr只能获得或者设置一个对象的值，而prop可以设置或者获得一组对象的值
		    		   $("input[name='repairId']").prop("checked",true);
		    	   }else{
		    		   $("input[name='repairId']").prop("checked",false);
		    	   }
		    	});
		       
		       $("#btnDeleteChkRepair").click(function(){
		    	   //alert("--test--");
		    	   //1.获得选中的复选框的值
		    	   var chkModulars= $("input[name='repairId']:checked");
		    	   var params=chkModulars.serialize();
		    	 //  alert(params);
		    	   //2.将chkModular转换成发送的参数
		    	  // window.location.href="${pageContext.request.contextPath }/repair/deleteRepairs.do?"+params;
		    	  if(chkModulars.length==0){
		    		  layer.msg("请选择需要删除的报修信息", {time:2000, icon:5, shift:6}, function(){
	                    	
	                    });
		    	  }else{
		    		  
		    	   layer.confirm("删除选择的报修信息, 是否继续",  {icon: 3, title:'提示'}, function(cindex){
	    			    
	    				// 删除报修信息
	    				$.ajax({
	    					type : "POST",
	    					url  : "${pageContext.request.contextPath }/repair/deleteRepairs.do",
	    					data :  params ,
	    					success : function(result) {
	    						if ( result.success ) {
	    							layer.msg("报修信息删除成功", {time:1000, icon:6}, function(){
			                        	window.location.href = "${pageContext.request.contextPath }/repair/toRepairList.do";
			                        });
	    						} else {
	                                layer.msg("报修信息删除失败", {time:2000, icon:5, shift:6}, function(){
	                                	
	                                });
	    						}
	    					}
	    				});
	    				
	    				layer.close(cindex);
	    			}, function(cindex){
	    			    layer.close(cindex);
	    			});
		    	  }
		       });
        </script>
  </body>
</html>

