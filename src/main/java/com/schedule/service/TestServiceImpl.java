package com.schedule.service;

import com.schedule.dao.TestDaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired TestDaoImp testDao;

    @Override
    public String test() {
        String testResult = "";
        try {
            testResult = testDao.now();
            return testResult;
        } catch (Exception e) {
            e.printStackTrace();
            return testResult;
        }
    }
}