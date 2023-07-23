package jason.dynamicPrgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/15 18:51:05
 **/
@SuppressWarnings({"all"})
public class DSingleString {


    /**
     * 最长的斐波那契子序列的长度
     * <p>
     * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
     * n >= 3
     * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
     * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
     * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5ruvye/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution_01 {
        /**
         * 算法思路：先确定最后两个数，以此向前求解
         * <p>
         * k < j < i    dp[j][i]  表示以 j,i 结尾的斐波那契数列的元素的个数
         *
         * @param arr
         * @return
         */
        public int lenLongestFibSubseq(int[] arr) {
            int length = arr.length;
            if (length < 4) return arr[0] + arr[1] == arr[2] ? 3 : 0;
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < length; i++) {
                hashMap.put(arr[i], i);
            }
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++) {
                Arrays.fill(dp[i], 2);
            }
            int answer = 0;
            for (int i = 0; i < length; i++) {
                // 有数组严格递增，当 arr[j] * 2 > arr[i] 时，才有可能出现斐波那契数列
                for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                    if (hashMap.containsKey(arr[i] - arr[j])) {
                        int k = hashMap.get(arr[i] - arr[j]);
                        // arr[k] + arr[j] == arr[i] 则以 j,i结尾的个数 在以 k,j结尾的基础上 +1
                        dp[j][i] = dp[k][j] + 1;
                    }
                    // 更新最大个数
                    answer = Math.max(answer, dp[j][i]);
                }
            }
            return answer >= 3 ? answer : 0;

        }
    }


    /**
     * 最长等差数组
     * <p>
     * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
     * <p>
     * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-arithmetic-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution {

        public int longestArithSeqLength(int[] nums) {
            // diff 为公差
            int max = Arrays.stream(nums).max().getAsInt();
            int min = Arrays.stream(nums).min().getAsInt();
            int diff = max - min;
            int answer = 1;

            // d in [-diff, diff]
            for (int i = -diff; i <= diff; i++) {
                // 存储以某个数以某个数开头，公差为 i 的 元素个数
                int[] temp = new int[max + 1];
                Arrays.fill(temp, -1);
                for (int num : nums) {
                    int pre = num - i;
                    // 保证pre合理
                    if (pre >= min && pre <= max && temp[pre] != -1) {
                        temp[num] = Math.max(temp[num], temp[pre] + 1);
                        answer = Math.max(answer, temp[num]);
                    }
                    temp[num] = Math.max(temp[num], 1);
                }
            }
            return answer;
        }

    }

}
