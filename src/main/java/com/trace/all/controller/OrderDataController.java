package com.trace.all.controller;

import com.trace.all.chain.*;
import com.trace.all.controller.global.NodeEnum;
import com.trace.all.controller.global.OrderBlock;
import com.trace.all.qrcode.QRcodeUtil;
import org.hibernate.criterion.Order;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理节点提交的数据
 */
@Controller
public class OrderDataController {



    /*----------------------------------------订单信息提取--------------------------------------*/
    @RequestMapping("/newOrder.get")
    @ResponseBody
    public String newOrder(){
        OrderBlock.block = new Block(BlockChain.chain.get(BlockChain.chain.size() - 1).hash);
        return OrderBlock.block.hash;
    }

//    获取生产
    @RequestMapping("/producerNodeData.post")
    @ResponseBody
    public Map<String, String> producerNodeData(@RequestBody Map<String,String>params){
        Map<String,String> returnMap = new HashMap<>();
        System.out.println(params);
        String producerName = params.get("producer_name");
        Keys productKeys = BlockChain.nodeKeys.get(producerName);
        OrderBlock.callBackString = "第一节点信息: <br><br>"
                +"生产厂家:&emsp;" +producerName
                +"<br>厂址:&emsp;" +params.get("producer_loca")
                +"<br>联系厂家:&emsp;" +params.get("producer_tel")
                +"<br><br>产品信息:<br>"
                +"<br>产品名称:&emsp;" +params.get("product_name")
                +"<br>产品原料:&emsp;" +params.get("raw_metirial")
                +"<br>产品类型:&emsp;" +params.get("product_catagory")
                +"<br>产品数量:&emsp;" +params.get("product_sum")
                +"<br><br><br>";

        ArrayList<String> initList = new ArrayList<>();
        initList.add(OrderBlock.callBackString);

//        添加交易到区块
        String returnStr = "";
        if (OrderBlock.block == null){
            returnStr = "未创建订单，不可提交";
        }else if (hasBlank(params) == true){
            returnStr = "不能提交空值！";
        }else {
            Transaction transaction = addTansaction(producerName,productKeys,initList);
            returnStr = "节点交易id:&emsp;"
                    +transaction.transactionId
                    +"<br><br>节点公匙:"
                    +"<br>" +productKeys.publicKey
                    +"<br><br>请选择下一节点";
            returnMap.put("returned",returnStr);
//            节点已提交
            OrderBlock.hasNodePut.put(NodeEnum.PRODUCERENUM,true);
        }
        returnMap.put("returned",returnStr);
        return returnMap;
    }


    /*获取加工厂商信息*/
    @RequestMapping("/processerNodeData.post")
    @ResponseBody
    public Map<String,String> processerNode(@RequestBody Map<String,String> params){
        System.out.println(params);
        String returnStr = "";

        if (OrderBlock.block == null){
            returnStr = "未创建订单，提交失败";
        }else if (hasBlank(params) == true){
            returnStr = "不能提交空值！";
        }else if (OrderBlock.hasNodePut.get(NodeEnum.PRODUCERENUM)){
            String processerName = params.get("processer_name");
            Keys processerKeys = BlockChain.nodeKeys.get(processerName);

            String nodeStr = "第二节点信息: <br><br>"
                    +"加工厂家:&emsp;" +processerName
                    +"<br>加工方式:&emsp;" +params.get("processer_method")
                    +"<br>加工种类:&emsp;" +params.get("processer_catagory")
                    +"<br>联系电话:&emsp;" +params.get("processer_tel")
                    +"<br><br><br>";

            OrderBlock.callBackString += nodeStr;

            ArrayList<String> initList = new ArrayList<>();
            initList.add(nodeStr);

            Transaction transaction = addTansaction(processerName,processerKeys,initList);
            returnStr = "节点交易id:&emsp;"
                    +transaction.transactionId
                    +"<br><br>节点公匙:"
                    +"<br>" +processerKeys.publicKey
                    +"<br><br>请选择下一节点";
//            节点已提交
            OrderBlock.hasNodePut.put(NodeEnum.PROCESSERENUM,true);
        }else {
            returnStr = "上一节点未提交产品数据，提交失败!";
        }

        Map<String,String> returnMap = new HashMap<>();
        returnMap.put("returned",returnStr);
        return returnMap;
    }

    /*获取销售节点信息*/
    @RequestMapping("/storeNodeData.post")
    @ResponseBody
    public Map<String,String> storeNode(@RequestBody Map<String,String> params){
        System.out.println(params);
        String returned = "";

        if (OrderBlock.block == null){
            returned = "未创建订单，提交失败";
        }else if (hasBlank(params) == true){
            returned = "不能提交空值！";
        }else if (OrderBlock.hasNodePut.get(NodeEnum.PROCESSERENUM)){
            String storeName = params.get("store_name");
            Keys storeKeys = BlockChain.nodeKeys.get(storeName);

            String nodeStr = "第三节点信息: <br><br>"
                    +"商家名字:&emsp;" +storeName
                    +"<br>负责人:&emsp;" +params.get("store_person_in_charge")
                    +"<br>联系电话:&emsp;" +params.get("store_tel")
                    +"<br><br>产品信息:"
                    +"<br><br>"
                    +"售价:&emsp;" +params.get("price")
                    +"<br>认证:&emsp;" +params.get("autestation")
                    +"<br><br><br>";

            OrderBlock.callBackString += nodeStr;

            ArrayList<String> initList = new ArrayList<>();
            initList.add(nodeStr);

            Transaction transaction = addTansaction(storeName,storeKeys,initList);
            returned = "节点交易id:&emsp;"
                    +transaction.transactionId
                    +"<br><br>节点公匙:"
                    +"<br>" +storeKeys.publicKey
                    +"<br><br>请选择下一节点";
//            节点已提交
            OrderBlock.hasNodePut.put(NodeEnum.STOREENUM,true);
        }else {
            returned = "上一节点未提交产品数据，提交失败!";
        }


        Map<String,String> map = new HashMap<>();
        map.put("returned",returned);
        return map;
    }


