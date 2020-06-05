package com.trace.all.service.impl;

import com.trace.all.dao.StoreDao;
import com.trace.all.pojo.Store;
import com.trace.all.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl  implements StoreService {
   @Autowired
   private StoreDao storeDao;

    @Override
    public List<Store> findAll() {
        return storeDao.findAll();
    }

    @Override
    public Store findById(int id) {
        return storeDao.findById(id).get();
    }

    @Override
    public void insertStore(Store store) throws Exception {
        storeDao.save(store);
    }
}
