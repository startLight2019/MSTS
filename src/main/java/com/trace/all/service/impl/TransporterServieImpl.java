package com.trace.all.service.impl;

import com.trace.all.dao.TransporterDao;
import com.trace.all.pojo.Transporter;
import com.trace.all.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporterServieImpl implements TransporterService {
    @Autowired
    private TransporterDao transporterDao;

    @Override
    public List<Transporter> findAll() {
        return transporterDao.findAll();
    }

    @Override
    public Transporter findById(int id) {
        return transporterDao.findById(id).get();
    }

    @Override
    public void insertTransporter(Transporter transporter) throws Exception {
        transporterDao.save(transporter);
    }
}
