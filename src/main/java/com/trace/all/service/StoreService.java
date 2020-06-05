package com.trace.all.service;

import com.trace.all.pojo.Store;

import java.util.List;

public interface StoreService {
    public List<Store> findAll();

    public Store findById(int id);

    public void insertStore(Store store) throws Exception;
}
