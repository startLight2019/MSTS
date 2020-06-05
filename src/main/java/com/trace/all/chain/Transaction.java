package com.trace.all.chain;

import javax.persistence.criteria.From;
import java.security.*;
import java.util.ArrayList;

//签名在我们的区块链中执行了两个非常重要的任务：
// 第一，签名用来保证只有货币的拥有者才可以用来发送自己的货币，
// 第二，签名用来阻止其他人试图篡改提交的交易。

public class Transaction {

//    交易的hash值,也就是id
    public String transactionId;

//    发送者公匙,用于验证签名
    public PublicKey sender;

//    接收者公匙，即此交易接受者地址
    public PublicKey reciepient;

//    交易内容,即产品信息
    public ArrayList<String> value;

//    一个加密签名，证明该交易是由地址的发送者是发送的，并且数据没有被更改。(阻止第三方机构更改发送的数量)
    public byte[] signature;

//    对发送者以前交易的引用，即发送者有过接收记录，即发送者的拥有资源
    public ArrayList<TransactionInput> inputs;

//    接收方接收资源数量
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();

    public String nodeName;

//  粗略计算有多少次交易产生
    private static int sequence = 0;


//    创建交易
    public Transaction(PublicKey from, PublicKey to, ArrayList<String> value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }

    // 计算本次交易的hash值
    private String calulateHash() {
//        交易计数增加
        sequence++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient)
                        + value + sequence
        );
    }

    //给需要保存的数据签名
    public void generateSignature(PrivateKey privateKey) {
        String valueStr = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + value;
        signature = StringUtil.applyECDSASig(privateKey,valueStr);
    }


    //验证签名的数据未被篡改
    public boolean verifiySignature() {
        String valueStr = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + value;
        return StringUtil.verifyECDSASig(sender, valueStr, signature);
    }

/**
 *  把对交易的所有处理放在一起*/
    public boolean processTransaction(){
//        签名数据验证失败
        if (verifiySignature() == false){
            return false;
        }

//        将未使用交易集中在一起
        for (TransactionInput i : inputs){
            i.UTXO = BlockChain.UTXQs.get(i.transactionOutputId);
        }

//        检查交易是否有效
        if (getInputsValue().size() == 0){
            return false;
        }

//        产生交易输出
        ArrayList<String> leftOver = new ArrayList<>();
        for (String temp : getInputsValue()){
            if (!value.contains(temp)){
                leftOver.add(temp);
            }
        }
//        getInputsValue() - value;
        transactionId = calulateHash();
//        将交易数额发送给接收者
        outputs.add(new TransactionOutput(this.reciepient,value,transactionId));
        outputs.add(new TransactionOutput(this.sender,leftOver,transactionId));

//        将输出添加到未花费列表
        for (TransactionOutput o : outputs){
            BlockChain.UTXQs.put(o.id,o);
        }

        for (TransactionInput i : inputs){
            if (i.UTXO == null){
                continue;
            }else {
                BlockChain.UTXQs.remove(i.UTXO.id);
            }
        }

        return true;
    }

   /**
    * 返回输入总量*/
    public ArrayList<String> getInputsValue(){
        ArrayList<String> total = new ArrayList<>();
        for (TransactionInput i : inputs){
//            如果没找到交易就跳过
            if (i.UTXO == null){
                continue;
            }else {
                total.addAll(i.UTXO.value);
            }
        }
        return total;
    }

    /**返回输出总量
     * */
    public ArrayList<String> getOutputsValue(){
        ArrayList<String> total = new ArrayList<>();
        for (TransactionOutput o : outputs){
            total.addAll(o.value);
        }
        return total;
    }
}