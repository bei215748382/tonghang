package com.tonghang.server.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {

    /**
     * 对byte数组编码
     *
     * @param srcByte
     * @return
     */
    public static String encode(byte[] srcByte) {
        if (null == srcByte) {
            return null;
        }

        if (srcByte.length == 0) {
            return "";
        }

        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(srcByte);
    }

    /**
     * 对String进行解码
     *
     * @param srcString
     * @return
     * @throws IOException
     */
    public static byte[] decoder(String srcString) throws IOException {
        if (StringUtils.isEmpty(srcString)) {
            return null;
        }

        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        return decoder.decodeBuffer(srcString);
    }
}
