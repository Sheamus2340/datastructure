package org.sheamus.algorithm.array.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * title：https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
 */
public class GroupAnagrams {

    /**
     * 排序完了之后进行组合
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> collect = Arrays.stream(strs).collect(Collectors.groupingBy(getStringFunction()));
        Collection<List<String>> values = collect.values();
        return new ArrayList<>(values);
    }

    private static Function<String, String> getStringFunction() {
        return str -> {
            // 返回 str 排序后的结果。
            // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
            char[] array = str.toCharArray();
            Arrays.sort(array);
            return new String(array);
        };
    }

}
