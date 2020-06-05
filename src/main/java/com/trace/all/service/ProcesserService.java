package com.trace.all.service;

import com.trace.all.pojo.Processer;

import java.util.List;

public interface ProcesserService {
    public List<Processer> findAll();

    public Processer findById(int id);

    public void insertProcesser(Processer processer) throws Exception;
}
