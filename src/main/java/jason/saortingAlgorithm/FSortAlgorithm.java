package jason.saortingAlgorithm;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/8/1 16:10:32
 **/
@SuppressWarnings({"all"})
public class FSortAlgorithm {

    /**
     * 快排：时间复杂度 O(nlogn) &nbsp; 空间复杂度 O(logn)
     */
    static class QuickSort {

        public static void main(String[] args) {
            int[] arr = {7, 5, 2, 3, 9, 8, 4, 5, 3};
            quickSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        public static void quickSort(int[] arr) {
            quickSort(arr, 0, arr.length - 1);
        }

        private static void quickSort(int[] arr, int start, int end) {
            if (start >= end) {
                return;
            }
            // 以 arr[middle] 为界限将数组对半分
            int middle = partition(arr, start, end);
            // 数组左半边排序
            quickSort(arr, start, middle - 1);
            // 数组右半边排序
            quickSort(arr, middle + 1, end);
        }

        private static int partition(int[] arr, int start, int end) {
            int target = arr[start];
            int left = start + 1;
            int right = end;
            while (left < right) {
                while (left < right && arr[left] <= target) left++;
                while (left < right && arr[right] >= target) right--;
                // arr[left] > arr[right] 时交换其值
                if (left < right) {
                    swap(arr, left, right);
                    left++;
                    right--;
                }
            }
            if (left == right && target < arr[right]) {
                right--;
            }
            // 将边界值放在中间
            swap(arr, start, right);
            return right;
        }

        private static void swap(int[] arr, int l, int r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }

    }


    /**
     * 多数元素
     * <p>
     * 算法思路：思维逆转
     * <p>
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/sort-algorithms/et2gzs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }

    }
}
