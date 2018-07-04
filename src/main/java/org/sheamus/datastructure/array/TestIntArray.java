package org.sheamus.datastructure.array;

/**
 * int型的数组
 * Created by Sheamus on 2018/7/3.
 */
public class TestIntArray {
    public static void main(String[] args) {
        // 给数组分配空间，Java中的数组是引用类型
        int[] myArray = new int[10];
        // 插入数据
        for(int i = 0; i < myArray.length; i++) {
            myArray[i] = i;
        }
        display(myArray);
        // 查找数据
        int i;
        int searchNum = 5;
        for(i = 0; i < myArray.length; i++) {
            if(myArray[i] == searchNum) {
                break;
            }
        }

        if(i == myArray.length) {
            System.out.println("没有查询到数据");
        } else {
            System.out.println("数据的下标是: " + i);
        }

        // 删除数据,并且将数组迁移，删除数字 4
        int deleteNum = 4;
        int[] index = new int[myArray.length];
        int k = 0;
        for (int j = 0; j < myArray.length; j++) {
            if(myArray[j] == deleteNum) {
                index[k++] = j+1;
            }
        }

        if(index[0] == 0) {//没有删除的元素
            System.out.println("没有要删除的元素");
        } else {
            for(int h = 0; h < index.length; h++) {
                if(index[h] == 0) {
                    break;
                }

                for (int q = index[h] - 1; q < myArray.length - 1; q++) {
                    myArray[q] = myArray[q + 1];
                }
                myArray[myArray.length - 1] = 0;
            }
        }
        display(myArray);

    }

    /**
     * 展示数组
     * @param myArray
     */
    private static void display(int[] myArray) {
        for(int arr : myArray) {
            System.out.print(arr + " ");
        }
        System.out.println("------------------------");
    }
}
