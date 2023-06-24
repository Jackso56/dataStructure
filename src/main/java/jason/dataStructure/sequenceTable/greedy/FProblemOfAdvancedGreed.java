package jason.dataStructure.sequenceTable.greedy;


import jdk.nashorn.internal.ir.CallNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/23 10:57:36
 **/
@SuppressWarnings({"all"})
public class FProblemOfAdvancedGreed {

    public static void main(String[] args) {
        System.out.println(new StringBuilder("jason"));
    }


    /**
     * 分发糖果
     * <p></p>
     * 核心：左右依次遍历，再取最大值
     * <p></p>
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * <p></p>
     * 你需要按照以下要求，给这些孩子分发糖果：
     * <p></p>
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     * <p></p>
     *  
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rv48cr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        left[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            left[i] = ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : 1;
        }
        int answer = left[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                left[i] = Math.max(left[i], left[i + 1] + 1);
            }
            answer += left[i];
        }
        return answer;
    }


    /**
     * 最大子序和 (这里使用到了分治算法)
     * <p></>
     * 核心：previousSum 与 currentSum 作比较，取较大值，在这过程会出现一个最大值
     * <p></>
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p></>
     * 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int previous = 0, max = nums[0], i;
        for (int num : nums) {
            previous = Math.max(previous + num, num);
            max = Math.max(max, previous);
        }
        return max;
    }


    ;

    /**
     * 摆动序列
     * <p></>
     * 核心：以num[i]为中心，其两边的差不同时为0即可，因为每次添加 num[i+1],即添加一个元素
     * <p>
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     * <p></>
     * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
     * <p></>
     * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * <p></>
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     * <p></>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvy04d/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int previous = nums[1] - nums[0];
        int answer = previous != 0 ? 2 : 1;
        int current;
        for (int i = 2; i < len; i++) {
            current = nums[i] - nums[i - 1];
            if ((current > 0 && previous <= 0) || (current < 0 && previous >= 0)) {
                answer++;
                previous = current;
            }
        }
        return answer;
    }


    /**
     * 单调递增的数字
     * <p></>
     * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
     * <p></>
     * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
     * <p>
     *  
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvcvsh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        int i = 1, len = nums.length;
        // 寻找原数组中 k=右边界值+1  result <= [0,k]组成的数字
        while (i < len && nums[i - 1] <= nums[i]) {
            i++;
        }
        // k < nums.length
        if (i < len) {
            // 构造新数组的右边界值
            while (i > 0 && nums[i - 1] > nums[i]) {
                // -1,使其符合单调递增的规则
                nums[i - 1] -= 1;
                i--;
            }
            // 构造单调递增数
            for (i += 1; i < len; i++) {
                nums[i] = '9';
            }
        }
        return Integer.valueOf(new String(nums));
    }


    /**
     * 移掉 K 位数字
     * <p></>
     * 核心：使序列单调递增
     * <p></>
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，
     * 使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        // 通过双端队列实现数据的筛选
        Deque<Character> deque = new LinkedList<Character>();
        int len = num.length();
        char current = ' ';
        // 获取尽可能小的数值
        for (int i = 0; i < len; i++) {
            current = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > current) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(current);
        }
        // 特殊处理   k > 0
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        // 删除前导 0
        StringBuilder answer = new StringBuilder();
        boolean zero = true;
        while (!deque.isEmpty()) {
            current = deque.pollFirst();
            if (current == '0' && zero) {
                continue;
            }
            zero = false;
            answer.append(current);
        }
        return answer.length() == 0 ? "0" : answer.toString();
    }


    /**
     * 翻转矩阵后的得分
     * <p></>
     * 核心：grid[i][0] == 1  &&  grid[i][n] ==> count(0) > count(1)   ~grid[i][n], 本题中并没有修改原数组
     * <p>
     * 给你一个大小为 m x n 的二元矩阵 grid ，矩阵中每个元素的值为 0 或 1 。
     * <p>
     * 一次 移动 是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     * <p>
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的 得分 就是这些数字的总和。
     * <p>
     * 在执行任意次 移动 后（含 0 次），返回可能的最高分数。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvjrkm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public int matrixScore(int[][] grid) {
        // answer 为第一列全一的和
        int row = grid.length, clumn = grid[0].length;
        int answer = row * (1 << (clumn - 1)), one = 0;
        for (int i = 1; i < clumn; i++) {
            for (int j = 0; j < row; j++) {
                // grid[j][0] == 1   表示行没有反转过
                if (grid[j][0] == 1) {
                    one += grid[j][i];
                // grid[j][0] == 0   表示行反转过
                } else {
                    one += (1 - grid[j][i]);
                }
            }
            // 获取尽可能多的1的个数
            one = Math.max(one,row - one);
            answer += one * (1<<(clumn - 1 - i));
            one = 0;
        }
        return answer;
    }

}
