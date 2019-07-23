<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">物业管理系统 </a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${sessionScope.admin_info.admin_name } <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath }/admin/toAdminSetting.do"><i class="glyphicon glyphicon-cog"></i> 修改密码</a></li>
						<li><a href="${pageContext.request.contextPath }/admin/toAdminEdit.do?adminId=${sessionScope.admin_info.admin_id }"><i class="glyphicon glyphicon-user"></i> 个人信息</a></li>
						<li><a href="${pageContext.request.contextPath }/admin/logout.do"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li> 
          </ul>
        </div>
      </div>
    </nav>