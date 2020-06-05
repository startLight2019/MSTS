package com.trace.all.chain;

/**
 * 1、交易输入指向前一个交易输出
 * 2、钱包余额是所有未使用的交易输出的总和
 * 这个类将用于引用尚未使用的transactionoutput。
 * transactionOutputId将用于查找相关的TransactionOutput
 * */
public class TransactionInput {
//    上一个输出的节点hash
//Reference to TransactionOutputs -> transactionId
	public String transactionOutputId;


//	未被交易的输出节点，相当于现有数据
	public TransactionOutput UTXO;

	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
}
