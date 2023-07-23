package jason.dynamicPrgramming;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/14 19:37:41
 **/
@SuppressWarnings({"all"})
public class CSingleString {


    /**
     * 打家劫舍
     * <p>
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5rwy97/
     * 来源：力扣（LeetCode）
     */
    class Solution_01 {

        public int rob(int[] nums) {
            int pre = 0, curr = 0, temp = 0;
            // 每次循环，计算“偷到当前房子为止的最大金额”
            for (int num : nums) {
                // 循环开始时，curr 表示 dp[k-1]，prev 表示 dp[k-2] num 表示 k
                // dp[k] = max{ dp[k-1], dp[k-2] + i }
                temp = Math.max(curr, pre + num);
                pre = curr;
                curr = temp;
            }
            return curr;
        }
    }


    /**
     * 打家劫舍 II
     * <p>
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5rds55/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_02 {

        /**
         * 此题是 198. 打家劫舍 的拓展版： 唯一的区别是此题中的房间是 环状排列 的（即首尾相接），而 198.198. 题中的房间是 单排排列 的；而这也是此题的难点。
         * <p>
         * 环状排列 意味着第一个房子和最后一个房子中 只能选择一个偷窃，因此可以把此 环状排列房间 问题约化为两个 单排排列房间 子问题：
         * <p>
         * 1. 在不偷窃第一个房子的情况下
         * <p>
         * 2.在不偷窃最后一个房子的情况下
         * <p>
         * 综合偷窃最大金额： 为以上两种情况的较大值
         * <p>
         * 作者：jyd
         * 链接：https://leetcode.cn/problems/house-robber-ii/solution/213-da-jia-jie-she-iidong-tai-gui-hua-jie-gou-hua-/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         * @param nums
         * @return
         */
        public int rob(int[] nums) {
            if (nums.length < 2) return nums[0];
            return Math.max(resolve(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                    resolve(Arrays.copyOfRange(nums, 1, nums.length)));
        }

        private int resolve(int[] nums) {
            int pre = 0, curr = 0, temp = 0;
            for (int num : nums) {
                temp = Math.max(curr, pre + num);
                pre = curr;
                curr = temp;
            }
            return curr;
        }
    }


    /**
     * 删除与获得点数
     * <p>
     * 给你一个整数数组 nums ，你可以对它进行一些操作。
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5r0kh6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution_03 {
        public int deleteAndEarn(int[] nums) {
            if (nums.length < 2) return nums[0];
            int length = nums.length, max = 0;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            int[] index = new int[max + 1];
            for (int num : nums) {
                index[num]++;
            }
            int pre = 0, curr = 0, temp = 0;
            for (int i = 0; i <= max; i++) {
                // 按照打家劫舍的算法思想，对统计数组进行 动态规划
                temp = Math.max(pre + i * index[i], curr);
                pre = curr;
                curr = temp;
            }
            return curr;
        }
    }


    /**
     * 3n 块披萨
     * <p>
     * 算法思路：同打家劫舍||
     * <p>
     * 给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
     * <p>
     * 你挑选 任意 一块披萨。
     * Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
     * Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
     * 重复上述过程直到没有披萨剩下。
     * 每一块披萨的大小按顺时针方向由循环数组 slices 表示。
     * <p>
     * 请你返回你可以获得的披萨大小总和的最大值。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/pizza-with-3n-slices
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution_04 {

        public int maxSizeSlices(int[] slices) {
            int length = slices.length - 1;
            // 环形数组遍历方式：遍历两次线性数组
            // 1. 不包含第一个元素
            // 2. 不包含最后一个元素
            int[] withOutFirst = new int[length];
            int[] withOutLast = new int[length];
            int num1 = calculate(Arrays.copyOfRange(slices, 0, length));
            int num2 = calculate(Arrays.copyOfRange(slices, 1, length + 1));
            return Math.max(num1, num2);
        }

        public int calculate(int[] slices) {
            int n = slices.length;
            int c = (n + 1) / 3;
            int[][] dp = new int[n + 1][c + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= c; j++) {
                    // i 表示第几个元素， j 表示前几个元素的和   其中 j = i - 1
                    // 选择了第 i 个元素  dp[i][j] = dp[i - 2][j - 1] + slices[i - 1]
                    // 没有选择第 i 个元素  dp[i][j] = dp[i - 1][j]
                    dp[i][j] = Math.max(dp[i - 1][j], (i - 2 >= 0 ? dp[i - 2][j - 1] : 0) + slices[i - 1]);
                }
            }
            return dp[n][c];
        }
    }
}
