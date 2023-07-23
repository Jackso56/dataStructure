package jason.dynamicPrgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/10 20:34:45
 **/
@SuppressWarnings({"all"})
public class ASingleString {


    /**
     * 最长递增子序列
     * <p>
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public int lengthOfLIS(int[] nums) {
            int length = nums.length;
            // 特殊情况
            if (length < 2) return 1;
            // 存储 子问题的答案
            int[] tempNums = new int[length];
            Arrays.fill(tempNums, 1);
            // 存储最终答案
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    // 核心：对序列长度进行累加   所以 “tempNums[j]+1” 使用 “j” 进行索引
                    if (nums[j] < nums[i]) tempNums[i] = Math.max(tempNums[i], tempNums[j] + 1);
                }
                // 每次记录跟新答案
                result = Math.max(tempNums[i], result);
            }
            return result;
        }
    }


    /**
     * 最长递增子序列的个数
     * <p>
     * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
     * <p>
     * 注意 这个数列必须是 严格 递增的。
     */
    class Solution_02 {
        public int findNumberOfLIS(int[] nums) {
            int length = nums.length;
            if (length < 2) {
                return 1;
            }
            // countLength 记录最长子序列的长度    countSubString 记录最长子序列的个数
            int[] countLength = new int[length], countSubString = new int[length];
            // 存在 nums 元素相同的情况  则最长子序列的长度和最长子序列的个数均为1
            Arrays.fill(countLength, 1);
            Arrays.fill(countSubString, 1);
            int maxLen = Integer.MIN_VALUE, result = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    // nums[i] > nums[j] 时可以组成严格递增子序列
                    if (nums[i] > nums[j]) {
                        // countLength[j] + 1 > countLength[i] 时会组成新的最长子序列
                        if (countLength[j] + 1 > countLength[i]) {
                            countSubString[i] = countSubString[j];
                            // countLength[j] + 1 == countLength[i] 时最长子序列的长度需要进行累加
                        } else if (countLength[i] == countLength[j] + 1) {
                            countSubString[i] += countSubString[j];
                        }
                        // 跟新最长子序列的长度
                        countLength[i] = Math.max(countLength[j] + 1, countLength[i]);
                    }
                }
                maxLen = Math.max(maxLen, countLength[i]);
            }
            for (int i = 0; i < length; i++) {
                // 当 countLength[i] == maxLen 时，countSubString[i] 为 [0.i] 上最长子序列的长度的个数
                if (countLength[i] == maxLen) result += countSubString[i];
            }
            return result;
        }
    }


    /**
     * 俄罗斯套娃信封问题
     * <p>
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * <p>
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * <p>
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * <p>
     * 注意：不允许旋转信封。
     * <p>
     * 作者：FennelDumplings
     * 链接：https://leetcode.cn/leetbook/read/dynamic-programming-1-plus/5okoej/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution_03 {

        public static void main(String[] args) {
            Solution_03 solution03 = new Solution_03();
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(2);
            objects.add(2);
            objects.add(3);
            System.out.println(objects);
//            int[][] arr = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}};
//            int[][] arr = {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4},{9,5}};
            int[][] arr = {{1, 2}, {2, 3}, {3, 4}, {3, 5}, {4, 5}, {5, 5}, {5, 6}, {6, 7}, {7, 8}};
            System.out.println(solution03.maxEnvelopes_02(arr));
            for (int[] ints : arr) {
                System.out.println(Arrays.toString(ints));
            }
        }

        /**
         * 算法思路：动态规划
         * <p>
         * 该方式会超时，无法通过OJ
         *
         * @param envelopes
         * @return
         */
        public int maxEnvelopes_01(int[][] envelopes) {
            if (envelopes.length < 2) {
                return 1;
            }
            Arrays.sort(envelopes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
                }
            });
            int length = envelopes.length, answer = 1;
            int[] count = new int[length];
            Arrays.fill(count, 1);
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    if (envelopes[i][1] > envelopes[j][1]) {
                        count[i] = Math.max(count[i], count[j] + 1);
                    }
                }
                answer = Math.max(answer, count[i]);
            }
            return answer;
        }

        /**
         * 算法思路：动态规划 + 二分查找
         * @param envelopes
         * @return
         */
        public int maxEnvelopes_02(int[][] envelopes) {
            int length = envelopes.length;

            // 特殊情况
            if (length < 2) {
                return 1;
            }

            // 排序：[[num1,num2],[num1,num2],[num1,num2]....]
            // num1 --> 升序     num1相等时 --> num2降序
            Arrays.sort(envelopes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
                }
            });

            // 创建临时数组存储所有的 num2
            int[] tempEnvelopes = new int[length];
            for (int i = 0; i < length; i++) tempEnvelopes[i] = envelopes[i][1];

            // 对 num2 进行 LIS
            return LIS(tempEnvelopes);
        }

        private int LIS(int[] tempEnvelopes) {
            int length = tempEnvelopes.length, substringLen = 0;
            int[] maxLength = new int[length];

            // 遍历数组，获取最长子序列长度
            for (int tempEnvelope : tempEnvelopes) {

                // 通过二分查找寻找 tempEnvelopes 中找 tempEnvelopes
                int index = Arrays.binarySearch(tempEnvelopes, 0, substringLen, tempEnvelope);

                // index < 0  表示未找到， 二分查找未找到元素时 返回  -(index + 1)
                if (index < 0) {
                    index = -(index + 1);
                }
                // 1.tempEnvelop 不在 tempEnvelopes 中，
                //    (1) 对应位置上有值，则覆盖
                //    (2) 对应位置上无值，则添加
                // 2.tempEnvelop 在 tempEnvelopes 中， 则值不变
                // 如此可做到不遗漏任何一个元素
                tempEnvelopes[index] = tempEnvelope;
                if (index == substringLen) {
                    substringLen++;
                }
            }
            return substringLen;
        }



    }


}
