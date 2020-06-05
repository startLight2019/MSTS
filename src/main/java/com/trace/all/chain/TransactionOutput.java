package com.trace.all.chain;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * 显示从交易中发送给每一方的最终金额。这些作为新交易中的输入参考，作为证明你可以发送的金额数量。*/
public class TransactionOutput {
//    输出hash
    public String id;

//  该输出产品接收者的公匙,即当前拥有者
    public PublicKey reciepient;

//  拥有的资源
    public ArrayList<String> value;

//产生该输出的交易的id
    public String parentTransactionId;

    //构造器
    public TransactionOutput(PublicKey reciepient, ArrayList<String> data, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = data;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient) + data + parentTransactionId);
    }

//    验证货物是否属于该节点
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == reciepient);
    }

}
