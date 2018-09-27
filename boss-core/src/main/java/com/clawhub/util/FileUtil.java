package com.clawhub.util;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * <Description> 文件工具<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/27 <br>
 */
public class FileUtil {

    /**
     * Byte 2 image.
     *
     * @param data the data
     * @param path the path
     * @throws IOException the io exception
     */
    public static void byte2image(byte[] data, String path) throws IOException {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
    }
}
