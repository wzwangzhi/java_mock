package com.wangzhi.test.mock.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileManager {
    Logger logger = LoggerFactory.getLogger(FileManager.class);
    public static final String PATH_RESPONSE = "response/";

    /**
     * 写入返回值
     *
     * @param path
     * @param value
     * @return 是否写入成功
     */
    public boolean writeResponse(String path, String value) {
        return writeToFile(PATH_RESPONSE + path, value);
    }

    /**
     * 读取返回值
     *
     * @param path
     * @return
     */
    public String readResponse(String path) {
        return readFromFile(PATH_RESPONSE + path);
    }

    /**
     * @return 是否写入成功
     */
    private boolean writeToFile(String path, String value) {
        logger.debug("正在写入文件，path:" + path);
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(value);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.debug("写入文件失败");
            e.printStackTrace();
            return false;
        }
        logger.debug("写入文件成功");
        return true;
    }

    /**
     * @param path
     * @return 文件内容，当文件不存在时返回null
     */
    private String readFromFile(String path) {
        logger.debug("正在读取文件，path:" + path);
        StringBuffer valueBuffer = new StringBuffer();
        try {
            File file = new File(path);
            if (!file.exists()) {
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                valueBuffer.append(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("读取失败");
            return null;
        }
        logger.debug("读取成功");
        return valueBuffer.toString();
    }

    public static class FileManagerHolder {
        static FileManager INSTANCE = new FileManager();
    }

    public static FileManager getInstance() {
        return FileManagerHolder.INSTANCE;
    }
}
