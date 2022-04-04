package top.trumandu.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
public class SmartDigestUtils extends DigestUtils {
    /**
     * md5加盐加密
     *
     * @param salt
     * @param password
     * @return
     */
    public static String encryptPassword(String salt, String password) {
        return DigestUtils.md5DigestAsHex(String.format(salt,password).getBytes(StandardCharsets.UTF_8));
    }
}
