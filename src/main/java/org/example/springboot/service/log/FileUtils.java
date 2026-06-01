package org.example.springboot.service.log;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件操作工具类
 * 提供文件的判断、重命名、拷贝、读写等基础操作
 */
public class FileUtils {

    /** 判断文件或目录是否存在 */
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /** 判断路径是否为目录 */
    public static boolean isDir(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.isDirectory();
        } else {
            return false;
        }
    }

    /**
     * 文件或目录重命名
     *
     * @param oldFilePath 旧文件路径
     * @param newName     新的文件名（可以是单个文件名或绝对路径）
     * @return 是否重命名成功
     */
    public static boolean renameTo(String oldFilePath, String newName) {
        try {
            File oldFile = new File(oldFilePath);
            if (oldFile.exists()) {
                // 判断是单文件名还是全路径
                if (newName.indexOf("/") < 0 && newName.indexOf("\\") < 0) {
                    String absolutePath = oldFile.getAbsolutePath();
                    if (newName.indexOf("/") > 0) {
                        // Linux 系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1) + newName;
                    } else {
                        // Windows 系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1) + newName;
                    }
                }
                File file = new File(newName);
                if (file.exists()) {
                    System.out.println("该文件已存在,不能重命名");
                } else {
                    return oldFile.renameTo(file);
                }
            } else {
                System.out.println("原该文件不存在,不能重命名");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 文件拷贝（基于 NIO FileChannel 实现）
     *
     * @param sourceFile 源文件路径
     * @param targetFile 目标文件路径
     */
    public static void copy(String sourceFile, String targetFile) {
        File source = new File(sourceFile);
        File target = new File(targetFile);
        target.getParentFile().mkdirs();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            in = fis.getChannel();
            out = fos.getChannel();
            // 使用 transferTo 实现零拷贝，效率高于传统流复制
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (fos != null) fos.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 读取文本文件内容 */
    public static String readText(String filePath) {
        String lines = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 写入文本文件
     *
     * @param filePath  文件路径
     * @param content   写入内容
     * @param isAppend  是否追加模式
     */
    public static void writeText(String filePath, String content, boolean isAppend) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath, isAppend);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
                if (outputStream != null) outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
