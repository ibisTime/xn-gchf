package com.cdkj.loan.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdkj.gchf.dao.ISYSConfigDAO;
import com.cdkj.gchf.domain.SYSConfig;

import java.util.List;

/**
 * Created by tianlei on 2017/十二月/05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class SYSConfigDAOImplTest {

    @Autowired
    private ISYSConfigDAO sysConfigDAO;

    @Test
    public void select() throws Exception {

        SYSConfig sysConfig = new SYSConfig();
        sysConfig.setCkey("eth_coin_price_x");
        List list = sysConfigDAO.selectList(sysConfig);
        int a = 10;

    }

}