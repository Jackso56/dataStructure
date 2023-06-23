package jason.dataStructure.sequenceTable;

import java.util.*;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/18 09:26:31
 **/
@SuppressWarnings({"all"})
public class Greedy {

    // region

    /**
     * 贪心算法概述：
     * 「贪心算法」由于适用的问题，每一个步骤只有一种选择，一般而言只需要记录与当前步骤相关的变量的值。
     * <p>
     * 使用贪心算法的问题需要满足的条件
     * 最优子结构：规模较大的问题的解由规模较小的子问题的解组成，区别于「动态规划」，
     * 可以使用「贪心算法」的问题「规模较大的问题的解」只由其中一个「规模较小的子问题的解」决定；
     * 无后效性：后面阶段的求解不会修改前面阶段已经计算好的结果；
     * 贪心选择性质：从局部最优解可以得到全局最优解。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(questionForEight("abc", "ahbgdc"));
    }


    /**
     * 分饼干问题
     * <p>
     * 一个孩子只能有一块饼干
     * <p>
     * * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * * <p>
     * * 对每个孩子 i，都有一个胃口值[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
     * * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
     * * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * * <p>
     * * <p>
     * * example：
     * * 输入: g = [1,2,3], s = [1,1]
     * * 输出: 1
     * * 解释:
     * * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * * 所以你应该输出1。
     * * <p>
     * * <p>
     * * 作者：LeetCode
     * * 链接：https://leetcode.cn/leetbook/read/greedy/rvrk1c/
     * * 来源：力扣（LeetCode）
     * * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param g
     * @param s
     * @return
     */
    public static int questionOne(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int lenG = g.length, lenS = s.length;
        int i = 0, j = 0;
        while (i < lenG && j < lenS) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }


