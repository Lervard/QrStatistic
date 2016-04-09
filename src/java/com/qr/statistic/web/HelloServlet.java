/**
 * @(#)HelloServlet, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.web;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjun on 2016/4/9.
 */
public class HelloServlet extends HttpServlet{
    private static Logger LOG = LogManager.getLogger(HelloServlet.class);

    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        LOG.info("headers=" + request.getHeader("User-agent"));
        LOG.info("Hello I,m hello");

        out.println("<html>");
        out.println("<head>");
        out.println("<body>");
        out.println("Hello World!!");
        out.println("</body>");
        out.println("</head>");
        out.println("</html>");
        out.close();
    }
}
