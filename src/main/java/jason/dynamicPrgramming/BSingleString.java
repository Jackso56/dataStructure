package jason.dynamicPrgramming;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/13 19:27:36
 **/
@SuppressWarnings({"all"})
public class BSingleString {

    /**
     * 最大子序和
     * <p>
     * 算法思路：连续求和，遇到和为负数则舍弃
     *
     * <p>
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     */
    class Solution_01 {
        public int maxSubArray(int[] nums) {
            int length = nums.length;
            if (length < 2) return nums[0];
            int[] answerNums = new int[length];
            int answer = nums[0];
            answerNums[0] = nums[0];
            for (int i = 1; i < length; i++) {
                answerNums[i] = Math.max(0, answerNums[i - 1]) + nums[i];
                answer = Math.max(answerNums[i], answer);
            }
            return answer;
        }
    }


    /**
     * 乘积最大子组数
     * <p>
     * 算法思路：Math.max(maxSum * num, minSum * num, num)
     *
     * <p>
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。
     * 子数组 是数组的连续子序列。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5rmwns/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_02 {
        public int maxProduct(int[] nums) {
            int length = nums.length;
            if (length < 2) return nums[0];
            // min, max 分别维护前 i 个连续子序列和的 最小值和最大值
            int[] max = new int[length];
            int[] min = new int[length];
            System.arraycopy(nums, 0, max, 0, length);
            System.arraycopy(nums, 0, min, 0, length);
            for (int i = 1; i < length; i++) {
                // Math.max(maxSum * num, minSum * num, num)    维护最大和数组
                max[i] = Math.max(max[i - 1] * nums[i], Math.max(min[i - 1] * nums[i], nums[i]));
                // Math.min(maxSum * num, minSum * num, num)    维护最小和数组
                min[i] = Math.min(min[i - 1] * nums[i], Math.min(max[i - 1] * nums[i], nums[i]));
            }
            int answer = max[0];
            for (int i = 0; i < length; i++) {
                answer = Math.max(answer, max[i]);
            }
            return answer;
        }
    }


    /**
     * 环形子数组的最大和
     * <p>
     * 算法思路：1.maxSub > 0 ---> Math.max(maxSub, sum - minSub)  2. maxSub <= 0  ---> maxSub
     * <p>
     * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。环形数组 意味着数组的末端将会与
     * 开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
     * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，
     * 不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5r2pah/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_03 {
        public int maxSubarraySumCircular(int[] nums) {
            int sum = 0, maxSub = nums[0], maxCur = 0, minSub = nums[0], minCur = 0;
            for (int num : nums) {
                // 当前最大值
                maxCur = Math.max(maxCur + num, num);
                // 当前最大和的子序列
                maxSub = Math.max(maxCur, maxSub);
                // 当前最小值
                minCur = Math.min(minCur + num, num);
                // 当前最小和的子序列
                minSub = Math.min(minCur, minSub);
                sum += num;
            }
            // maxSub > 0 时，取 "maxSub, sum - minSub" 较大值， maxSub < 0 表示元素全为负数，则 maxSub 即为最大值
            return maxSub > 0 ? Math.max(maxSub, sum - minSub) : maxSub;
        }
    }


    /**
     * 最大子矩阵
     * <p>
     * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
     * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
     * <p>
     * 注意：本题相对书上原题稍作改动
     * <p>
     * 示例：
     * <p>
     * 输入：
     * [
     *    [-1,0],
     *    [0,-1]
     * ]
     * <p>
     * 输出：[0,1,0,1]
     * <p>
     * 解释：输入中标粗的元素即为输出所表示的矩阵
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5rnep3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
     */
    class Solution_04 {

    }


    /**
     * 矩形区域不超过 K 的最大数值和
     * <p>
     * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
     * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5rvxrm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_05 {

    }

}
