package com.trace.all.service.impl;

import com.trace.all.dao.ProcesserDao;
import com.trace.all.pojo.Processer;
import com.trace.all.service.ProcesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesserServiceImpl implements ProcesserService {
    @Autowired
    private ProcesserDao processerDao;

    @Override
    public List<Processer> findAll() {
        return processerDao.findAll();
    }

    @Override
    public Processer findById(int id) {
        return processerDao.findById(id).get();
    }

    @Override
    public void insertProcesser(Processer processer) throws Exception{
        processerDao.save(processer);
    }
}
