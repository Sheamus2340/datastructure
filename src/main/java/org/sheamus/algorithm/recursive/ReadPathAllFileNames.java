package org.sheamus.algorithm.recursive;

import java.io.File;

/**
 * 读取给定路径的所有的目录名和文件名
 * Created by Sheamus on 2018/7/7.
 */
public class ReadPathAllFileNames {

    public static void main(String[] args) {
        String path = "D:\\ProgramFiles\\Java";
        ReadPathAllFileNames names = new ReadPathAllFileNames();
        names.readPathNames(path);
    }

    /**
     * 根据路径遍历出所有的文件名和目录名
     * @param path
     */
    private void readPathNames(String path) {
        File file = new File(path);
        if(!file.exists()) {
            System.out.println("目录不存在");
            return;
        }
        readNames(file);
    }

    private void readNames(File file) {
        File[] files = file.listFiles();
        for(File newFile : files) {
            if(newFile.isDirectory()) {
                System.out.println("目录名: " + newFile.getName());
                readNames(newFile);
            } else {
                System.out.println("\t" + newFile.getParentFile().getName() + "  的文件名：" + newFile.getName());
            }
        }
    }

}
