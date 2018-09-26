package com.clawhub.auth.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * <Description> 密码生成器<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/11 <br>
 */
public class PasswordGenarator {
    /**
     * Gets password.
     *
     * @param source        the source
     * @param salt          the salt
     * @param hashIteration the hash iteration
     * @return the password
     */
    public static String getPassword(String source, String salt, int hashIteration) {
        SimpleHash obj = new SimpleHash("MD5", source, salt, hashIteration);
        return obj.toString();
    }
}
