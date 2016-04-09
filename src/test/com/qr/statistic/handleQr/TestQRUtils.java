/**
 * @(#)TestQRUtils, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.handleQr;

import org.apache.commons.lang.math.JVMRandom;

/**
 * Created by chenjun on 2016/4/9.
 */
public class TestQRUtils {

    public static void main(String[] args) {
        String text = "hello world!!!!";
        String path = "./logs/qrcode";
        String format = "png";
        JVMRandom random = new JVMRandom();
        path = path + random.nextInt() + "." + format;
        
        QRUtils.createQrcodeAndReturnPath(300, 300, format, text, path);
        
    }
}
