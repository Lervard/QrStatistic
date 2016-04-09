/**
 * @(#)QrRedirectServlet, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenjun on 2016/4/9.
 */
public class QrRedirectServlet extends HttpServlet{
    public static Logger LOG = LogManager.getLogger(QrRedirectServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String redirect_url = req.getParameter(WebConstant.REDIRECT_URL);
        LOG.info("redirect url=" + redirect_url);
        resp.sendRedirect(redirect_url);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        doGet(req, resp);
    }
}
