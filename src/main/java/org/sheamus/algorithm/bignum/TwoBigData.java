package org.sheamus.algorithm.bignum;

/**
 * Created by Sheamus on 2018/7/20.
 */
public class TwoBigData {

    public static void main(String[] args) {
        int[] num1 = new int[5];
        int[] num2 = new int[5];
        num1[num1.length - 1] = 1;
        num1[num1.length - 2] = 1;


        num2[num2.length - 1] = 1;
        num2[num2.length - 2] = 1;


        //calculate(num1,num2);

       int[] result = bigNumberMultiply2(num1,num2);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }

    }

    private static int[] calculate(int[] num1, int[] num2) {
        int[][] sum = new int[100][100];

        for(int i = num2.length - 1; i > 0; i--) {
            sum[i] = bigNum(num1,num2[i]);
        }

        // 越位相加
        for (int i = 0; i < sum.length; i++) {

        }

        return null;
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

    /**
     * 大数相乘方法二
     */
    public static int[] bigNumberMultiply2(int[] num1, int[] num2){
        // 分配一个空间，用来存储运算的结果，num1长的数 * num2长的数，结果不会超过num1+num2长
        int[] result = new int[num1.length + num2.length];

        // 先不考虑进位问题，根据竖式的乘法运算，num1的第i位与num2的第j位相乘，结果应该存放在结果的第i+j位上
        for (int i = 0; i < num1.length; i++){
            for (int j = 0; j < num2.length; j++){
                result[i + j + 1] += num1[i] * num2[j];  // (因为进位的问题，最终放置到第i+j+1位)
            }
        }

        //单独处理进位
        for(int k = result.length-1; k > 0; k--){
            if(result[k] > 10){
                result[k - 1] += result[k] / 10;
                result[k] %= 10;
            }
        }
        return result;
    }

}
