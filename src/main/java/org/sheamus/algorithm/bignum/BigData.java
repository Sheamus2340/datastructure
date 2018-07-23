package org.sheamus.algorithm.bignum;

/**
 * 一个大数和一个小数相乘
 * 732 * 18
 * Created by Sheamus on 2018/7/20.
 */
public class BigData {

    public static void main(String[] args) {
        int[] bigData = new int[7];
        bigData[bigData.length - 1] = 2;
        bigData[bigData.length - 2] = 3;
        bigData[bigData.length - 3] = 7;

        int num = 118;

        // 相乘
        for (int i = 0; i < bigData.length; i++) {
            bigData[i] *=  num;
        }

        // 进位和留位
        for (int i = bigData.length - 1; i > 0; i--) {
            bigData[i - 1] += bigData[i] / 10;
            bigData[i] = bigData[i] % 10;
        }

        for (int i = 0; i < bigData.length; i++) {
            System.out.print(bigData[i]);
        }

    }

}
