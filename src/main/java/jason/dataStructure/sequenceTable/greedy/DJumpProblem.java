package jason.dataStructure.sequenceTable.greedy;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/23 10:40:11
 **/
@SuppressWarnings({"all"})
public class DJumpProblem {

    /**
     * 跳跃问题
     *
     * 这一节我们的跳跃问题也是使用「贪心算法」解决的经典问题。
     * 对于不同的目标「贪心算法」贪心的点是不一样的。
     * 大家可以在学习完这两个例题之后，分析它们之间的区别。
     */


    /**
     * 跳跃问题
     * <p>
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int target = nums.length;
        int step = 0;
        for (int i = 0; i < target; i++) {
            if (i <= step) {
                step = Math.max(step, i + nums[i]);
                if (step >= target - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 跳跃问题
     * <p>
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * <p>
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * <p>
     * 0 <= j <= nums[i] 
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     * <p>
     *  
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvlkdm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int end = 0;
        int maxStep = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxStep = Math.max(maxStep, nums[i] + i);
            if (i == end) {
                end = maxStep;
                step++;
            }
        }
        return step;
    }
}
