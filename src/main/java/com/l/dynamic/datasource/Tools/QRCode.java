package com.l.dynamic.datasource.Tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class QRCode {

    public static void main(String[] args) throws Exception {
        //需要编码的内容
        String urlCode = "my QR Code";
        QRCodeWriter qrCodeWriter1 = new QRCodeWriter();
        //设置二维码图片宽高
        BitMatrix bitMatrix1 = qrCodeWriter1.encode(urlCode, BarcodeFormat.QR_CODE, 600, 600);
        //输出到指定路径
        // Path path = FileSystems.getDefault().getPath("E:\\Download\\chrome下载\\MyQRCode.png");
        // MatrixToImageWriter.writeToPath(bitMatrix1, "PNG", path);
        // 写到输出流
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix1, "PNG", outputStream1);
        //转换为base64
        Base64.Encoder encoder1 = Base64.getEncoder();
        String advUrl = "data:image/jpeg;base64,"
                + encoder1.encodeToString(outputStream1.toByteArray());
        //打印base64结果
        System.out.println(advUrl);
    }


}
