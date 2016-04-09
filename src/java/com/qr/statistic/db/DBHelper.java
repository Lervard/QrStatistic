/**
 * @(#)DBHelper, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by chenjun on 2016/4/9.
 */
public class DBHelper {
    private static final Logger LOG = LogManager.getLogger(DBHelper.class);
    
    private static final String url = "jdbc:mysql://104.131.156.64:3306/test";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "cj";
    private static final String password = "123456";
    
    private static Connection connection;
    
    private static volatile DBHelper instance = null;
    
    private DBHelper() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOG.info("the class is not exsit!!!, name=" + driver);
        }
    }
    
    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            LOG.warn("init db error!!");
            e.printStackTrace();
        }
        return connection;
    }
}
