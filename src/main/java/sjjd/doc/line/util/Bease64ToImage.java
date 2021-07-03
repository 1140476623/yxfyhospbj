package sjjd.doc.line.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

public class Bease64ToImage {


    /**
     *      * 二进制流转Base64字符串
     *      *
     *      * @param data 二进制流
     *      * @return data
     *      * @throws IOException 异常
     *      
     */
    public static String getImageString(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }


    public static byte[] base64topng(String imageBase64) {
        byte[] b1 = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            if (imageBase64.indexOf("data:image/jpeg;base64,") != -1) {
                b1 = decoder.decodeBuffer(imageBase64.replaceAll("data:image/jpeg;base64,", ""));
            } else {
                if (imageBase64.indexOf("data:image/png;base64,") != -1) {
                    b1 = decoder.decodeBuffer(imageBase64.replaceAll("data:image/png;base64,", ""));
                } else {
                    b1 = decoder.decodeBuffer(imageBase64.replaceAll("data:image/jpg;base64,", ""));
                }
            }
            for (int i = 0; i < b1.length; ++i) {
                if (b1[i] < 0) {// 调整异常数据
                    b1[i] += 256;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b1;
    }

    public static String filePath(String path, byte[] b) {
        String imgPath = "";
        try {
            String FilePath = path;//新生成的图片
            File f1 = new File(FilePath);
            if (!f1.exists()) {
                f1.mkdir();
            }
            imgPath = FilePath + "/" + UUID.randomUUID().toString() + ".jpg";
            File f2 = new File(imgPath);
            if (!f2.exists()) {
                f2.createNewFile();
            }
            OutputStream out = new FileOutputStream(imgPath);
            out.write(b);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgPath;
    }


    public static String filePath(String path, byte[] b, String fileName) {
        String imgPath = "";
        String paths = fileName.substring(0, fileName.lastIndexOf(".") + 1);
        try {
            String FilePath = path;//新生成的图片
            File f1 = new File(FilePath);
            if (!f1.exists()) {
                f1.mkdir();
            }
            imgPath = FilePath + "/" + paths + "jpg";
            File f2 = new File(imgPath);
            if (!f2.exists()) {
                f2.createNewFile();
            }
            OutputStream out = new FileOutputStream(imgPath);
            out.write(b);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgPath;
    }


    public static String barcode2Base64(String msg) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(msg, BarcodeFormat.QR_CODE, 600, 600);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        Base64.Encoder encoder = Base64.getEncoder();
        String text = "data:image/png;base64," + encoder.encodeToString(outputStream.toByteArray());
        return text;
    }

}
