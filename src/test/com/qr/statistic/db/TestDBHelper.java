/**
 * @(#)TestDBHelper, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chenjun on 2016/4/9.
 */
public class TestDBHelper {
    
    
    private static String SQL_INSERT = "insert into person(name, sex) values(?, ?);";
    private static String SQL_SELECT = "select name, sex from person;";
    
    public static void main(String[] args) {
        DBHelper db = DBHelper.getInstance();

        PreparedStatement pst = null;
        try {
            pst = db.getConnection().prepareStatement(
                SQL_INSERT);
            pst.setString(1, "chenjun");
            pst.setInt(2, 0);
            pst.execute();
            
            pst = db.getConnection().prepareStatement(SQL_SELECT);
            ResultSet set =  pst.executeQuery();
            
            while (set.next()) {
                System.out.println(set.getString(1) + "," + set.getString(2));
            }
            
            pst.close();
            db.getConnection().close();
        } catch (SQLException e) {
            System.out.println("test sql failed!!!!");
            e.printStackTrace();
        } finally {
        }
        
        
        
    }
}
