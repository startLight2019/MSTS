package com.trace.all.controller.global;

import com.trace.all.chain.Block;

import java.util.HashMap;

public class OrderBlock {
    public static Block block;

    public static String callBackString;

    public static HashMap<NodeEnum,Boolean> hasNodePut = new HashMap<>();

    static {
        hasNodePut.put(NodeEnum.PRODUCERENUM,false);
        hasNodePut.put(NodeEnum.PROCESSERENUM,false);
        hasNodePut.put(NodeEnum.STOREENUM,false);
        hasNodePut.put(NodeEnum.TRANSPORTERENUM,false);
    }

}