    /**
     * 找零钱问题
     * <p>
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     * <p>
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * <p>
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * <p>
     * 注意，一开始你手头没有任何零钱。
     * <p>
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     * <p>
     * 思路：
     * 由于顾客只可能给你三个面值的钞票，而且我们一开始没有任何钞票，因此我们拥有的钞票面值只可能是 55 美元，1010 美元和 2020 美元三种。基于此，我们可以进行如下的分类讨论。
     * <p>
     * 55 美元，由于柠檬水的价格也为 55 美元，因此我们直接收下即可。
     * <p>
     * 1010 美元，我们需要找回 55 美元，如果没有 55 美元面值的钞票，则无法正确找零。
     * <p>
     * 2020 美元，我们需要找回 1515 美元，此时有两种组合方式，一种是一张 1010 美元和 55 美元的钞票，一种是 33 张 55 美元的钞票，如果两种组合方式都没有，则无法正确找零。当可以正确找零时，两种找零的方式中我们更倾向于第一种，即如果存在 55 美元和 1010 美元，我们就按第一种方式找零，否则按第二种方式找零，因为需要使用 55 美元的找零场景会比需要使用 1010 美元的找零场景多，我们需要尽可能保留 55 美元的钞票。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvp34i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param bills
     * @return
     */
    public static boolean questionTwo(int[] bills) {
        if (bills == null || bills.length <= 0) {
            return false;
        } else {
            int five = 0, ten = 0;
            for (int bill : bills) {
                if (bill == 5) {
                    five++;
                } else if (bill == 10) {
                    if (five == 0) {
                        return false;
                    } else {
                        five--;
                        ten++;
                    }
                } else {
                    if (five > 0 && ten > 0) {
                        five--;
                        ten--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 区间重叠问题
     * <p>
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * <p>
     * 注意:
     * <p>
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * <p>
     * <p>
     * example：
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * <p>
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rva7gg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param list
     * @return
     */
    public static int questionThree(int[][] list) {
        if (list == null || list.length == 0) {
            return 0;
        }
        Arrays.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[1] - arr2[1];
            }
        });
        int len = list.length;
        int answer = 1;
        int right = list[0][1];
        for (int i = 1; i < len; i++) {
            // 若上一区间的右边界小于下一区间的左边界，则无重叠
            if (list[i][0] >= right) {
                // 仅仅是记录无重叠区间
                answer++;
                right = list[i][1];
            }
        }
        // 减去不符合要求的剩下的就是符合要求的
        return len - answer;
    }


    /**
     * practice for qquestionThree
     *
     * @param list
     * @return
     */
    public static int testForQuestionThree(int[][] list) {
        if (list.length == 0 || list == null) {
            return 0;
        }
        Arrays.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int len = list.length;
        int answer = 1;
        int right = list[0][1];
        for (int i = 1; i < len; i++) {
            if (list[i][0] >= right) {
                answer++;
                right = list[i][1];
            }
        }
        return len - answer;
    }


    /**
     * 用最少数量的箭引爆气球
     * <p>
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     * <p>
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 x``start，x``end，且满足 xstart ≤ x ≤ x``end，则该气球会被引爆。可以射出的弓箭的数量没有限制。弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     * <p>
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
     * <p>
     * <p>
     * example:
     * 输入:
     * [[10,16], [2,8], [1,6], [7,12]]
     * <p>
     * 输出:
     * 2
     * <p>
     * 解释:
     * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rv2wkv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rv2wkv/
     * 来源：力扣（LeetCode）
     *
     * @param array
     * @return
     */
    public static int questionFour(int[][] array) {
        // judge empty
        if (array.length == 0 || array == null) {
            return 0;
        }
        // sort array by right node
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int answer = 1;
        long right = array[0][1];
        for (int i = 1; i < array.length; i++) {
            // right[0] > right  answer++
            if (array[i][0] > right) {
                answer++;
                right = array[i][1];
            }
        }
        return answer;
    }


    /**
     * practice for Question questionFour
     *
     * @param array
     * @return
     */
    public static int testForQuestionFour(int[][] array) {
        // empty judje
        if (array == null || array.length == 0) {
            return 0;
        }
        // sort array by right node
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int answer = 1;
        int right = array[0][1];
        for (int i = 1; i < array.length; i++) {
            // right[0] > right
            if (array[i][0] > right) {
                answer++;
                right = array[i][1];
            }
        }
        return answer;
    }


    /**
     * 合并区间
     * <p>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * <p>
     * example:
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvn2qj/
     *
     * @param array
     * @return
     */
    public static int[][] questionFive(int[][] array) {
        // judge empty
        if (array == null || array.length == 0) {
            return null;
        }
        // sort by right node
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 按照左端点排序
                return o1[0] - o2[0];
            }
        });
        int left, right;
        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < array.length; i++) {
            left = array[i][0];
            right = array[i][1];
            // list为空 || 没有重叠情况  --->  直接添加新的区间
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                // 有重叠的情况   Math.max(preRight,afterRight)  求最大值
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }


    /**
     * practice for questtionFour
     *
     * @param array
     * @return
     */
    public static int[][] testForQuestionFive(int[][] array) {
        if (array.length == 0 || array == null) {
            return null;
        }
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // left 左节点    right 右节点
        int left, right;
        // 存储答案
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            left = array[i][0];
            right = array[i][1];
            // list 为空  ||  没有重叠的情况
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                // 有重叠的情况
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }


    /**
     * 跳跃问题一
     * <p>
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个位置。
     *
     * @param array
     * @return
     */
    public static boolean questionSix(int[] array) {
        int target = array.length;
        int step = 0;
        for (int i = 0; i < target; i++) {
            if (i <= step) {
                step = Math.max(step, i + array[i]);
                if (step >= target - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    // endregion

    /**
     * 跳跃问题二(隐含条件：一定可以到达最后一个元素)
     * <p>
     * <p>
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * <p>
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * <p>
     * 0 <= j <= nums[i] 
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvlkdm/
     *
     * @param array
     * @return
     */
    public static int questtionSeven(int[] array) {
        int target = array.length - 1;
        int end = 0;
        int maxStep = 0;
        int step = 0;
        for (int i = 0; i < target; i++) {
            maxStep = Math.max(maxStep, array[i] + i);
            if (i == end) {
                end = maxStep;
                step++;
            }
        }
        return step;
    }


    /**
     * test for questionSeven
     *
     * @param array
     * @return
     */
    public static int testForQuestionSeven(int[] array) {
        int step = 0;
        int len = array.length;
        int maxStep = 0;
        int end = 0;
        for (int i = 0; i < len - 1; i++) {
            maxStep = Math.max(maxStep, array[i] + 1);
            if (i == end) {
                end = maxStep;
                step++;
            }
        }
        return step;
    }


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
     * @param children
     * @param parent
     * @return
     */
    public static boolean questionForEight(String children, String parent) {
        if ((parent.length() == children.length() && parent.length() == 0)
                || children == null || children.length() == 0) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < parent.length(); i++) {
            if (parent.charAt(i) == children.charAt(j)) {
                j++;
            }
            if (j == children.length()) {
                return true;
            }
        }
        return false;
    }


    /**
     * test for questionForSeven
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean testForQuestionEight(String s, String t) {
        if ((s.length() == t.length() && t.length() == 0) || s.length() == 0 || s == null) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j == s.length()) {
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
     *
     * @param price
     * @return
     */
    public static int questionNine(int[] prices) {
        int length = prices.length, low = 0, heigh = 0, i = 0, j = 1, sum = 0;
        while (i < length && j < length) {
            if (prices[i] >= prices[j]) {
                i++;
                j++;
            } else {
                low = prices[i];
                heigh = prices[j];
                j++;
                while (j < length) {
                    if (prices[j] > heigh) {
                        heigh = prices[j];
                    } else {
                        sum += heigh - low;
                        i = j;
                        j++;
                        heigh = Integer.MAX_VALUE;
                        break;
                    }
                    j++;
                }
            }
            if (heigh != Integer.MAX_VALUE) {
                sum += heigh - low;
            }
        }
        return sum;
    }

    /**
     * test for questionForNine
     *
     * @param prices
     * @return
     */
    public static int testForQuestionNine(int[] prices) {
        int sum = 0, len = prices.length, i = 0, j = 1, low = 0, height = 0;
        while (i < len && j < len) {
            if (prices[i] >= prices[j]) {
                j++;
                i++;
            } else {
                height = prices[j];
                low = prices[i];
                j++;
                while (j < len) {
                    if (prices[j] > height) {
                        height = prices[j];
                    } else {
                        sum += height - low;
                        i = j;
                        j++;
                        height = Integer.MAX_VALUE;
                        break;
                    }
                    j++;
                }
            }
            if (height != Integer.MAX_VALUE) {
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
    public static int arrayPairSum(int[] nums) {
        int len = nums.length >> 1, max = 0, min = 0, j = nums.length - 1, sum = 0;
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
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        // boxTypes[i][0] <= truckSize
        int count = 0, units = 0;
        int i = 0;
        while (count < truckSize && i < boxTypes.length) {
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
    public static int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int i = 0; i < position.length; i++) {
            // 基数之间和偶数之间无消耗，奇偶之间消耗为1
            if (position[i] % 2 == 0) {
                odd++;
            } else {
                even++;
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
    public static int minimumSwap(String s1, String s2) {
        // 核心：x y 不同的个数
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
        // 多数的一次交换 +一个两次交换
        // (xHave + yHave) / 2 的方式输出答案，会直接排出基数的情况
        return xHave / 2 + yHave / 2 + xHave % 2 * 2;
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
    public static boolean canConstruct(String s, int k) {
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
        // 最少回文字符串个数 <= k <= 最大回文字符串个数
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
    public static int minAddToMakeValid(String s) {
        int left = 0, answer = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                // 遇到左括号直接累加
                left++;
            } else if (left > 0) {
                // 有与右括号匹配的左括号
                left--;
            } else {
                // 没有与右括号匹配的左括号
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
    public static int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 这里采用差值排序
                return Integer.compare(o1[0] - o1[1], o2[0] - o1[1]);
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
    public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
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
