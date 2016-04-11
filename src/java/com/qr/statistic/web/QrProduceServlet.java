/**
 * @(#)QrProduceServlet, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.web;

import com.qr.statistic.handleQr.QRUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.List;

/**
 * Created by chenjun on 2016/4/9.
 */
public class QrProduceServlet extends HttpServlet{
    private static Logger LOG = LogManager.getLogger(QrProduceServlet.class);
    
    
    private String htmlText(String text) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<body>");
        builder.append(text);
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
        if (!ServletFileUpload.isMultipartContent(req)) {
            PrintWriter out = resp.getWriter();
            out.println(htmlText("the request is not ok, please upload a file!!"));
            out.close();
            return;
        }
        
        /*get the file stream, and get the text of the stream*/
        try {
            List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);

            String text = "";
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    String name = new File(item.getName()).getName();
                    LOG.info(
                        "the fileName, pathName=" + item.getName() + ", name="
                            + name);
                    BufferedImage image = ImageIO.read(item.getInputStream());
                    text = QRUtils.decodeQr(image);
                    break;
                }
            }
            
            if (StringUtils.isBlank(text)) {
                PrintWriter out = resp.getWriter();
                out.println(htmlText("can not decode the picture!!!"));
                out.close();
                return;
            }
            
            resp.setContentType("image/png");
            OutputStream out = resp.getOutputStream();
            QRUtils.createQrCode(300, 300, "png", text, out);
            out.close();
            
        } catch (FileUploadException e) {
            
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String url = req.getParameter("url");
        /*Is a valid url*/
        
        if (StringUtils.isBlank(url)) {
            PrintWriter out = resp.getWriter();
            out.println(htmlText("the url is blank"));
            out.close();
            return;
        }
        
        String text = makeARedirectURL(req, url);
        resp.setContentType("image/png");
        OutputStream out = resp.getOutputStream();
        QRUtils.createQrCode(300, 300, "png", text, out);
        out.close();
    }
    
    private String makeARedirectURL(HttpServletRequest request, String url) {
        LOG.info("host=" + request.getRemoteHost());
        LOG.info("port=" + request.getRemotePort());
        LOG.info("url=" + request.getRequestURI());
        
        String expact = "http://192.168.1.100:8080/HelloServlet";
        
        StringBuilder builder = new StringBuilder();
        builder.append(expact);
        builder.append("/redirect");
        builder.append("?");
        builder.append(WebConstant.REDIRECT_URL);
        builder.append("=");
        builder.append(url);
        return builder.toString();
    }
}
