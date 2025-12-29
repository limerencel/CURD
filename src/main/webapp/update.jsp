<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户修改</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/jquery-3.5.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>

		<div class="container">
            <form class="form-horizontal" action="/update" method="post">

                <%--隐藏域存储id--%>
                <input type="hidden" name="id" value="${selectedUser.id}">

                <h5 class="page-header alert-info" style="margin:0; padding:10px; ">
                    头像
                </h5>
                <div class="row">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">头像</label>
                            <div class="col-sm-9">
                                <input type="file" name="pic" id="picFile" value="${selectedUser.pic}" style="display: none;"/>
                                <img src="${selectedUser.pic}" alt="" id="pic" style="cursor: pointer;">
                            </div>
                        </div>
                    </div>
                </div>

                <h5 class="page-header alert-info" style="margin:0px; padding:10px; margin-bottom:10px;">
                    基本信息
                </h5>

                <!-- 111 -->
                <div class="row">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">员工姓名</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" value="${selectedUser.name}" name="name" placeholder="请输入员工姓名"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">出生日期</label>
                            <div class="col-sm-9">
                                <input type="date"
                                       class="form-control"
                                       value="${selectedUser.birthday}"
                                       name="birthday"
                                       placeholder="出生日期"/>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- 111 -->
                <!-- 222 -->
                <div class="row">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电子邮件</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="email" value="${selectedUser.email}"
                                       placeholder="请输入电子邮件"/>

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">籍贯</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="address" value="${selectedUser.address}"
                                       placeholder="请输入籍贯"/>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- 2222 -->
                <!-- 3333 -->
                <div class="row">

                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">员工性别</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="gender">

                                    <option value="2" ${selectedUser.gender==2?'selected':''}>保密</option>
                                    <option value="0" ${selectedUser.gender==0?'selected':''}>女</option>
                                    <option value="1" ${selectedUser.gender==1?'selected':''}>男</option>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- 3333 -->
                <h5 class="page-header alert-info" style="margin:0px; padding:10px; margin-bottom:10px;">
                    账户信息
                </h5>
                <!-- 222 -->
                <div class="row">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="username" value="${selectedUser.username}" readonly
                                       placeholder="请输入用户名"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">员工密码</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="password" value="${selectedUser.password}" readonly
                                       placeholder="请输入员工密码"/>

                            </div>
                        </div>
                    </div>
                </div>


                <!-- 2222 -->
                <hr>
                <div class="row">
                    <div style="text-align: center;">
                        <input type="submit" class="btn btn-success" value="修改信息">
                        <input type="reset" class="btn  btn-danger" value="重置信息">
                    </div>
                </div>
            </form>
		</div>

	</body>

</html>
