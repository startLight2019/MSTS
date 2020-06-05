package com.trace.all.service;

import com.trace.all.pojo.Producer;

import java.util.List;

public interface ProducerService{
    public List<Producer> findAll();

    public Producer findById(int id);

    public void insertPoducer(Producer producer) throws Exception;
}
