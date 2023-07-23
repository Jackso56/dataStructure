package jason.recursion;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/2 08:51:06
 **/
@SuppressWarnings({"all"})
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 7, 9, 3, 8, 2, 4};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = patition(nums, left, right);
        quickSort(nums, left, middle - 1);
        quickSort(nums, middle + 1, right);
    }

    private static int randomMiddle(int left, int right) {
        return new Random().nextInt(left + (right - left));
    }

    private static int patition(int[] nums, int left, int right) {
//        int index = randomMiddle(left, right);
//        swap(nums, index, left);
        int middle = nums[left];
        int tempLeft = left + 1;
        int tempRight = right;
        while (tempLeft < tempRight) {
            while (tempLeft < right && nums[tempLeft] < middle) {
                tempLeft++;
            }
            while (tempRight > left && nums[tempRight] > middle) {
                tempRight--;
            }
            if (tempLeft < tempRight) {
                swap(nums, tempLeft, tempRight);
                tempLeft++;
                tempRight--;
            }
        }
        if (tempLeft == tempRight && nums[tempRight] > middle) {
            tempRight--;
        }
        swap(nums, left, tempRight);
        return tempRight;
    }

    private static void swap(int[] nums, int num1, int num2) {
        int temp = nums[num1];
        nums[num1] = nums[num2];
        nums[num2] = temp;
    }
}
