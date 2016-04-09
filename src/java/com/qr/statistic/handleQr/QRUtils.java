/**
 * @(#)QRUtils, 2016/4/9.
 * <p/>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.qr.statistic.handleQr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang.math.JVMRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * Created by chenjun on 2016/4/9.
 */
public class QRUtils {
    private static final Logger LOG = LogManager.getLogger(QRUtils.class);

    /**
     * generate the picture, and return the file path in absolute path
     * @param width
     * @param height
     * @param imageFormat
     * @param _text
     * @param path
     * @return
     */
    public static String createQrcodeAndReturnPath(int width, int height, String imageFormat, String _text,
            String path){
        String qrcodeFilePath = "";
        try {
            
            HashMap<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(_text, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            JVMRandom random = new JVMRandom();
            File QrcodeFile = new File(path);
            ImageIO.write(image, imageFormat, QrcodeFile);
            MatrixToImageWriter.writeToPath(bitMatrix, imageFormat,
                QrcodeFile.toPath());
            qrcodeFilePath = QrcodeFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return qrcodeFilePath;
    }

    /**
     * create qrcode return bufferImage
     * @param width
     * @param height
     * @param imageFormat
     * @param text
     * @return
     */
    public static BufferedImage createQrCode(int width, int height, String imageFormat, String text,
        OutputStream stream) {
        BufferedImage image = null;
        try {
            HashMap<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, stream);
            LOG.debug("init write stream success!!!");
        } catch (WriterException e) {
            e.printStackTrace();
            LOG.warn("create the bufferImage failed!!!");
        } catch (IOException e) {
            LOG.warn("write the image failed!!");
        }
        return image;
    }

    /**
     * decode the qr picture, when decode failed, return null or "";
     * @param filePath
     * @return
     */
    public static String decodeQr(String filePath) {
        String retStr = "";
        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
            return "";
        }
        
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            return decodeQr(ImageIO.read(fileInputStream));
        } catch (FileNotFoundException e) {
            LOG.warn("can not found file Path path=" + filePath);
        } catch (IOException e) {
            LOG.warn("can not read file", e);
        }
        return null;
    }
    
    
    public static String decodeQr(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            return null;
        }
        
        String retStr = null;
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap bitmap = new BinaryBitmap(binarizer);
            HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<DecodeHintType, Object>();
            hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(bitmap,
                hintTypeObjectHashMap);
            retStr = result.getText();
        } catch (NotFoundException e) {
            LOG.warn("failed decode the picture," + e);
        }
        return retStr;
    }
    
    
    
    
}
