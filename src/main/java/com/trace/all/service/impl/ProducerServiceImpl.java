package com.trace.all.service.impl;

import com.trace.all.dao.ProducerDao;
import com.trace.all.pojo.Producer;
import com.trace.all.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerDao producerDao;

    @Override
    public List<Producer> findAll() {
        List<Producer> producers = producerDao.findAll();

        return producerDao.findAll();
    }

    @Override
    public Producer findById(int id) {
        Optional<Producer> pro = producerDao.findById(id);
        return pro.get();
    }

    @Override
    public void insertPoducer(Producer producer) throws Exception{
        producerDao.save(producer);
    }
}
