package com.trace.all.controller;

import com.trace.all.chain.Block;
import com.trace.all.chain.BlockChain;
import com.trace.all.chain.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DealManageController {

    @RequestMapping("/managePage.get")
    @ResponseBody
    public Map<String,Integer> loadDealManageController(){
        Map<String,Integer> map = new HashMap<>();
        System.out.println("来了！");
        map.put("result",1);
        return map;
    }

    @RequestMapping("/turnToManagePage")
    public String turnToPage(Model model){
        Map<String,String> resultMap = new HashMap<>();
        for (int i = 1 ; i <BlockChain.chain.size();i++){
            Block block = BlockChain.chain.get(i);
            String valueStr = "";
            for (int j = 0;j < block.transactions.size();j++){
                Transaction transaction = block.transactions.get(j);
                if (j == block.transactions.size() - 1){
                    valueStr += transaction.nodeName;
                }else {
                    valueStr += transaction.nodeName+ "——";
                }
            }
            resultMap.put(block.hash,valueStr);
        }
        model.addAttribute("map",resultMap);
        return "DealManagePage";
    }
}
