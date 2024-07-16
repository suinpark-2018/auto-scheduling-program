package com.schedule.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImp implements TestDao {
    @Autowired SqlSession session;

    private static String namespace = "com.schedule.dao.TestDao.";

    @Override
    public String now() throws Exception {
        return session.selectOne(namespace + "now");
    }
}
