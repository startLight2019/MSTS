package com.trace.all.chain;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 搜集余额（通过循环遍历UTXO列表来检查交易的输出是否是我的）并
 * 创建交易
 * 存储节点的公匙和私匙*/
public class Keys {
//    私匙用于接受方获取付款使用权，对每个交易进行签名
    public PrivateKey privateKey;
//    作为接收者的地址，作为发送方的验证
    public PublicKey publicKey;

//当前节点所拥有的产品数量
    public HashMap<String,TransactionOutput> UTXOs = new HashMap<>();

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Keys(){
        generateKeyPair();
    }

    /**创建结点公匙和私匙*/
    public void generateKeyPair(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**从整条链上确定返回余额并存储*/
    public ArrayList<String> getBalance(){
        ArrayList<String> total = new ArrayList<>();
        for (Map.Entry<String,TransactionOutput> item : BlockChain.UTXQs.entrySet()){
            TransactionOutput UTXO = item.getValue();
//            如果输出属于该节点
            if (UTXO.isMine(publicKey)){
                UTXOs.put(UTXO.id,UTXO);
                total.addAll(UTXO.value);
            }
        }
        return total;
    }

    /**输入接收者和发送的资源，产生并返回一个新的交易*/
    public Transaction sendFunds(PublicKey recipient,ArrayList<String> value){
        if (!getBalance().containsAll(value)){
//            余额不足
            return null;
        }

//        该钱包的输入列表
        ArrayList<TransactionInput> inputs = new ArrayList<>();

        ArrayList<String> total = new ArrayList<>();
        for (Map.Entry<String,TransactionOutput> item : UTXOs.entrySet()){
            TransactionOutput UTXO = item.getValue();
            total.addAll(UTXO.value);
            inputs.add(new TransactionInput(UTXO.id));
            if (total.containsAll(value)){
                break;
            }
        }

        Transaction newTransaction = new Transaction(publicKey,recipient,value,inputs);
//        使用私匙签名
        newTransaction.generateSignature(privateKey);

        for (TransactionInput input : inputs){
//            移除掉使用后的输出，相当于把货币交易出去
            UTXOs.remove(input.transactionOutputId);
        }
        return newTransaction;
    }
}
