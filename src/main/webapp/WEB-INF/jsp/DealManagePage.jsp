<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-05-09
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交易管理页面</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style>
        .main{
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
        }
        .mainBack{
            position: absolute;
            width: 60%;
            height: 70%;
            top: 10%;
            left: 20%;
            background: rgba(255,255,255,0.5);
        }

        p{
            margin-left:10px;
            margin-top:10px;
        }
        .title{
            position: absolute;
            align-self: center;
            left: 20%;
            top: 5px;
        }
        h1{
            color: transparent;
            background-color : black;
            text-shadow : rgba(255,255,255,0.5) 0 5px 6px, rgba(255,255,255,0.2) 1px 3px 3px;
            -webkit-background-clip : text;
        }
        .leftDiv{
            position: absolute;
            width: 30%;
            height: 70%;
            top: 15%;
            left: 12%;
            background: lightblue;
            border-width: 2px;
            border-color: black;
            border-radius: 5%;
        }
        /*分割线*/
        .cutLine{
            position: absolute;
            width: 3px;
            height: 80%;
            left: 49%;
            top: 10%;
            background: lightgreen;
        }
        .rightDiv{
            position: absolute;
            width: 30%;
            height: 70%;
            top: 15%;
            left: 57%;
            background: lightgrey;
            border-width: 2px;
            border-color: black;
            border-radius: 5%;
        }
        #bottom{
            position: absolute;
            top: 95%;
            width: 100%;
            height: 5%;
            left: 0;
            background: azure;
        }
        .content{
            display: inline-block;
            vertical-align: middle;
        }
        .flag{
            display: inline-block;
            vertical-align: middle;
            height: 100%;
            width: 0;
        }

        .main_contain{
            position: absolute;
            width: 100%;
            height: 100%;

            background-image: url("pictures/Background.jpg");
        }

        #btnContainer{
            position: absolute;
            left: 68%;
            top: 5%;
            width: 12%;
            height: 5%;
        }
    </style>
</head>
<body>
<div class="main_contain">
    <div class="title">
        <h1>商&emsp;品&emsp;溯&emsp;源&emsp;系&emsp;统</h1>
    </div>
    <%--链接按钮--%>
    <div id="btnContainer">
        <div class="btn-group" role="group" aria-label="web">
            <button type="button" class="btn btn-info" id="new_order"><a href="/register">返回</a></button>
            <button type="button" class="btn btn-warning" id="manage_btn"><a href="/turnToManagePage">刷新</a></button>
        </div>
    </div>
    <div class="mainBack" align="center">
        <%--内容--%>
            <table class="table table-bordered">
                <thead>
                <th>区块hash</th>
                <th>产品链路环节</th>
                </thead>
                <tbody>
                <c:forEach items="${map}" var="block">
                    <tr>
                        <td>${block.key}</td>
                        <td>${block.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
</div>



<%--信息提示窗体--%>
<div class="modal fade" id="messageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h5 class="modal-title" id="serverMessage">提示信息</h5>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<%--底部--%>
<div id="bottom">
    <p style="font-size: 15px;font-weight: bold;" align="center">© 2020 MSTS — All Rights Reserved.</p>
</div>

<script>
    /*信息提示方法*/
    function alertMessage(msg) {
        $("#serverMessage").html(msg);
        $("#messageModel").modal('show');
    }
</script>

</body>
</html>
