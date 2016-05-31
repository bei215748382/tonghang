package com.tonghang.server.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;

/**
 * 详细：HTTP 请求签名
 */
public class RequestSignatureUtil {
    private final static Logger log = LoggerFactory
            .getLogger(RequestSignatureUtil.class);

    /**
     * 检查请求是否合法
     *
     * @param content
     * @param time
     * @param appkey
     * @param sign
     * @return
     * @throws ServiceException
     */
    public static void checkRequestLegal(String content, String time,
            String appkey, String sign) throws ServiceException {
        if (StringUtils.isEmpty(sign)) {
            throw new ServiceException(ErrorCode.code21.getCode(),
                    ErrorCode.code21.getHttpCode(), ErrorCode.code21.getDesc());
        }
        try {
            /*
             * int features = 0; features |= Feature.AutoCloseSource.getMask();
             * features |= Feature.InternFieldNames.getMask(); features |=
             * Feature.UseBigDecimal.getMask(); features |=
             * Feature.AllowUnQuotedFieldNames.getMask(); features |=
             * Feature.AllowSingleQuotes.getMask(); features |=
             * Feature.AllowArbitraryCommas.getMask(); features |=
             * Feature.IgnoreNotMatch.getMask();
             */
            // @SuppressWarnings("unchecked")
            // LinkedHashMap<String,Object> contentMap =
            // JSON.parseObject(content, new
            // TypeReference<LinkedHashMap<String, Object>>(){});
            @SuppressWarnings("unchecked")
            Map<String, Object> contentMap = JSON.parseObject(content,
                    Map.class);
            String signServer = RequestSignatureUtil
                    .createHashedQueryString(contentMap, time, appkey);
            if (log.isDebugEnabled()) {
                log.debug("sign[" + sign + "],signServer[" + signServer + "]");
            }
            if (!sign.equals(signServer)) {
                throw new ServiceException(ErrorCode.code21.getCode(),
                        ErrorCode.code21.getHttpCode(),
                        ErrorCode.code21.getDesc());
            }
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(ErrorCode.code21.getCode(),
                    ErrorCode.code21.getHttpCode(), ErrorCode.code21.getDesc());
        }
    }

    /**
     * 功能：将一个Map按照Key字母升序构成一个QueryString. 并且加入时间混淆的hash串。
     *
     * @param queryMap
     *            query内容
     * @param time
     *            加密时候，为当前时间；解密时，为从请求中的时间戳得到的时间；
     * @param salt
     *            加密salt
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createHashedQueryString(Map<String, Object> queryMap,
            String time, String appkey) throws UnsupportedEncodingException {
        String salt = Base64.encode(appkey.getBytes("UTF-8"));
        String qs = "";
        if (queryMap != null) {

            Map<String, Object> map = new TreeMap<String, Object>(queryMap);
            qs = createQueryString(map);
            if (qs == null) {
                qs = "";
            }
        }
        StringBuilder sb = new StringBuilder(qs);
        if (qs != null && !qs.equals("")) {
            sb.append("&");
        }
        sb.append("time").append("=").append(time != null ? time : "");
        sb.append("&").append("salt").append("=")
                .append(salt != null ? salt : "");

        if (log.isDebugEnabled())
            log.debug("true:---" + sb.toString());
        String hash = md5(sb.toString()); // 得到加密字段值
        // hash = hash.toUpperCase();
        // String htqs = String.format("%s&time=%d&appsecret=%s", qs, time,
        // hash);
        return hash;
    }

    /**
     * 功能：用一个Map生成一个QueryString，参数的顺序不可预知。
     *
     * @param queryString
     * @return
     */
    public static String createQueryString(Map<String, Object> queryMap) {

        if (queryMap == null) {
            return null;
        }

        try {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
                if (entry.getValue() == null) {
                    continue;
                }
                String key = entry.getKey().trim();
                String value = entry.getValue() + "";

                try {
                    value = URLEncoder.encode(value, "UTF-8");
                    if (log.isDebugEnabled())
                        log.debug(
                                "key[" + key + "],value--en--[" + value + "]");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(value);
            }
            return sb.toString();
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对字符串计算MD5摘要
     *
     * @param str
     *            待计算字符串 return 计算后摘要
     */
    public static String md5(String str) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        md5.update(str.getBytes());

        byte[] encodedValue = md5.digest();
        int j = encodedValue.length;
        char finalValue[] = new char[j * 2];
        int k = 0;

        for (int i = 0; i < j; i++) {
            byte encoded = encodedValue[i];
            finalValue[k++] = hexDigits[encoded >> 4 & 0xf];
            finalValue[k++] = hexDigits[encoded & 0xf];
        }

        return new String(finalValue);
    }

}
