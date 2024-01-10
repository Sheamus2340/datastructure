package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/count-and-say/
 */
public class CountAndSay {

    /**
     *
     作者：力扣官方题解
     链接：https://leetcode.cn/problems/count-and-say/solutions/1047325/wai-guan-shu-lie-by-leetcode-solution-9rt8/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public String countAndSay(int n) {

        String str = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;

            while (pos < str.length()) {
                while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
                    pos++;
                }
                sb.append(Integer.toString(pos - start)).append(str.charAt(start));
                start = pos;
            }
            str = sb.toString();
        }

        return str;
    }

}
