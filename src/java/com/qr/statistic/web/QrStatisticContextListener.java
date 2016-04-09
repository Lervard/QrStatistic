/**
 * @(#)QrStatisticContextListener, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.web;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Created by chenjun on 2016/4/9.
 */
public class QrStatisticContextListener implements ServletContextListener{
    
    private static Logger LOG = Logger.getLogger(QrStatisticContextListener.class);
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String realPath = servletContextEvent.getServletContext().getRealPath("WEB-INF");
        File confFile = new File(realPath, "conf");
        System.out.println("config path: " + confFile.getAbsolutePath());
        System.err.println("config path: " + confFile.getAbsolutePath());


        //1. load log4j
        File log4jFile = new File(confFile, "log4j2.xml");
        System.setProperty("log4jConfiguration", log4jFile.getAbsolutePath());

        LOG.info("config path: " + confFile.getAbsolutePath());
    }
}
