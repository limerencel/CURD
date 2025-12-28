<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户注册</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="bootstrap/css/bootstrap-select.min.css">
		<script src="bootstrap/js/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="bootstrap/js/bootstrap-select.min.js"></script>
		<style type="text/css">
			.input-error {
				border-color: #cb2d01;
			}
		</style>
	</head>

	<body>

		<div class="container">
			<form class="form-horizontal" action="list.jsp">

				<h5 class="page-header alert-success" style=" padding:10px; text-align: center;">
					用户注册
				</h5>

				<div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名：</label>
            <div class="col-sm-10">
                <input type="text" id="username" class="form-control" required name="username" placeholder="请输入用户名"/>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码：</label>
            <div class="col-sm-10">
                <input type="password" id="password" class="form-control" required name="password" placeholder="请输入密码"/>
            </div>
        </div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">姓名：</label>
			<div class="col-sm-10">
				<input type="text" id="name" class="form-control" name="name" required placeholder="请输入姓名" /></div>
		</div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱：</label>
            <div class="col-sm-10">
                <input type="email" id="email" class="form-control" name="email" required placeholder="请输入邮箱"/></div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别：</label>
            <div class="col-sm-10">
                <label class="radio-inline">
                    <input type="radio" id="man" name="gender" required value="0"> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" id="girl" name="gender" required value="1"> 女
                </label>
            </div>
        </div>


        <div class="form-group">
            <label for="birthday" class="col-sm-2 control-label">出生日期：</label>
            <div class="col-sm-10">
                <input type="date" id="birthday" class="form-control" required name="birthday" placeholder="请选择出生日期"/></div>
        </div>


        <div class="form-group">
            <label for="province" class="col-sm-2 control-label">省份：</label>
            <div class="col-sm-10">
                <select name="province" id="province" required class="form-control selectpicker" data-live-search="true">
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="city" class="col-sm-2 control-label">城市：</label>
            <div class="col-sm-10">
                <select name="city" id="city" required class="form-control selectpicker" data-live-search="true">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="area" class="col-sm-2 control-label">县/区：</label>
            <div class="col-sm-10">
                <select name="area" id="area" required class="form-control selectpicker" data-live-search="true">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
				<hr>
				<div class="form-group" style="text-align: center;">
					<input type="submit" class="btn btn-primary" value="注册">
					<input type="reset" class="btn  btn-danger" value="重置">
					<input type="button" class="btn  btn-warning" style="float: right; width: 100px;" onclick="location.href='login.html'"
					 value="去登陆">

				</div>
			</form>
		</div>

	</body>

    <script>

        let provinceCode, cityCode;

        function queryCities(provinceCode) {
            $.get("/queryCity?provinceCode=" + provinceCode, function (data) {
                let html = '<option value="">请选择</option>';
                for(const city of data) {
                    html += "<option value='" + city.code + "'>" + city.name + "</option>";
                }
                $("#city").html(html);
                $('.selectpicker').selectpicker('refresh');
            })
        }

        function queryArea(cityCode) {
            $.get("/queryArea?cityCode=" + cityCode, function (data) {
                let html = '<option value="">请选择</option>';
                for(const area of data) {
                    html += "<option value='" + area.code + "'>" + area.name + "</option>";
                }
                $("#area").html(html);
                $('.selectpicker').selectpicker('refresh');
            })
        }

        $(function () {
            // 初始化省份
            $.get("/queryProvince", function(data) {
                let html = '<option value="">请选择</option>';
                for(const province of data) {
                    html += "<option value='" + province.code + "'>" + province.name + "</option>";
                }
                $("#province").html(html);
                $('.selectpicker').selectpicker('refresh');
            })
        });

        $('#province').on('change', function () {
            provinceCode = $(this).val();

            // 清空下级选项
            $("#city").html('<option value="">请选择</option>');
            $("#area").html('<option value="">请选择</option>');
            $('.selectpicker').selectpicker('refresh');

            // 加载城市
            if(provinceCode) {
                queryCities(provinceCode);
            }
        });

        $("#city").on("change", function () {
            cityCode = $(this).val();

            // 清空区县
            $("#area").html('<option value="">请选择</option>');
            $('.selectpicker').selectpicker('refresh');

            // 加载区县
            if(cityCode) {
                queryArea(cityCode);
            }
        });

        // validate the username
        $("#username").on("blur", function () {
            const username = $(this).val().trim();

            if (!username) {
                $(this).css("border", "1px solid red");
                return;
            }

            $.get("/checkUsername", { username: username }, function (data) {
                if (data === "1") {
                    // 用户已存在
                    $("#username").css("border", "1px solid red");
                    $("#usernameMsg").text("用户名已存在");
                } else {
                    // 用户可用
                    $("#username").css("border", "1px solid green");
                    $("#usernameMsg").text("用户名可用");
                }
            });
        });


    </script>
</html>
