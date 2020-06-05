package com.trace.all.chain;


import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockChain {

//    存储所有的区块集合
    public static ArrayList<Block> chain = new ArrayList<>();

//    每一个节点对应的可用输出
    public static HashMap<String,TransactionOutput> UTXQs = new HashMap<>();

//    数字越大越难创建
    public static int mount = 2;

    //    创世区块
    public static Block genesis = new Block("0");
//    创世钱包
    public static Keys genesisKeys = new Keys();

//    区块链创建以来的所有订单信息,一个区块的hash对应一个订单
    public static HashMap<String,String> allData = new HashMap<>();

//    public static float minimumTransaction = 0.1f;

//    所有的钱包,每一个节点对应的钱包，使用其主键生成
    public static HashMap<String,Keys> nodeKeys = new HashMap<>();

    /*增加一个区块*/
    public static void addBlock(Block newBlock){
        newBlock.mineBlock(mount);
        chain.add(newBlock);
    }

    /*检查链的完整性*/
    public static Boolean isValid(){
        Block currentBlock;
        Block previousBlock;

        String hashTarget = new String(new char[mount]).replace('\0','0');
        HashMap<String,TransactionOutput> tempUTXOs = new HashMap<>();
//        tempUTXOs.put(geneesisTransaction.outputs.get(0).id,geneesisTransaction.outputs.get(0));



//        循环区块链检查散列
        for (int i = 1;i < chain.size();i++){
            currentBlock = chain.get(i);
            previousBlock = chain.get(i - 1);

//            比较注册散列和计算散列
            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                return false;
            }

//            比较以前的散列和注册的先前散列
            if (!previousBlock.hash.equals(currentBlock.previousHash)){
                return false;
            }

//            检查hash是否被使用
            if (!currentBlock.hash.substring(0,mount).equals(hashTarget)){
                return false;
            }

            /*循环检查区块链的交易*/
            TransactionOutput tempOut;
            for (int t = 0;t < currentBlock.transactions.size();t++){
                Transaction currentTransaction = currentBlock.transactions.get(t);
                if (!currentTransaction.verifiySignature()){
                    return false;
                }
                if (currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()){
                    return false;
                }

                for (TransactionInput input : currentTransaction.inputs){
                    tempOut = tempUTXOs.get(input.transactionOutputId);

                    if (tempOut == null){
                        return false;
                    }
                    if (!input.UTXO.value.toString().equals(tempOut.value.toString())){
                        return false;
                    }
                    tempUTXOs.remove(input.transactionOutputId);
                }

                for (TransactionOutput output : currentTransaction.outputs){
                    tempUTXOs.put(output.id,output);
                }

                if (currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient){
                    return false;
                }

                if (currentTransaction.outputs.get(1).reciepient != currentTransaction.sender){
                    return false;
                }

            }
        }

        return true;
    }


}
