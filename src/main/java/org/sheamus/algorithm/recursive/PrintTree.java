package org.sheamus.algorithm.recursive;

import java.io.File;

/**
 * Created by Sheamus on 2018/7/7.
 */
public class PrintTree {

    public static void main(String[] args) {
        String path = "D:\\ProgramFiles\\Java\\jdk1.7.0_79";
        File file = new File(path);
        int level = 0;

        printTree(file,level);

    }

    /**
     * 通过给定的file和层级来打印对应的结构
     * @param file
     * @param level
     */
    private static void printTree(File file, int level) {
        //当给定的file是不存在的时候，直接返回，输出不存在的提示
        if(!file.exists()) {
            System.out.println("该目录不存在");
            return;
        }
        // 当是第1层的时候，直接打印文件名字
        if(0 == level) {
            if(file.isDirectory()) {
                System.out.println("目录名称为：" + file.getName());
            } else {
                System.out.println("文件名称为：" + file.getName());
            }
        } else { // 当不是第一层的时候
            // 首先要输出层级的收缩符
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            // 然后输出要的树的形状和文件名
            if(file.isDirectory()) {
                System.out.println("目录名称为：|- " + file.getName());
            } else {
                System.out.println("文件名称为：|- " + file.getName());
            }
        }
        // 遍历该目录的文件或者文件夹
        File[] files = file.listFiles();
        if(null != files) {
            for (File newFile : files) {
                printTree(newFile, level + 1);
            }
        }
    }

}
