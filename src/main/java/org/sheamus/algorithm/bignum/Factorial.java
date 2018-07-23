package org.sheamus.algorithm.bignum;

/**
 * 阶乘 ，大数阶乘,这里可以看成是一个大数和一个小数相乘
 * Created by Sheamus on 2018/7/20.
 */
public class Factorial {
    public static void main(String[] args) {
        int[] sum = new int[100];
        sum[sum.length - 1] = 1;
        int num = 50;
        for(int i = 1; i < num; i++) {
            sum = bigNum(sum,i);
        }
        int index = index(sum);

        for (int i = index; i < sum.length - 1; i++) {
            System.out.print(sum[i]);
        }

    }

    /**
     * 找到前面不是0的位置
     * @param sum
     * @return
     */
    public static int index(int[] sum) {
        for (int i = 0; i < sum.length; i++) {
            if(sum[i] == 0) {
                continue;
            } else {
                return i;
            }
        }
        return sum.length - 2;// 全为0，那么就保留一个0
    }

    public static int[] bigNum(int[] data,int num) {
        // 相乘
        for (int i = 0; i < data.length; i++) {
            data[i] *= num;
        }

        // 进位和留位
        for(int i = data.length - 1; i > 0; i--) {
            data[i - 1] += data[i] / 10;
            data[i] = data[i] % 10;
        }
        return data;
    }



}
