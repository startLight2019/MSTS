package com.trace.all.chain;

import com.google.gson.GsonBuilder;
import com.trace.all.pojo.Processer;
import com.trace.all.pojo.Producer;
import com.trace.all.pojo.Store;
import com.trace.all.pojo.Transporter;
import com.trace.all.service.ProcesserService;
import com.trace.all.service.ProducerService;
import com.trace.all.service.StoreService;
import com.trace.all.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 给每一个钱包添加初始值，即初始拥有的节点信息
 */
@Component
public class InitData implements CommandLineRunner {
    @Autowired
    ProducerService producerService;

    @Autowired
    ProcesserService processerService;

    @Autowired
    StoreService storeService;

    @Autowired
    TransporterService transporterService;

    private static InitData initData = new InitData();

//    初始钱包
    private static Keys initKeys = new Keys();


    /**
     * @param args 初始化加载数据
     */
    @Override
    public void run(String... args) {
//        启动时添加创世区块
        BlockChain.addBlock(BlockChain.genesis);
        init();
    }

    public static void initData(){
        initData.init();
    }

    /**
     * 初始化数据,每个节点都创建一个钱包
     */
    public void init(){
        List<Producer> producers = producerService.findAll();
        for (Producer p : producers){
            BlockChain.nodeKeys.put(p.getName(),new Keys());
        }

        List<Processer> processers = processerService.findAll();
        for (Processer p : processers){
            BlockChain.nodeKeys.put(p.getName(),new Keys());
        }

        List<Store> stores = storeService.findAll();
        for (Store p : stores){
            BlockChain.nodeKeys.put(p.getName(),new Keys());
        }

        List<Transporter> transporters = transporterService.findAll();
        for (Transporter p : transporters){
            BlockChain.nodeKeys.put(p.getCompanyName(),new Keys());
            ArrayList<String> test = new ArrayList<>();
            test.add("test");
            createKeys(p.getCompanyName(),test);
        }
    }

    /**
     * @param name
     * @param data
     */
    private static void createKeys(String name, ArrayList<String> data){
        Keys keys = new Keys();
//            创建交易并赋值给该钱包
        Transaction tempTransaction = new Transaction(initKeys.publicKey,keys.publicKey,data,null);
//            私匙加密
        tempTransaction.generateSignature(initKeys.privateKey);
//            设置初始交易的id
        tempTransaction.transactionId = Integer.toString(name.hashCode());
        tempTransaction.outputs.add(new TransactionOutput(tempTransaction.reciepient,data,tempTransaction.transactionId));
        BlockChain.UTXQs.put(tempTransaction.outputs.get(0).id,tempTransaction.outputs.get(0));

        BlockChain.genesis.addTransaction(tempTransaction);

    }

    //返回JSON格式数据
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

}
