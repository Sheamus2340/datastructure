package org.sheamus.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * title：https://leetcode.cn/problems/group-anagrams/
 */
public class GroupAnagrams {

    /**
     * 作者：Sweetiee 🍬
     * 链接：https://leetcode.cn/problems/group-anagrams/solutions/520515/kan-wo-yi-ju-hua-ac-zi-mu-yi-wei-ci-fen-yrnis/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    // 返回 str 排序后的结果。
                    // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
                    char[] array = str.toCharArray();
                    Arrays.sort(array);
                    return new String(array);
                })).values());
    }

}
