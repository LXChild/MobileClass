package com.chong.utils;

import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 密码加解密工具
 * Created by LXChild on 28/04/2017.
 */
public final class SecurityUtils {

    private static final String UTF_8 = "utf-8";

    private SecurityUtils() {}

//    public static String encodeByMD5(String origin) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//
//        // 确定计算方法
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//
//        return base64Encoder.encode(md5.digest(origin.getBytes("utf-8")));
//    }

    public static String encodeByBase64(String origin) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(origin.getBytes(UTF_8));
    }

    public static String decodeByBase64(String origin) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(origin), UTF_8);
    }

    public static String getDigestByMD5(String origin) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] btInput = origin.getBytes(UTF_8);
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(btInput);
        byte[] md = mdInst.digest();
        StringBuffer sb = new StringBuffer();
        for (byte aMd : md) {
            int val = ((int) aMd) & 0xff;
            if (val < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }

    public static String getFileDigestByMD5(File file) throws FileNotFoundException, Exception, IOException {
        String value;
        try (FileInputStream in = new FileInputStream(file)) {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        }
        return value;
    }
}
