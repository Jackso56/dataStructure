package jason.recursion;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/27 15:46:42
 **/
@SuppressWarnings({"all"})
public class Test {

    public static void main(String[] args) {
        int[] nums = {4, 7, 1, 2, 9, 3, 4, 7, 8, 2};
        int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(nums));
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static int reversePairs(int[] nums) {
        int len = nums.length;
        // 当数组长度小于2时，会出现栈溢出的情况
        if (len < 2) {
            return 0;
        }
        int[] tempNums = new int[len];
        return pair(nums, 0, len - 1, tempNums);
    }

    private static int pair(int[] nums, int start, int end, int[] tempNums) {
        // 边界条件
        if (start == end) {
            return 0;
        }
        // 求中点
        int middle = start + (end - start) / 2;
        // 对左右两边分别进行分治
        int leftResult = pair(nums, start, middle, tempNums);
        int rightResult = pair(nums, middle + 1, end, tempNums);
        // 当左边界的值 大于 有边界的值   则直接返回左右两边的结果
        if (nums[middle] <= nums[middle + 1]) {
            return leftResult + rightResult;
        }
        int allResult = partition(nums, start, middle, end, tempNums);
        return leftResult + rightResult + allResult;
    }

    private static int partition(int[] nums, int start, int middle, int end, int[] tempNums) {
        // 临时数组存储数组
        for (int i = start; i <= end; i++) {
            tempNums[i] = nums[i];
        }
        int result = 0;
        int leftStart = start;
        int rightStart = middle + 1;
        for (int i = start; i <= end; i++) {
            // 两个边界条件   leftStart > middle + 1、rightStart > end + 1
            if (leftStart > middle) {
                nums[i] = tempNums[rightStart++];
            } else if (rightStart > end) {
                nums[i] = tempNums[leftStart++];
            } else if (tempNums[leftStart] <= tempNums[rightStart]) {
                nums[i] = tempNums[leftStart++];
            } else {
                // tempNums[leftStart] > tempNums[rightStart] 时，添加逆序对
                nums[i] = tempNums[rightStart++];
                result += (middle - leftStart + 1);
            }
        }
        return result;
    }


    public static void quickSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        quicksort(nums, 0, nums.length - 1);
    }

    private static void quicksort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = merge(nums, left, right);
        quicksort(nums, left, middle - 1);
        quicksort(nums, middle + 1, right);
    }

    private static int randomMiddle(int left, int right) {
        return new Random().nextInt(left + (right - left));
    }

    private static int merge(int[] nums, int left, int right) {
        int middle = randomMiddle(left, right);
        swap(nums, middle, left);
        int target = nums[left];
        int tempLeft = left + 1;
        int tempRight = right;
        while (tempLeft < tempRight) {
            while (tempLeft < tempRight && nums[tempLeft] <= target) {
                tempLeft++;
            }
            while (tempLeft < tempRight && nums[tempRight] >= target) {
                tempRight--;
            }
            swap(nums,tempLeft,tempRight);
            tempLeft++;
            tempRight--;
        }
        if (tempLeft == tempRight && nums[tempRight] >= target) {
            tempRight--;
        }
        swap(nums, left, tempRight);
        return tempRight;
    }

    private static void swap(int[] nums, int num1, int num2) {
        int temp = nums[num1];
        nums[num1] = nums[num2];
        nums[num1] = temp;
    }

}
