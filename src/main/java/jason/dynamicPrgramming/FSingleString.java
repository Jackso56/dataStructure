package jason.dynamicPrgramming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/17 17:44:39
 **/
@SuppressWarnings({"all"})
public class FSingleString {


    /**
     * 最长有效括号
     * <p>
     * 算法思路：索引入栈
     *
     * <p>
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     */
    static class Solution_01 {
        public static void main(String[] args) {
            longestValidParentheses(new String("()(()"));
        }

        static public int longestValidParentheses(String s) {
            Deque<Integer> list = new ArrayDeque<>();
            list.push(-1);
            int answer = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    list.push(i);
                } else {
                    // 避免空指针异常
                    list.pop();
                    if (list.isEmpty()) {
                        list.push(i);
                    } else {
                        // 索引相减即可或得答案
                        answer = Math.max(answer, i - list.peek());
                    }
                }
            }
            return answer;
        }
    }


    /**
     * 等差数列划分
     * <p>
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * <p>
     * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
     * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
     * <p>
     * 子数组 是数组中的一个连续序列。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5rz8i3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_02 {
        public int numberOfArithmeticSlices(int[] nums) {
            if (nums.length < 4) {
                return 0;
            }
            int answer = 0, sum = 0;
            for (int i = 2; i < nums.length; i++) {
                if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                    sum++;
                } else {
                    // 计算公式
                    answer += ((sum + 1) * sum) >>> 1;
                    sum = 0;
                }
            }
            // 循环结束前也可能存在  nums[i] - nums[i-1] == nums[i-1] - nums[i-2]
            answer += ((sum + 1) * sum) >>> 1;
            return answer;
        }
    }


    /**
     * 91. 解码方法
     * <p>
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     * <p>
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * <p>
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     * <p>
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     * <p>
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * <p>
     * 题目数据保证答案肯定是一个 32 位 的整数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/decode-ways
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution_03 {
        // 隐含条件 '0' 即为 ‘A’之前的编码
        public int numDecodings(String s) {
            int length = s.length();
            if (length < 2) return s.equals("0") ? 0 : 1;
            // 加空格哨兵
            s = "" + s;
            char[] chars = s.toCharArray();
            int[] ans = new int[length + 1];
            ans[0] = 1;
            for (int i = 1; i < length; i++) {
                int single = chars[i] - '0', double_ = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
                if (single <= 9 && single >= 1) ans[i] = ans[i - 1];
                if (double_ <= 26 && double_ >= 10 && i > 1) ans[i] += ans[i - 2];
            }
            return ans[length];
        }
    }


    /**
     * 分割回文串 II
     * <p>
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
     * <p>
     * 返回符合要求的 最少分割次数 。
     */
    class Solution_04 {
        public int minCut(String s) {
            int n = s.length();
            char[] cs = s.toCharArray();

            // g[l][r] 代表 [l,r] 这一段是否为回文串
            boolean[][] g = new boolean[n + 1][n + 1];
            for (int r = 1; r <= n; r++) {
                for (int l = r; l >= 1; l--) {
                    // 如果只有一个字符，则[l,r]属于回文
                    if (l == r) {
                        g[l][r] = true;
                    } else {
                        // 在 l 和 r 字符相同的前提下
                        if (cs[l - 1] == cs[r - 1]) {
                            // 如果 l 和 r 长度只有 2；或者 [l+1,r-1] 这一段满足回文，则[l,r]属于回文
                            if (r - l == 1 || g[l + 1][r - 1]) {
                                g[l][r] = true;
                            }
                        }
                    }
                }
            }

            // f[r] 代表将 [1,r] 这一段分割成若干回文子串所需要的最小分割次数
            int[] f = new int[n + 1];
            for (int r = 1; r <= n; r++) {
                // 如果 [1,r] 满足回文，不需要分割
                if (g[1][r]) {
                    f[r] = 0;
                } else {
                    // 先设定一个最大分割次数（r 个字符最多消耗 r - 1 次分割）
                    f[r] = r - 1;
                    // 在所有符合 [l,r] 回文的方案中取最小值
                    for (int l = 1; l <= r; l++) {
                        if (g[l][r]) f[r] = Math.min(f[r], f[l - 1] + 1);
                    }
                }
            }

            return f[n];
        }
    }


    /**
     * 两个字符串的删除操作
     * <p>
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     * <p>
     * 每步 可以删除任意一个字符串中的一个字符。
     */
    static class Solution_05 {
        public static void main(String[] args) {
            minDistance("as", "eas");
        }

        public static int minDistance(String word1, String word2) {
            char[] cs1 = word1.toCharArray(), cs2 = word2.toCharArray();
            int n = word1.length(), m = word2.length();
            int[][] f = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) f[i][0] = i;
            for (int j = 0; j <= m; j++) f[0][j] = j;
            print(f);
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    f[i][j] = Math.min(f[i - 1][j] + 1, f[i][j - 1] + 1);
                    if (cs1[i - 1] == cs2[j - 1]) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
            print(f);
            return f[n][m];
        }


        private static void print(int[][] arr) {
            for (int[] ints : arr) {
                System.out.println(Arrays.toString(ints));
            }
            System.out.println("===================");
        }
    }


    /**
     * 比特位计数
     * <p>
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     */
    class Solution_06 {
        public int[] countBits(int n) {
            int[] answer = new int[n + 1];
            answer[0] = 0;
            for (int i = 0; i <= n; i++) {
                if (i % 2 == 0) answer[i] = answer[i / 2];
                else answer[i] = answer[i - 1] + 1;
            }
            return answer;
        }
    }


    /**
     * 使序列递增的最小交换次数
     * <p>
     * 我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。
     * <p>
     * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
     * 返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。
     * <p>
     * 数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5ryaw5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_07 {
        public int minSwap(int[] nums1, int[] nums2) {
            int a = 0, b = 1;
            for (int i = 1; i < nums1.length; ++i) {
                int x = a, y = b;
                if (nums1[i - 1] >= nums1[i] || nums2[i - 1] >= nums2[i]) {
                    a = y;
                    b = x + 1;
                } else {
                    b = y + 1;
                    if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                        a = Math.min(a, y);
                        b = Math.min(b, x + 1);
                    }
                }
            }
            return Math.min(a, b);
        }
    }


    /**
     * 最低加油次数
     * <p>
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     * <p>
     * 沿途有加油站，用数组 stations 表示。其中 stations[i] = [positioni, fueli] 表示第 i 个加油站位于出发位置东面 positioni 英里处，并且有 fueli 升汽油。
     * <p>
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     * <p>
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     * <p>
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-number-of-refueling-stops
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int len = stations.length;
            if (len == 0) return target <= startFuel ? 0 : -1;
            int[] ans = new int[len + 1];
            ans[0] = startFuel;
            for (int i = 0; i < len; i++) {
                for (int j = i; j >= 0; j--) {
                    if (ans[j] >= stations[i][0]) ans[j + 1] = Math.max(ans[j + 1],ans[j]+stations[i][1]);
                }
            }
            for (int i = 0; i <= len; i++) {
                if (ans[i] >= target){
                    return i;
                }
            }
            return -1;
        }
    }
}
