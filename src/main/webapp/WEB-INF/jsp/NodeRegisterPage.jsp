<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-05-09
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>节点注册页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">

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
                <button type="button" class="btn btn-info" id="new_order">新建订单</button>
                <button type="button" class="btn btn-warning" id="manage_btn">管理页面</button>
            </div>
        </div>
        <div class="mainBack" align="center">
            <div class="main" align="center">
                <%--页面导航--%>
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                        <a href="#create" data-toggle="tab" id="produce_nav">
                            生 产
                        </a>
                    </li>
                    <li>
                        <a href="#process" data-toggle="tab" id="process_nav">
                            加 工
                        </a>
                    </li>
                    <li>
                        <a href="#sell" data-toggle="tab" id="store_nav">
                            销 售
                        </a>
                    </li>
                    <li>
                        <a href="#delivery" data-toggle="tab" id="transport_nav">
                            运 输
                        </a>
                    </li>
                    <li>
                        <a href="#user" data-toggle="tab" id="user_nav">
                            用 户
                        </a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <%--主页面--%>
                    <div class="tab-pane fade in active" id="create">
                        <%--生产--%>
                        <div class="leftDiv" id="producer_regist">
                            <%--左边--%>
                            <button type="button" class="btn btn-block disabled">生产方注册</button>
                            <br>
                            <br>
                            <form id="producer_form">
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon2">名 字</span>
                                <input type="text" class="form-control" name="name" placeholder="名字" aria-describedby="basic-addon2">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon3">厂 址</span>
                                <input type="text" class="form-control" name="location" placeholder="厂址" aria-describedby="basic-addon3">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">联系电话</span>
                                <input type="text" class="form-control" name="tel" placeholder="联系电话" aria-describedby="basic-addon1">
                            </div>
                            <br>
                            <br>
                            <button type="button" class="btn btn-info" id="producer_register">注 册</button>
                            </form>
                        </div>

                        <div class="cutLine"></div>
                        <div class="rightDiv">
                            <div class="list-group">
                                <div style="position: absolute;width: 100%;height: 15%">
                                    <button type="button" class="btn btn-block disabled">生产厂商列表</button>
                                </div>
                                <div style="position: absolute;width: 100%;height: 90%;top: 10%;left: 5%" class="row pre-scrollable">
                                    <%--节点列表--%>
                                    <c:forEach items="${producers}" var="producer">
                                        <button type="button" class="list-group-item list-group-item-success" data-toggle="modal" data-target="#producerModel" onclick="liClicked(this,${producer.id})" id="producerId">${producer.name}</button>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="process">
                        <%--加工--%>
                        <div class="leftDiv">
                            <button type="button" class="btn btn-block disabled">加工方注册</button>
                            <br>
                            <br>
                            <form id="process_form">
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon4">名 字</span>
                                <input type="text" class="form-control" name="name" placeholder="名字" aria-describedby="basic-addon4">
                            </div>
                            <br>
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">加工方式<span class="caret"></span></button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="progress_method">冲压</a></li>
                                        <li><a class="progress_method">热加工</a></li>
                                        <li><a class="progress_method">水冷</a></li>
                                        <li><a class="progress_method">热塑</a></li>
                                    </ul>
                                </div>
                                <input type="text" class="form-control" value="" id="process_method_input" placeholder="其他加工方式" aria-label="...">
                            </div>
                            <br>
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">加工种类<span class="caret"></span></button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="progress_catagory">精加工</a></li>
                                        <li><a class="progress_catagory">粗加工</a></li>
                                        <li><a class="progress_catagory">未加工</a></li>
                                    </ul>
                                </div>
                                <input type="text" class="form-control" value="" id="process_catagory_input" placeholder="其他加工种类" aria-label="...">
                            </div>

                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon7">联系电话</span>
                                <input type="text" class="form-control" name="tel" placeholder="联系电话" aria-describedby="basic-addon7">
                            </div>
                            <br>
                            <button type="button" class="btn btn-info" id="process_regist">注 册</button>
                            </form>
                        </div>
                        <div class="cutLine"></div>
                        <%--右边--%>
                        <div class="rightDiv">
                            <div class="list-group">
                                <div style="position: absolute;width: 100%;height: 15%">
                                    <button type="button" class="btn btn-block disabled">加工厂商列表</button>
                                </div>
                                <div style="position: absolute;width: 100%;height: 90%;top: 10%;left: 5%" class="row pre-scrollable">
                                    <%--节点列表--%>
                                    <c:forEach items="${processers}" var="processer">
                                        <button type="button" class="list-group-item list-group-item-success" data-toggle="modal" data-target="#processerModel" onclick="liClicked(this,${processer.id})" id="processerId">${processer.name}</button>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="sell">
                        <%--销售--%>
                        <div class="leftDiv">
                            <%--左边--%>
                            <button type="button" class="btn btn-block disabled">商家注册</button>
                            <br>
                            <br>
                            <form id="store_form">
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon8">店 名</span>
                                <input type="text" class="form-control" name="name" placeholder="输入店名" aria-describedby="basic-addon8">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon9">负责人</span>
                                <input type="text" class="form-control" name="person_in_charge" placeholder="负责人名字" aria-describedby="basic-addon9">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon10">联系电话</span>
                                <input type="text" class="form-control" name="tel" placeholder="联系电话" aria-describedby="basic-addon10">
                            </div>
                            </form>
                            <br>
                            <br>
                            <button type="button" id="store_regist" class="btn btn-info">注 册</button>
                        </div>
                        <div class="cutLine"></div>
                            <%--右边--%>
                        <div class="rightDiv">
                            <div class="list-group">
                                <div style="position: absolute;width: 100%;height: 15%">
                                    <button type="button" class="btn btn-block disabled">销售商店列表</button>
                                </div>
                                <div style="position: absolute;width: 100%;height: 90%;top: 10%;left: 5%" class="row pre-scrollable">
                                    <%--节点列表--%>
                                    <c:forEach items="${stores}" var="store">
                                        <button type="button" class="list-group-item list-group-item-success" data-toggle="modal" data-target="#storeModel" onclick="liClicked(this,${store.id})" id="store_id">${store.name}</button>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="delivery">
                        <%--运输--%>
                        <div class="leftDiv">
                            <%--左边--%>
                            <button type="button" class="btn btn-block disabled">运输方注册</button>
                            <br>
                            <br>
                            <form id="delivery_form">
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon11">公司名字</span>
                                <input type="text" class="form-control" name="name" placeholder="输入名字" aria-describedby="basic-addon11">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon12">负责人</span>
                                <input type="text" class="form-control" name="person_in_charge" placeholder="负责人姓名" aria-describedby="basic-addon12">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon13">联系电话</span>
                                <input type="text" class="form-control" name="tel" placeholder="联系电话" aria-describedby="basic-addon13">
                            </div>
                            </form>
                            <br>
                            <br>
                            <button type="button" id="transporter_regist" class="btn btn-info">注 册</button>
                        </div>
                        <%--右边--%>
                        <div class="cutLine"></div>
                        <div class="rightDiv">
                            <div class="list-group">
                                <div style="position: absolute;width: 100%;height: 15%">
                                    <button type="button" class="btn btn-block disabled">运输公司列表</button>
                                </div>
                                <div style="position: absolute;width: 100%;height: 90%;top: 10%;left: 5%" class="row pre-scrollable">
                                    <%--节点列表--%>
                                    <c:forEach items="${transporters}" var="transporter">
                                        <button type="button" class="list-group-item list-group-item-success" data-toggle="modal" data-target="#transporterModel" onclick="liClicked(this,${transporter.id})" id="transporter_id">${transporter.companyName}</button>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="user">
                        <%--用户查看--%>
                        <div class="leftDiv">
                            <%--左边--%>
                            <button type="button" class="btn btn-block disabled">查看产品信息</button>
                            <br>
                            <br><br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon14">产品编号</span>
                                <input type="text" class="form-control" id="product_code" placeholder="输入号码" aria-describedby="basic-addon14">
                            </div>
                            <br>
                            <br>
                            <br>
                            <br>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#userModel" onclick="userCheck()">查 看</button>
                        </div>
                        <%--右边--%>
                        <div class="cutLine"></div>
                        <div class="rightDiv">
                            <div class="flag"></div>
                            <div class="content">
                                <img src="pictures/placeQR.jpg" width="200" height="200" id="qr_img">
                            </div>
                            <span class="text-info" style="position: absolute;top: 80%;width: 100%;height: 100%;left: -2%"><a href="#" id="get_code">点击以获取二维码</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%--模态窗体--%>

    <%------------------------------------------------生产方-------------------------------------------%>
    <div class="modal fade" id="producerModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">生产厂商信息</h4>
                </div>
                <div class="modal-body">
                    <%--内容--%>
                    <%--厂商信息--%>
                    <div>
                        <ul class="list-group" id="producer_list">
                            <li class="list-group-item" id="producer_name">厂商名字:</li>
                            <li class="list-group-item" id="producer_loca">地址：</li>
                            <li class="list-group-item" id="producer_tel">联系电话：</li>
                        </ul>
                    </div>
                        <%--产品信息--%>
                    <div id="productInfo">
                        <form id="producer_submit_form">
                        <button type="button" class="btn btn-block disabled">注入产品信息</button>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_a">产品名字</span>
                            <input type="text" class="form-control" name="product_name" placeholder="产品名字" aria-describedby="basic-addon_a">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_b">产品原料</span>
                            <input type="text" class="form-control" name="raw_metirial" placeholder="产品原料" aria-describedby="basic-addon_b">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_c">产品类别</span>
                            <input type="text" class="form-control" name="product_catagory" placeholder="类别" aria-describedby="basic-addon_c">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_d">产品数量</span>
                            <input type="text" class="form-control" name="product_sum" placeholder="产品数量" aria-describedby="basic-addon_d">
                        </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button" id="producer_close">关闭</button>
                    <button class="btn btn-primary" type="button" id="producer_submit">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>


    <%----------------------------------------模态窗体加工方----------------------------------------------%>
    <div class="modal fade" id="processerModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">加工厂信息</h4>
                </div>
                <div class="modal-body">
                    <%--内容--%>
                    <%--厂商信息--%>
                    <div>
                        <ul class="list-group">
                            <li class="list-group-item" id="processer_name">名字:</li>
                            <li class="list-group-item" id="processer_method">加工方式:</li>
                            <li class="list-group-item" id="processer_catagory">加工种类:</li>
                            <li class="list-group-item" id="processer_tel">联系电话:</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button" id="process_close">关闭</button>
                    <button class="btn btn-primary" type="button" id="processer_submit">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>


    <%----------------------------------------模态窗体商店----------------------------------------------%>
    <div class="modal fade" id="storeModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">销售商店信息</h4>
                </div>
                <div class="modal-body">
                    <%--内容--%>
                    <%--厂商信息--%>
                    <div>
                        <ul class="list-group">
                            <li class="list-group-item" id="store_name">店 名:</li>
                            <li class="list-group-item" id="store_person_in_charge">加工方式:</li>
                            <li class="list-group-item" id="store_tel">联系电话:</li>
                        </ul>
                    </div>
                    <div id="productInfo">
                            <button type="button" class="btn btn-block disabled">注入产品信息</button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon_a">售 价</span>
                                <input type="text" id="price" class="form-control" name="price" placeholder="输入价格" aria-describedby="basic-addon_a">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon_b">是否安全认证</span>
                                <select class="form-control" id="store_select">
                                    <option selected="selected">已认证</option>
                                    <option>未认证</option>
                                </select>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button" id="store_close">关闭</button>
                    <button class="btn btn-primary" type="button" id="store_subumit">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <%----------------------------------------模态窗体运输公司----------------------------------------------%>
    <div class="modal fade" id="transporterModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">运输公司信息</h4>
                </div>
                <div class="modal-body">
                    <%--内容--%>
                    <%--厂商信息--%>
                    <div>
                        <ul class="list-group">
                            <li class="list-group-item" id="transporter_name">店 名:</li>
                            <li class="list-group-item" id="transporter_person_in_charge">负责人:</li>
                            <li class="list-group-item" id="transporter_tel">联系电话:</li>
                        </ul>
                    </div>
                    <div id="productInfo">
                        <button type="button" class="btn btn-block disabled">注入产品信息</button>
                        <form id="transport_form">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_a">运输起点</span>
                            <input type="text" class="form-control" name="start" placeholder="输入起点" aria-describedby="basic-addon_a">
                        </div>
                            <br>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_a">运输终点</span>
                            <input type="text" class="form-control" name="destination" placeholder="输入终点" aria-describedby="basic-addon_a">
                        </div>
                            <br>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon_a">运输成本</span>
                            <input type="text" class="form-control" name="cost" placeholder="输入成本" aria-describedby="basic-addon_a">
                        </div>
                            <br>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button" id="transport_close">关闭</button>
                    <button class="btn btn-primary" type="button" id="transport_submit">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
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
    $("#manage_btn").click(function () {
        //跳转到管理页面
        location.href = "/turnToManagePage";
    })


    /*单个结点点击响应*/
    liClicked = function (object,proId) {
        if (object.id == "producerId"){
            // console.log("节点id为：" +proId);
            $.ajax({
                url: "/produceData.post",
                type: "post",
                data:{"id":proId},
                dataType:"json",
                success: function(result){
                    // console.log(result);
                    $("#producer_name").html("厂商名字:" +result.name);
                    $("#producer_loca").html("地址:" +result.loca);
                    $("#producer_tel").html("联系电话:" +result.tel);
                }
            });
        }else if (object.id == "processerId"){
            // console.log("节点id为：" +proId);
            $.ajax({
                url: "/processData.post",
                type: "post",
                data:{"id":proId},
                dataType:"json",
                success: function(result){
                    // console.log(result);
                    $("#processer_name").html("名字:" +result.name);
                    $("#processer_method").html("加工方式: " +result.pro_method);
                    $("#processer_catagory").html("加工种类: " +result.pro_catagory);
                    $("#processer_tel").html("联系电话:" +result.tel);
                }
            });
        }else if (object.id == "store_id"){
            // console.log("节点id为：" +proId);
            $.ajax({
                url:"/storeData.post",
                type:"post",
                data:{"idStr":proId},
                dataType:"json",
                success:function (result) {
                    // console.log(result);
                    $("#store_name").html("店 名:" +result.name);
                    $("#store_person_in_charge").html("负责人:" +result.person_in_charge);
                    $("#store_tel").html("联系电话:" +result.tel);
                }
            });
        }else if (object.id == "transporter_id"){
            //    console.log("节点id为：" +proId);
            $.ajax({
                url:"/transporterData.post",
                type:"post",
                data:{"idStr":proId},
                dataType:"json",
                success:function (result) {
                    // console.log(result);
                    $("#transporter_name").html("公司名字:" +result.name);
                    $("#transporter_person_in_charge").html("负责人:" +result.person_in_charge);
                    $("#transporter_tel").html("联系电话:" +result.tel);
                }
            });
        }

    }


    /*加工方式列表*/
    $(".progress_method").click(function () {
        $("#process_method_input").attr("value",$(this).html());
        $("#process_method_input").attr("name","method");
    })

    /*加工种类列表*/
    $(".progress_catagory").click(function () {
        $("#process_catagory_input").attr("value",$(this).html());
        $("#process_catagory_input").attr("name","catagory");
    })

    /*____________生产方注册——————————————————————————*/
    $("#producer_register").click(function () {
        nodeRegist("/producerRegist.post","#producer_form");
    })

    /*---------------加工方注册------------------------*/
    $("#process_regist").click(function () {
        nodeRegist("/processRegist.post","#process_form");
    })

    /*---------------商店注册------------------------*/
    $("#store_regist").click(function () {
        nodeRegist("/storeRegist.post","#store_form");
    })

    /*---------------运输节点注册------------------------*/
    $("#transporter_regist").click(function () {
        nodeRegist("/transporterRegist.post","#delivery_form");
    })


    /**节点注册
     * @param {string} url
     * @param {string} formId
     */
    function nodeRegist(url,formId){
        var dataJson = getFormData(formId);
        $.ajax({
            url:url,
            type:"post",
            data: dataJson,
            dataType: "json",
            contentType : "application/json;charset=UTF-8",
            success:function (a) {
                alertMessage(a.result);
                setTimeout(function () {
                    location.href = location.href;
                    location.reload(true);
                },1000);
            }
        });
    }


    /*---------------------------------订单信息处理----------------------------------*/
    /*点击新建订单*/
    $("#new_order").click(function () {
        skipToNav("#produce_nav");
        // console.log("来了！");
        let start = new Date().getMilliseconds();
        $.get(
            "/newOrder.get",
            function (msg) {
                let end = new Date().getMilliseconds();
                alertMessage("生成时间为:" +(end-start)+"<br><br>新建区块的hash值为：<br>" +msg+ "<br><br>请选择第一节点")
            }
        );
    })

    /*生产节点数据提交*/
    $("#producer_submit").click(function () {
        var data = {};
        $("#producerModel li").each(function (i,n) {
            data[n.id] = n.innerText.split(":")[1];
        })
        var allData = $.mergeJsonObject(JSON.stringify(data),getFormData("#producer_submit_form"));
        submitNodeData("/producerNodeData.post",allData);
        $("#producer_close").click();
        skipToNav("#process_nav");
    })

    /*加工节点数据提交*/
    $("#processer_submit").click(function () {
        var data = {};
        $("#processerModel li").each(function (i,n) {
            data[n.id] = n.innerText.split(":")[1];
        })
        var allData = JSON.stringify(data);
        submitNodeData("/processerNodeData.post",allData);
        $("#process_close").click();
        skipToNav("#store_nav");
    })

    /*销售节点数据提交*/
    $("#store_subumit").click(function () {
        var data = {};
        $("#storeModel li").each(function (i,n) {
            data[n.id] = n.innerText.split(":")[1];
        })
        var formData = {
            "price" : $("#price").val(),
            "autestation": $("#store_select").val()
        };
        var allData = $.mergeJsonObject(JSON.stringify(data),JSON.stringify(formData));
        submitNodeData("/storeNodeData.post",allData);
        $("#store_close").click();
        skipToNav("#transport_nav");
    })

    /*运输节点数据提交*/
    $("#transport_submit").click(function () {
        var data = {};
        $("#transporterModel li").each(function (i,n) {
            data[n.id] = n.innerText.split(":")[1];
        })
        var allData = $.mergeJsonObject(JSON.stringify(data),getFormData("#transport_form"));
        $.ajax({
            url:"/transportNodeData.post",
            data:allData,
            type:"post",
            dataType:"json",
            contentType: "application/json;charset=UTF-8",
            success:function () {

            }
        });
        $("#transport_close").click();
        skipToNav("#user_nav");
        $.get(
            "/code.get",
            function (data) {
                alertMessage(data.result);
            }
        )
    })

    /*跳转指定导航页*/
    function skipToNav(navId){
        $(navId).append("<span></span>");
        $(navId+ " span").click();
    }


    /*提取form表单数据,返回json字符串*/
    function getFormData(formId) {
        var data = {};
        $(formId).serializeArray().map(function (val,key) {
            data[val.name] = val.value;
        })
        return JSON.stringify(data);
    }

    /*合并两个json字符串*/
    $.mergeJsonObject = function (jsonbject1, jsonbject2) {
        return jsonbject1.split("}")[0]+ "," +jsonbject2.split("{")[1];
    };

    /*提交节点给出的产品数据*/
    function submitNodeData(url,data) {
        $.ajax({
            url:url,
            data:data,
            type:"post",
            dataType:"json",
            contentType: "application/json;charset=UTF-8",
            success:function (result) {
                alertMessage(result.returned);
            }
        });
    }

    /*信息提示方法*/
    function alertMessage(msg) {
        $("#serverMessage").html(msg);
        $("#messageModel").modal('show');
    }

    /*读取二维码，更改图片*/
    $("#get_code").click(function () {
        $.get(
            "/qr.get",
            function (result) {
                console.log(result);
                $("#qr_img").attr("src",result.url);
            }
        )
    })

    //用户查看
    userCheck = function () {
        var value = $("#product_code").val();
        $.get(
            "/data.get",
            {"code" : value},
            function (data) {
                alertMessage(data.result);
            }

        )
    }

</script>
</body>
</html>
