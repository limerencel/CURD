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

                                <input type="hidden" name="pic" id="finalPicPath" value="${selectedUser.pic}"/>

                                <%--hidden ID for storing image path--%>
                                <input type="file" id="fileSelect" style="display: none;" accept="image/*">

                                <img src="${selectedUser.pic}" alt="点击修改头像" id="picPreview"
                                     style="cursor: pointer; width: 100px; height: 100px; border-radius: 50%;">

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
    <script>
        $(document).ready(function() {

            // 1. 点击头像 -> 触发文件选择框
            $("#picPreview").click(function() {
                $("#fileSelect").click();
            });

            // 2. 监听文件选择变化 (核心逻辑融合)
            $("#fileSelect").change(function() {
                let file = this.files[0];
                if (!file) return;

                // ============================================
                // 第一步：你喜欢的【本地即时预览】功能
                // ============================================
                let reader = new FileReader();
                reader.onload = function(e) {
                    // 这里用 e.target.result (Base64编码) 直接替换 src
                    // 用户完全感觉不到延迟，体验极佳！
                    $("#picPreview").attr("src", e.target.result);
                }
                reader.readAsDataURL(file);


                // ============================================
                // 第二步：后台【静默上传】功能
                // ============================================
                // 虽然用户已经看到图了，但我们还需要把图真正传给服务器

                let formData = new FormData();
                formData.append("file", file); // 对应 UploadServlet

                $.ajax({
                    url: "${pageContext.request.contextPath}/upload",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(serverReturnedPath) {
                        // 上传成功了！

                        // 把服务器返回的路径（例如 /images/uuid.png）填入隐藏域
                        // 这样等下用户点“修改信息”时，提交的就是这个新路径
                        console.log(serverReturnedPath)
                        $("#finalPicPath").val(serverReturnedPath);

                        console.log("后台上传成功，隐藏域已更新: " + serverReturnedPath);
                    },
                    error: function() {
                        alert("警告：图片上传服务器失败！请检查网络。");
                        // 如果失败了，你可能想把头像恢复成旧的，或者提示用户
                    }
                });
            });
        });
    </script>
</html>
