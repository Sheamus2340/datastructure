package org.sheamus.datastructure.recursive.age;

/**
 * Created by Sheamus on 2018/7/10.
 */
public class AgeProblem {

    public static int age(int n) {
        if(n == 1) {
            return 10;
        } else {
            return age(n - 1) + 2;
        }
    }

    static int getAge(int num,int acc) {
        if (num == 1)
            return acc;
        return getAge(num - 1,acc + 2);
    }

    public static void main(String[] args) {
        System.out.println(age(5));

        System.out.println(getAge(5,10));
    }
}
