package com.xsl.authority.utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Description:BASE64 加密工具类
 * Auth: Frank
 * Date: 2017-10-26
 * Time: 下午 3:29
 */
public class Base64Util {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}