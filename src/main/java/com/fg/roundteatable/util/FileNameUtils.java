package com.fg.roundteatable.util;

public class FileNameUtils {

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
//        return fileName.substring(fileName.lastIndexOf("."));//使用这个会出现数组越界
        return fileName.substring(fileName.indexOf("."));
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtils.getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }

}
