package org.sheamus.algorithm.backtrace;

import java.util.*;

/**
 * 全排列
 * 1. 广度优先遍历；
 * 2. 深度优先遍历；
 */
public class Permute2 {

    /**
     * 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 存放每一层的元素
        Deque<List<Integer>> deque = new ArrayDeque<>();
        // 存放结果
        List<List<Integer>> res = new ArrayList<>();
        // 标识是否使用了索引位置的元素
        Deque<List<boolean[]>> used = new ArrayDeque<>();

        bfs(nums, len, deque, used, res);

        return res;
    }

    private void bfs(int[] nums, int len, Deque<List<Integer>> deque, Deque<List<boolean[]>> used, List<List<Integer>> res) {

        // 初始化
        for (int i = 0; i < len; i++) {
            deque.offer(new ArrayList<>(nums[i]));
//            List<Integer> list = new ArrayList<>();
//            list.add(nums[i]);
            boolean[] use = new boolean[len];
            use[i] = true;
            List<boolean[]> objects = new ArrayList<>();
            objects.add(use);
            used.offer(objects);
//            res.add(list);
        }
        int level = len;

        while (level-- > 0) {
            int size = deque.size();
            // 层级遍历队列
            for (int i = 0; i < size; i++) {
                List<Integer> poll = deque.poll();
                List<boolean[]> poll1 = used.poll();
                boolean[] useObj = poll1.get(i);
                // 填充下一个元素
                for (int j = 0; j < len; j++) {
                    if (!useObj[j]) {
                        List<Integer> list = new ArrayList<>();
                        list.addAll(poll);
                        list.add(nums[j]);
                        deque.offer(list);
                        List<boolean[]> listObj = new ArrayList<>();
                        listObj.addAll(poll1);
                        useObj[j] = true;
                        listObj.add(useObj);
                    }
                }
            }
        }

        while (deque.size() > 0) {
            res.add(deque.poll());
        }
    }

    /**
     * 深度优先遍历
     * 1，2，3
     * <p>
     * []
     * [1],[2],[3]
     * [1,2],[1,3],[2,1],[2,3],[3,1][3,2]
     * [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        // int depth : 遍历的深度
        // Stack path : 遍历的路径，考虑到时一个先进后出的结构，因此使用栈的结构实现；
        // boolean[] used : 标识索引对应的元素是否使用了
        // int len : 数组的长度
        // int[] nums : 数组
        // List<List<Integer>> res: 结果
        int len = nums.length;
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param nums
     * @param len
     * @param depth
     * @param path
     * @param used
     * @param res
     */
    public void dfs(int[] nums, int len, int depth,
                    Stack<Integer> path,
                    boolean[] used,
                    List<List<Integer>> res) {
        if (depth == len) {
            // 构造结果
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            // 选择一个没有使用的元素
            if (!used[i]) {
                // path 元素的添加；used 的状态的改变；
                path.push(nums[i]);
                used[i] = true;
                // 递归的遍历下一个元素
                dfs(nums, len, depth + 1, path, used, res);
                // 完成之后进行回溯
                path.pop();
                used[i] = false;
            }
        }
    }

    private void print(List<List<Integer>> res) {
        for (List<Integer> r : res) {
            System.out.println(r.toString());
        }
        System.out.println("*****************");
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute2 permute2 = new Permute2();
        // List<List<Integer>> res1 = permute2.permute(nums);
        List<List<Integer>> res = permute2.permute2(nums);
        // permute2.print(res1);
        permute2.print(res);
    }

}
