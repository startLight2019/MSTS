package com.trace.all.controller;

import com.trace.all.chain.InitData;
import com.trace.all.pojo.Processer;
import com.trace.all.pojo.Producer;
import com.trace.all.pojo.Store;
import com.trace.all.pojo.Transporter;
import com.trace.all.service.ProcesserService;
import com.trace.all.service.ProducerService;
import com.trace.all.service.StoreService;
import com.trace.all.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private ProcesserService processerService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private TransporterService transporterService;


    /*—————————————————————————————————页面加载———————————————————————————————————————*/
    @RequestMapping("/register")
    public String loadRegitserPage(Model model) {
//        传递初始化数据
//        生产厂商列表
        List<Producer> producers = producerService.findAll();
//        System.out.println(producers);
        model.addAttribute("producers",producers);

//        加工厂商列表
        List<Processer> processers = processerService.findAll();
        model.addAttribute("processers",processers);

//        销售商店
        List<Store> stores = storeService.findAll();
        model.addAttribute("stores",stores);

//        运输方
        List<Transporter> transporters = transporterService.findAll();
        model.addAttribute("transporters",transporters);

//        System.out.println(transporters);
        return "NodeRegisterPage";
    }


/*___________________________________传递单节点数据________________________________________*/
//  根据前端ajax传输的id搜索对应生产商
    @RequestMapping("/produceData.post")
    @ResponseBody
    public Map<String,String> producerData(String id){
        int Id = Integer.parseInt(id);
        Producer producer = producerService.findById(Id);
        Map<String,String> result = new HashMap<>();
        result.put("name",producer.getName());
        result.put("loca",producer.getLocation());
        result.put("tel",producer.getTel());
        return result;
    }

//    搜索对应加工厂
    @RequestMapping("/processData.post")
    @ResponseBody
    public Map<String,String> processerData(String id){
//        System.out.println(id);
        int idInt = Integer.parseInt(id);
        Processer processer = processerService.findById(idInt);

        Map<String,String> result = new HashMap<>();
        result.put("name",processer.getName());
        result.put("pro_catagory",processer.getCatagory());
        result.put("pro_method",processer.getMethod());
        result.put("tel",processer.getTel());

        return result;
    }

    //    搜索对应商店
    @RequestMapping("/storeData.post")
    @ResponseBody
    public Map<String,String> storeData(String idStr){
        System.out.println(idStr);
        int idInt = Integer.parseInt(idStr);
        Store store = storeService.findById(idInt);

        Map<String,String> result = new HashMap<>();
        result.put("name",store.getName());
        result.put("person_in_charge",store.getPersonInCharge());
        result.put("tel",store.getTel());

        return result;
    }

    //    搜索对应运输公司
    @RequestMapping("/transporterData.post")
    @ResponseBody
    public Map<String,String> transporterData(String idStr){
//        System.out.println(idStr);
        int idInt = Integer.parseInt(idStr);
        Transporter transporter = transporterService.findById(idInt);

        Map<String,String> result = new HashMap<>();
        result.put("name",transporter.getCompanyName());
        result.put("person_in_charge",transporter.getPersonInCharge());
        result.put("tel",transporter.getTel());

        return result;
    }


    /*—————————————————————————————————注册信息提取与反馈———————————————————————————————————————————————————*/

//    接收生产厂商注册
    @RequestMapping(value = "/producerRegist.post",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> producerRegister(@RequestBody Map<String,String> param) {

        String proName = param.get("name"),
               proLoca = param.get("location"),
               proTel = param.get("tel");
        Producer producer = new Producer();
        producer.setName(proName);
        producer.setLocation(proLoca);
        producer.setTel(proTel);

//        System.out.println("来了：" +producer);

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","添加成功");
        try {
//            添加进数据库
            producerService.insertPoducer(producer);
//            刷新钱包数据
            InitData.initData();
        }catch (Exception e){
            resultMap.put("result","数据库异常，添加失败！");
        }
        return resultMap;
    }

//    接收加工厂注册
    @RequestMapping("/processRegist.post")
    @ResponseBody
    public Map<String,String> processRegist(@RequestBody Map<String,String> params){
        Processer processer = new Processer();
        processer.setName(params.get("name"));
        processer.setMethod(params.get("method"));
        processer.setCatagory(params.get("catagory"));
        processer.setTel(params.get("tel"));

//        System.out.println(processer);

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","添加成功");

        try {
//            添加进数据库
            processerService.insertProcesser(processer);
            InitData.initData();
        }catch (Exception e){
            resultMap.put("result","数据库异常，添加失败！");
        }
        return resultMap;
    }

    //    接收商店注册
    @RequestMapping("/storeRegist.post")
    @ResponseBody
    public Map<String,String> storeRegist(@RequestBody Map<String,String> params){
        Store store = new Store();
        store.setName(params.get("name"));
        store.setPersonInCharge(params.get("person_in_charge"));
        store.setTel(params.get("tel"));

        System.out.println(store);

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","添加成功");

        try {
//            添加进数据库
            storeService.insertStore(store);
//            刷新钱包数据
            InitData.initData();
        }catch (Exception e){
            resultMap.put("result","数据库异常，添加失败！");
        }
        return resultMap;
    }

    //    接收运输公司注册
    @RequestMapping("/transporterRegist.post")
    @ResponseBody
    public Map<String,String> tranporterRegist(@RequestBody Map<String,String> params){
        Transporter transporter = new Transporter();
        transporter.setCompanyName(params.get("name"));
        transporter.setPersonInCharge(params.get("person_in_charge"));
        transporter.setTel(params.get("tel"));

//        System.out.println(transporter);

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("result","添加成功");

        try {
//            添加进数据库
            transporterService.insertTransporter(transporter);
//            刷新钱包数据
            InitData.initData();
        }catch (Exception e){
            resultMap.put("result","数据异常，添加失败！");
        }
        return resultMap;
    }

}
