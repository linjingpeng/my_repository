<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
 <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item" >
						<a href="${pageContext.request.contextPath }/admin/index.do"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<c:forEach var="modular" items="${sessionScope.admin_info.modulars }">
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon-th-large"></i> ${modular.modular_name } <span class="badge" style="float:right"></span></span> 
						<ul style="margin-top:10px;">
							 <c:forEach var="power" items="${sessionScope.admin_info.powers }">
							    <c:if test="${power.power_parent==0 and power.power_is_show==0 and power.modular_id==modular.modular_id }" >
							      <c:choose>
							         <%--
							           	1.为了解决空字符的问题，需要判空！！
							           	2.为了忽略空格的判断，去掉空格
							          --%>
							 <c:when test="${fn:contains(fn:trim(sessionScope.path),fn:trim(power.power_action)) and power.power_action!='' and power.power_action!=null  }">
							<li class="active" style="height:30px;">
								<a href="${pageContext.request.contextPath }${power.power_action}"><i class="glyphicon glyphicon-user"></i> ${power.power_name}</a> 
							</li>
							 </c:when>
			                <c:otherwise>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }${power.power_action}"><i class="glyphicon glyphicon-king"></i> ${power.power_name}</a> 
							</li>
							</c:otherwise>
					      </c:choose>
						
						</c:if>
						</c:forEach>
							<%-- <li style="height:30px;">
								<a href="${pageContext.request.contextPath }/power/toPowerList.do"><i class="glyphicon glyphicon-lock"></i> 权限管理</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/modular/toModularList.do"><i class="glyphicon glyphicon-th"></i> 模块管理</a> 
							</li> --%>
						</ul>
					</li>
					</c:forEach>
					<%-- <li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 业务管理 <span class="badge" style="float:right">7</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/owner/toOwnerList.do"><i class="fa fa-users"></i> 户主管理</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/house/toHouseList.do"><i class="glyphicon glyphicon-home"></i> 住房管理</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/carSpace/toCarSpaceList.do"><i class="fa fa-truck"></i> 车位管理</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/complain/toComplainList.do"><i class="fa fa-bullhorn"></i> 投诉管理</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/repair/toRepairList.do"><i class="fa fa-wrench"></i> 报修管理</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath }/charge/toChargeList.do"><i class="fa fa-cny"></i> 收费管理</a> 
							</li>
						</ul>
					</li> --%>
					<%-- <li class="list-group-item tree-closed">
								<a href="${pageContext.request.contextPath }/dictionary/toDictionaryList.do"><i class="glyphicon glyphicon-book"></i> 数据字典</a> 
					</li> --%>
				</ul>
			</div>
        </div>