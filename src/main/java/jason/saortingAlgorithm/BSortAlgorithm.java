package jason.saortingAlgorithm;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/22 13:23:34
 **/
@SuppressWarnings({"all"})
public class BSortAlgorithm {

    /**
     * 选择排序：时间复杂度 O(n^2), 空间复杂度 O(1)
     */
    static class SelectSort {

        public static void main(String[] args) {
            int[] arr = {5, 8, 3, 4, 7, 5, 8, 9, 3};
            selectSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        private static void selectSort(int[] arr) {
            int len = arr.length, minIndex = 0;
            for (int i = 0; i < len; i++) {
                // 注意需要初始化
                minIndex = i;
                for (int j = i; j < len; j++) {
                    if (arr[minIndex] > arr[j]) {
                        minIndex = j;
                    }
                }
                swap(arr, i, minIndex);
            }
        }

        private static void swap(int[] nums, int i1, int i2) {
            int temp = nums[i1];
            nums[i1] = nums[i2];
            nums[i2] = temp;
        }
    }


    /**
     * 排序数组
     * <p>
     * 给你一个整数数组 nums，请你将该数组升序排列。
     */
    class Solution {
        public int[] sortArray(int[] nums) {
            int len = nums.length;
            Integer[] ans = new Integer[len];
            for (int i = 0; i < len; i++) {
                ans[i] = nums[i];
            }
            Arrays.sort(ans, (n1, n2) -> n1 - n2);
            for (int i = 0; i < len; i++) {
                nums[i] = ans[i];
            }
            return nums;
        }
    }
}
