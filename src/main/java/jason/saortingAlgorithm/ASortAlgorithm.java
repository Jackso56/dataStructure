package jason.saortingAlgorithm;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/22 12:20:24
 **/
@SuppressWarnings({"all"})

public class ASortAlgorithm {

    /**
     * 冒泡排序 ：时间复杂度 O(n^2), 空间复杂度 O(1)
     */
    static class BobbleSort {
        public static void main(String[] args) {
            int[] arr = {4, 7, 28, 9, 4, 7, 9, 23};
            bobbleSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        private static void bobbleSort(int[] arr) {
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }

        private static void swap(int[] arr, int i1, int i2) {
            int temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }
    }


    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * <p>
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     */
    class Solution_01 {
        public String minNumber(int[] nums) {
            int len = nums.length;
            String[] temp = new String[nums.length];
            for (int i = 0; i < len; i++) temp[i] = String.valueOf(nums[i]);
            Arrays.sort(temp,(s1,s2)->(s1+s2).compareTo(s2+s1));
            StringBuilder ans = new StringBuilder();
            for (String s : temp) {
                ans.append(s);
            }
            return ans.toString();
        }

    }


    /**
     * 移动0
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    class Solution_02 {
        public void moveZeroes(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len - 1 - i; j++) {
                    if (nums[j] == 0) {
                        swap(nums, j, j + 1);
                    }
                }
            }
        }

        private void swap(int[] arr, int i1, int i2) {
            int temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }

    }
}
