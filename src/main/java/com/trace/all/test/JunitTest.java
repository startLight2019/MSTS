package com.trace.all.test;

import com.trace.all.chain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.Security;


/*
* 测试类*/

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class JunitTest {

    @Before
    public void before(){
        System.out.println();
        System.out.println("开始测试：--------------------------");
        System.out.println();
    }

    @Test
    public void TestBlock() {

   /*     Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //Create the new wallets
        Keys walletA = new Keys();
        Keys walletB = new Keys();
        //Test public and private keys
        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
        //Create a test transaction from WalletA to walletB
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, "test", null);
        transaction.generateSignature(walletA.privateKey);
        //Verify the signature works and verify it from the public key
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());*/

//        创建钱包
        Keys A = new Keys();
        Keys B = new Keys();

//        初始钱包
        Keys productsBase = new Keys();

//        创建初始交易并交付给A
        Transaction geneesisTransaction = new Transaction(productsBase.publicKey,A.publicKey,null,null);
//        私匙用于A确实地获取到
        geneesisTransaction.generateSignature(productsBase.privateKey);
//        手动设置创世交易的id
        geneesisTransaction.transactionId = "0";
        geneesisTransaction.outputs.add(
                new TransactionOutput(geneesisTransaction.reciepient
                ,geneesisTransaction.value
                      ,  geneesisTransaction.transactionId));
        BlockChain.UTXQs.put(geneesisTransaction.outputs.get(0).id,geneesisTransaction.outputs.get(0));

        System.out.println("创建创世区块：");
        Block genesis = new Block("0");
        genesis.addTransaction(geneesisTransaction);
        BlockChain.addBlock(genesis);

//        测试
        Block block1 = new Block(genesis.hash);
        System.out.println("A的余额为：" +A.getBalance());
        System.out.println("A向B发送40：");
        block1.addTransaction(A.sendFunds(B.publicKey,null));
        BlockChain.addBlock(block1);
        System.out.println("A的余额为：" +A.getBalance());
        System.out.println("B的余额为：" +B.getBalance());

//        检查链的完整性
        BlockChain.isValid();
    }

    @After
    public void after(){
        System.out.println();
        System.out.println("测试结束：--------------------------");
        System.out.println();
    }
}
