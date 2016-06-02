package com.tonghang.server.util;

import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;

public class CodecUtil {

    public static String encodeToken2Ciphertext(String token)
            throws ServiceException {
        String result = null;
        try {
            result = new DESUtil().encrypt(token);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code50.getCode(),
                    ErrorCode.code50.getHttpCode(), ErrorCode.code50.getDesc());
        }
        return result;

    }

    public static String decodeCiphertext2Token(String ciphertext)
            throws ServiceException {
        String result = null;
        try {
            result = new DESUtil().decrypt(ciphertext);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code50.getCode(),
                    ErrorCode.code50.getHttpCode(), ErrorCode.code50.getDesc());
        }
        return result;
    }
}
