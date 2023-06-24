package jason.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/23 10:45:13
 **/
@SuppressWarnings({"all"})
public class EUsualProblemOfGreed {

    /**
     * 判断子序列问题
     * <p>
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 进阶：
     * <p>
     * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     * <p>
     * 致谢：
     * <p>
     * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvt725/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if ((s.length() == t.length() && t.length() == 0) || s.length() == 0 || s == null) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < t.length(); i++) {

            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j == s.length() ) {
                return true;
            }
        }
        return false;
    }


    /**
     * 买股票最佳时机问题
     * <p>
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * <p>
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * <p>
     * 返回 你能获得的 最大 利润 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rv8wm6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p></p>
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int sum = 0, len = prices.length, i = 0, j = 1, low = 0, height = 0;
        while (i < len && j < len) {
            if (prices[i] >= prices[j]) {
                j++;
                i++;
            } else {
                height = prices[j];
                low = prices[i];
                j++;
                while(j < len ){
                    if (prices[j] > height){
                        height = prices[j];
                    }else {
                        sum += height - low;
                        i = j;
                        j++;
                        height = Integer.MAX_VALUE;
                        break;
                    }
                    j++;
                }
            }
            if (height != Integer.MAX_VALUE){
                sum += height - low;
            }
        }
        return sum;
    }


    /**
     * 数组拆分
     * <p>
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对,
     * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 返回该 最大总和 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rv32p2/
     * 来源：力扣（LeetCode）
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        int len = nums.length >> 1, max = 0, min = 0, j = nums.length - 1,sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            max = nums[j];
            min = nums[j - 1];
            j -= 2;
            sum += min;
        }
        return sum;
    }


    /**
     * 卡车上的最大单元数
     * <p>
     * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
     * <p>
     * numberOfBoxesi 是类型 i 的箱子的数量。
     * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
     * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
     * <p>
     * 返回卡车可以装载 单元 的 最大 总数。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvqpoc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param boxTypes
     * @param truckSize
     * @return
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        // boxTypes[i][0] <= truckSize
        int count = 0, units = 0;
        int i = 0;
        while(count < truckSize && i < boxTypes.length){
            if (count + boxTypes[i][0] <= truckSize) {
                units += boxTypes[i][1] * boxTypes[i][0];
                count += boxTypes[i][0];
                i++;
            } else {
                units += (truckSize - count) * boxTypes[i][1];
                break;
            }
        }
        return units;
    }


    /**
     * 玩筹码
     * <p>
     * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
     * <p>
     * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
     * <p>
     * position[i] + 2 或 position[i] - 2 ，此时 cost = 0
     * position[i] + 1 或 position[i] - 1 ，此时 cost = 1
     * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rv6eei/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param position
     * @return
     */
    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0){
                odd ++;
            } else {
                even ++;
            }
        }
        return odd > even ? even : odd;
    }


    /**
     * 交换字符使得字符串相同
     * <p>
     * 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
     * <p>
     * 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
     * <p>
     * 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
     * <p>
     * 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
     * <p>
     *  
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvkx9e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumSwap(String s1, String s2) {
        // 核心：计算不同 xy
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int xHave = 0;
        int yHave = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (char1[i] - char2[i] < 0) {
                xHave++;
            } else if (char1[i] - char2[i] > 0) {
                yHave++;
            }
        }
        if ((xHave + yHave) % 2 != 0) {
            return -1;
        }
        // 多数的一次交换+一个两次交换
        return xHave/2 + yHave / 2 + xHave % 2 * 2;
    }


    /**
     * 构造 K 个回文字符串
     * <p>
     * 核心：计算出基数字符数
     * <p>
     * 给你一个字符串 s 和一个整数 k 。请你用 s 字符串中 所有字符 构造 k 个非空 回文串 。
     * <p>
     * 如果你可以用 s 中所有字符构造 k 个回文字符串，那么请你返回 True ，否则返回 False 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvkjxg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param k
     * @return boolean
     */
    public boolean canConstruct(String s, int k) {
        if (s.length() == k) {
            return true;
        }
        if (s.length() < k) {
            return false;
        }
        char[] strArray = s.toCharArray();
        int max = s.length(), min = 0, charsLen = (int) 'z' + 1;
        int[] chars = new int[charsLen];
        for (int i = 0; i < max; i++) {
            chars[(int) strArray[i]]++;
        }
        for (int i = 96; i < charsLen; i++) {
            if (chars[i] % 2 != 0) {
                min++;
            }
        }
        return k >= min && k <= max;
    }



    /**
     * 使括号有效的最少添加
     * <p>
     * 核心：栈思想
     * <p>
     * 只有满足下面几点之一，括号字符串才是有效的：
     * <p>
     * 它是一个空字符串，或者<p>
     * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
     * 它可以被写作 (A)，其中 A 是有效字符串。
     * 给定一个括号字符串 s ，在每一次操作中，你都可以在字符串的任何位置插入一个括号
     * <p>
     * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
     * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvgyhv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return int
     */
    public int minAddToMakeValid(String s) {
        int left = 0, answer = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (left > 0) {
                left--;
            } else {
                answer++;
            }
        }
        return left + answer;
    }


    /**
     * 两地面试
     * <p>
     * 核心：差值排序使一边尽可能小，则总和就会越小
     * <p>
     * 公司计划面试 2n 人。给你一个数组 costs ，
     * 其中 costs[i] = [aCosti, bCosti] 。第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti 。
     * <p>
     * 返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvzzjj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0]-o1[1],o2[0]-o2[1]);
            }
        });
        int answer = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < costs.length / 2) {
                answer += costs[i][0];
            } else {
                answer += costs[i][1];
            }
        }
        return answer;
    }


    /**
     * 给定行和列的和求可行矩阵
     * <p>
     * 核心：取行列的最小值
     * <p>
     * 给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
     * <p>
     * 请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。
     * <p>
     * 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvs6bt/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param rowSum
     * @param colSum
     * @return
     */
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int[][] answer = new int[rowSum.length][colSum.length];
        int value = 0;
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                value = Math.min(rowSum[i],colSum[j]);
                answer[i][j] = value;
                rowSum[i] -= value;
                colSum[j] -= value;
            }
        }
        return answer;
    }
}
