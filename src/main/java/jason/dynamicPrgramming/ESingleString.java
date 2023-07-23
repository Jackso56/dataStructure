package jason.dynamicPrgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/17 16:54:43
 **/
@SuppressWarnings({"all"})
public class ESingleString {

    /**
     * 形成字符串的最短路径
     * <p>
     * 对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的 子序列 。(例如，“ace” 是 “abcde” 的子序列，而 “aec” 不是)。
     * 给定源字符串 source 和目标字符串 target，返回 源字符串 source 中能通过串联形成目标字符串 target 的 子序列 的最小数量 。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 -1。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/9edhii/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public int shortestWay(String source, String target) {
            return 0;
        }
    }


    /**
     * 最大整除子集
     * <p>
     * 算法思路：双循环获取 maxSize, 逆序获取 answer
     * <p>
     * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
     * answer[i] % answer[j] == 0 ，或
     * answer[j] % answer[i] == 0
     * 如果存在多个有效解子集，返回其中任何一个均可。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/largest-divisible-subset
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class Solution_02 {

        public static void main(String[] args) {
            largestDivisibleSubset(new int[]{1, 2, 3});
        }

        public static List<Integer> largestDivisibleSubset(int[] nums) {
            int length = nums.length;
            ArrayList<Integer> answer = new ArrayList<>();
            if (length < 2) {
                answer.add(nums[0]);
                return answer;
            }

            Arrays.sort(nums);  // 先排序
            int[] dp = new int[length];
            Arrays.fill(dp, 1);
            int maxSize = 1, maxValue = nums[0];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                if (dp[i] > maxSize) {
                    maxValue = nums[i];
                    maxSize = dp[i];
                }
            }

            for (int i = length - 1; i >= 0; i--) {
                if (dp[i] == maxSize && maxValue % nums[i] == 0) {
                    answer.add(nums[i]);
                    maxValue = nums[i];
                    maxSize--;
                }
            }
            return answer;
        }
    }


}
