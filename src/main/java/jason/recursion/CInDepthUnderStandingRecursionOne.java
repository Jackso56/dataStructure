package jason.recursion;

import java.util.*;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/27 13:20:16
 **/
@SuppressWarnings({"all"})
public class CInDepthUnderStandingRecursionOne {



    /**
     * 数组中的逆序对
     * <p>
     * 核心：归并排序
     * <p>
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
     *
     * @param nums
     * @return
     */

    class Solution_01 {
        public int reversePairs(int[] nums) {
            int len = nums.length;
            // 当数组长度小于2时，会出现栈溢出的情况
            if (len < 2) {
                return 0;
            }
            int[] tempNums = new int[len];
            return pairs(nums, 0, len - 1, tempNums);
        }

        private int pairs(int[] nums, int start, int end, int[] tempNums) {
            if (start == end) {
                return 0;
            }
            // 这里处理不当容易出翔栈溢出的情况
            int middle = start + (end - start) / 2;
            // 对左右两边分别进行分治
            int leftArea = pairs(nums, start, middle, tempNums);
            int rightArea = pairs(nums, middle + 1, end, tempNums);
            // 当左边界的值 大于 有边界的值   则直接返回左右两边的结果
            if (nums[middle] <= nums[middle + 1]) {
                return leftArea + rightArea;
            }
            int allArea = partition(nums, start, middle, end, tempNums);
            return leftArea + rightArea + allArea;
        }

        private int partition(int[] nums, int start, int middle, int end, int[] tempNums) {
            for (int i = start; i <= end; i++) {
                tempNums[i] = nums[i];
            }
            int result = 0;
            int left = start;
            int right = middle + 1;
            for (int i = start; i <= end; i++) {
                // 两个边界条件   left > middle + 1、right > end + 1
                if (left > middle) {
                    nums[i] = tempNums[right++];
                } else if (right > end) {
                    nums[i] = tempNums[left++];
                } else if (tempNums[left] <= tempNums[right]) {
                    nums[i] = tempNums[left++];
                } else {
                    // tempNums[left] > tempNums[right] 时，添加逆序对
                    nums[i] = tempNums[right++];
                    result += (middle - left + 1);
                }
            }
            return result;
        }
    }


    /**
     * 数组中的第K个最大元素
     * <p>
     * 思路1：维护动态数据的最大最小值，可以考虑堆 (findKthLargestThoughtOne)
     * <p>
     * 思路2：快速选择算法，基于快排 (findKthLargestThoughtTwo)
     * <p>
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * <p>
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * @param nums
     * @param k
     * @return
     */
    class Solution_02 {
        public int findKthLargestThoughtOne(int[] nums, int k) {
            Queue<Integer> queue = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
            for (int num : nums) {
                queue.add(num);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            return queue.poll();
        }

        public int findKthLargestThoughtTwo(int[] nums, int k) {
            return findIndex(nums, 0, nums.length - 1, nums.length - k);
        }

        private int findIndex(int[] nums, int start, int end, int target) {
            // 获取随机轴值
            int index = partitionThoughtTwo(nums, start, end);
            // 边界条件
            if (index == target) {
                return nums[index];
            }
            // 递归求target
            return target > index ? findIndex(nums, index + 1, end, target)
                    : findIndex(nums, start, index - 1, target);
        }

        private int partitionThoughtTwo(int[] nums, int start, int end) {
            int pivot = nums[end], left = start - 1;
            for (int i = start; i < end; i++) {
                if (nums[i] <= pivot) {
                    // 将小于轴值的值置于 左半边
                    swap(nums, ++left, i);
                }
            }
            // 轴值归位，并将其返回
            swap(nums, left + 1, end);
            return left + 1;
        }

        private int getPivot(int[] nums, int start, int end) {
            int index = (int) (Math.random() * (end - start + 2));
            // 将轴值置于末尾
            swap(nums, index, end);
            // 通过快排返回对应的轴值
            return partitionThoughtTwo(nums, start, end);
        }

        private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }


