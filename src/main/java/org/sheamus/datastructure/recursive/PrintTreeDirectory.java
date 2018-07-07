package org.sheamus.datastructure.recursive;

import java.io.File;

/**
 * 把给定的目录输出它的树形结构
 * Created by Sheamus on 2018/7/7.
 */
public class PrintTreeDirectory {
    public static void main(String[] args) {
        final String path = "D:\\ProgramFiles\\Java";
        int level = 0;
        File file = new File(path);
        printTree(file,level);
    }

    /**
     * 通过level来打印出树状结构
     * @param file
     * @param level
     */
    private static void printTree(File file, int level) {
        if(!file.exists()) {
            System.out.println("目录不存在");
            return;
        }
        // 判断是否是第一级目录
        if (level == 0) {
            System.out.println(file.getName());// 一级目录只打名称
        } else {
            // 循环打空格
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(" ├ " + file.getName()); // 二级目录打标识符├
        }
        // 判断是不是目录
        if (file.isDirectory()) {
            // 列出所有文件及文件夹
            File[] fs = file.listFiles();
            // 判断fs是否为空
            if (null != fs) {
                // 循环递归
                for (File s : fs) {
                    printTree(s, level + 1);
                }
            }
        }

    }
}