    /*获取运输公司信息*/
    @RequestMapping("/transportNodeData.post")
    @ResponseBody
    public Map<String,String> transportNode(@RequestBody Map<String,String> params) {
        System.out.println(params);
        String returnStr = "";

        if (OrderBlock.block == null) {
            returnStr = "未创建订单，提交失败";
        } else if (hasBlank(params) == true) {
            returnStr = "不能提交空值！";
        }else if (OrderBlock.hasNodePut.get(NodeEnum.STOREENUM)){
            String transporterName = params.get("transporter_name");
            Keys transKeys = BlockChain.nodeKeys.get(transporterName);

            String nodeStr = "第四节点信息: <br><br>"
                    +"公司名字:&emsp;" +transporterName
                    +"<br>负责人:&emsp;" +params.get("transporter_person_in_charge")
                    +"<br>联系电话:&emsp;" +params.get("transporter_tel")
                    +"<br><br>产品信息:"
                    +"<br><br>"
                    +"运输起点:&emsp;" +params.get("start")
                    +"<br>运输终点:&emsp;" +params.get("destination")
                    +"<br>运输成本:&emsp;" +params.get("cost")
                    +"<br><br><br>";

            OrderBlock.callBackString += nodeStr;

            ArrayList<String> initList = new ArrayList<>();
            initList.add(nodeStr);

            Transaction transaction = addTansaction(transporterName,transKeys,initList);
//            添加进区块链
            BlockChain.addBlock(OrderBlock.block);
            returnStr = "节点交易id:&emsp;"
                    +transaction.transactionId
                    +"<br><br>节点公匙:"
                    +"<br>" +transKeys.publicKey
                    +"<br><br>请选择下一节点";
//            节点已提交
            OrderBlock.hasNodePut.put(NodeEnum.TRANSPORTERENUM,true);
        }else {
            returnStr = "上一节点未提交产品数据，提交失败!";
        }
        Map<String,String> map = new HashMap<>();
        map.put("returned",returnStr);
        createQRCode();
        return map;
    }


    /*获取产品编码*/
    @RequestMapping("/code.get")
    @ResponseBody
    public Map<String,String> getCode() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("result","产品编码：" +OrderBlock.block.hashCode());
        return map;
    }

    /*获取block数据*/
    @RequestMapping("/data.get")
    @ResponseBody
    public Map<String,String> getData(@RequestParam("code") String code) throws Exception {
        Map<String,String> map = new HashMap<>();
       /* System.out.println(code);
        System.out.println(OrderBlock.block.hashCode());*/
        if (Integer.parseInt(code) == OrderBlock.block.hashCode()){
            System.out.println("获取到了数据！");
            map.put("result",OrderBlock.callBackString);
        }else {
            map.put("result","编码错误!");
        }
        return map;
    }



    @RequestMapping("/qr.get")
    @ResponseBody
    public Map<String,String> getQRcode() throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("url","pictures/QRimgs/test.png");
        return map;
    }

    /*将所有数据录入二维码*/
    private void createQRCode(){
        try {
            System.out.println(OrderBlock.callBackString);
            String serverPath = QRcodeUtil.buildQuickMark(OrderBlock.callBackString,getSavePath(),"test");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param keysName 添加到交易到区块里
     * @param recipientKeys
     * @param data
     * @return
     */
    private Transaction addTansaction(String keysName,Keys recipientKeys, ArrayList<String> data){
        Keys keys = new Keys();
//            创建交易并赋值给该钱包
        Transaction tempTransaction = new Transaction(BlockChain.genesisKeys.publicKey,recipientKeys.publicKey,data,null);
//            私匙加密
        tempTransaction.generateSignature(BlockChain.genesisKeys.privateKey);
//            设置初始交易的id
        tempTransaction.transactionId = Integer.toString(keysName.hashCode());
        tempTransaction.outputs.add(new TransactionOutput(tempTransaction.reciepient,data,tempTransaction.transactionId));
        BlockChain.UTXQs.put(tempTransaction.outputs.get(0).id,tempTransaction.outputs.get(0));

        tempTransaction.nodeName = keysName;

        OrderBlock.block.transactions.add(tempTransaction);
        return tempTransaction;
    }

    /** 判断输入参数中是否有空值
     * @param params
     * @return
     */
    private boolean hasBlank(Map<String,String> params){
        boolean hashBlank = false;
        for (String key: params.keySet()) {
            if (params.get(key) == ""){
                hashBlank = true;
                break;
            }
        }
        return hashBlank;
    }

    /*二维码图片存储路径*/
    public static String getSavePath() throws FileNotFoundException {
        // 项目路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists())
            path = new File("");
        // 绝对路径=项目路径+自定义路径
        File upload = new File(path.getAbsolutePath(), "static/pictures/QRimgs");
        if (!upload.exists()){
            upload.mkdirs();
        }
        // 绝对路径=项目路径+打包的
        return upload.getAbsolutePath();
    }


}




