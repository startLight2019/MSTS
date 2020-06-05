package com.trace.all.chain;

import java.util.ArrayList;
import java.util.Date;

/**
 * 封装区块对象
 * @author zx*/
public class Block {

//    当前区块hash
    public String hash;

//    上一个区块的hash
    public String previousHash;

    private String merkleRoot;

    //时间戳
    private long timeStamp;

//    区块中存储的所有交易
    public ArrayList<Transaction> transactions = new ArrayList<>();

    //挖矿者的工作量证明
    private int nonce;

//    构造
    public Block(String previousHash){
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

//        根据previousHash.data和timeStamp产生位移hash
        this.hash = calculateHash();
    }

    //基于上一块的内容计算新的散列
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce)+
                        merkleRoot
        );
        return calculatedhash;
    }

    //计算新区块
    public void mineBlock(int difficulty) {
        //目标值，difficulty越大，下面计算量越大
        String target = StringUtil.getDificultyString(difficulty);

        //difficulty如果为5，那么target则为 00000
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
//        System.out.println("创建区块:" + hash);
    }

    /**添加交易到这个区块中*/
    public boolean addTransaction(Transaction transaction){
//        执行交易并检查是否有效，除非这是创世区块
        if (transaction == null){
            return false;
        }
//        不是创世区块
        if (previousHash != "0"){
            if (transaction.processTransaction() != true){
//                交易执行失败
                return false;
            }
        }
        transactions.add(transaction);
        return true;
    }

}