    /**
     * 计算右侧小于当前元素的个数
     * <p>
     * 注意：归并排序的过程中，会出现元素和下标不对应的情况
     * <p>
     * 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质：
     * counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rn0aht/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    class Solution_03 {
        // index
        private int[] index;
        // tempNums
        private int[] temp;
        // middleIndex
        private int[] tempIndex;
        // answer
        private int[] ans;

        public List<Integer> countSmaller(int[] nums) {
            this.index = new int[nums.length];
            this.temp = new int[nums.length];
            this.tempIndex = new int[nums.length];
            this.ans = new int[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                index[i] = i;
            }
            int l = 0, r = nums.length - 1;
            mergeSort(nums, l, r);
            List<Integer> list = new ArrayList<Integer>();
            for (int num : ans) {
                list.add(num);
            }
            return list;
        }

        public void mergeSort(int[] a, int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = (l + r) >> 1;
            mergeSort(a, l, mid);
            mergeSort(a, mid + 1, r);
            merge(a, l, mid, r);
        }

        public void merge(int[] a, int l, int mid, int r) {
            int i = l, j = mid + 1, p = l;
            while (i <= mid && j <= r) {
                if (a[i] <= a[j]) {
                    temp[p] = a[i];
                    tempIndex[p] = index[i];
                    ans[index[i]] += (j - mid - 1);
                    ++i;
                    ++p;
                } else {
                    temp[p] = a[j];
                    tempIndex[p] = index[j];
                    ++j;
                    ++p;
                }
            }
            while (i <= mid) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                ans[index[i]] += (j - mid - 1);
                ++i;
                ++p;
            }
            while (j <= r) {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
            for (int k = l; k <= r; ++k) {
                index[k] = tempIndex[k];
                a[k] = temp[k];
            }
        }
    }


    /**
     * 翻转对
     * <p>
     * 核心思路：归并排序，并之前统计翻转对
     * <p>
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * <p>
     * 你需要返回给定数组中的重要翻转对的数量。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rn7ofr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution_04 {
        // 统计翻转对个数
        private int reslut = 0;

        public int reversePairs(int[] nums) {
            int length = nums.length;
            if (length < 2) {
                return 0;
            }
            int[] temp = new int[length];
            mergeSort(nums, 0, length - 1, temp);
            return reslut;
        }

        private void mergeSort(int[] nums, int start, int end, int[] temp) {
            if (start >= end) {
                return;
            }
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid, temp);
            mergeSort(nums, mid + 1, end, temp);
            // 统计反转对 对数
            for (int i = start, j = mid + 1; i <= mid && j <= end; ) {
                if ((long) nums[i] > (long) 2 * nums[j]) {
                    reslut += mid - i + 1;
                    j++;
                } else {
                    i++;
                }
            }
            partition_(nums, start, mid, end, temp);
        }

        private void partition_(int[] nums, int start, int mid, int end, int[] temp) {
            for (int i = start; i <= end; i++) {
                temp[i] = nums[i];
            }
            int index = start, left = start, right = mid + 1;
            while (left <= mid && right <= end) {
                if (temp[left] <= temp[right]) {
                    nums[index++] = temp[left++];
                } else {
                    nums[index++] = temp[right++];
                }
            }
            while (left <= mid) {
                nums[index++] = temp[left++];
            }
            while (right <= end) {
                nums[index++] = temp[right++];
            }
        }
    }


    /**
     * 最大子序和
     * <p>
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     */
    static class Solution_05 {

        /**
         * 方式一：贪心算法
         *
         * @param nums
         * @return
         */
        public static int maxSubArray(int[] nums) {
            if (nums.length < 2) {
                return nums[0];
            }
            int answer = nums[0], previous = 0;
            for (int value : nums) {
                previous = previous + value >= value ? previous + value : value;
                answer = previous >= answer ? previous : answer;
            }
            return answer;
        }

    }

}
