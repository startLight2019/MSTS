package com.trace.all.service;

import com.trace.all.pojo.Transporter;

import java.util.List;

public interface TransporterService {
    public List<Transporter> findAll();

    public Transporter findById(int id);

    public void insertTransporter(Transporter transporter) throws Exception;
}
