package jason.recursion;

import java.util.Arrays;

import static com.sun.webkit.perf.WCGraphicsPerfLogger.log;


/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/25 14:27:04
 **/

@SuppressWarnings({"all"})
public class BInDepthUnderstandingRecursion {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[]{8, 7, 4, 1, 8, 3, 2, 7, 4, 9, 3, 2, 4, 9})));
    }

    /**
     * 归并排序 --> 分治，递归的经典案例
     * <p></>
     * partition() --> 实现分治  &nbsp;&nbsp; merge() --> 实现了归并
     *
     * @param nums
     * @return
     */
    public static int[] mergeSort(int[] nums) {
        int len = nums.length;
        int[] tempNums = new int[len];
        partition(nums, 0, len - 1, tempNums);
        return nums;
    }

    private static void partition(int[] nums, int left, int right, int[] tempNums) {
        System.out.println("X拆分子问题" + "[" + left + "," + right + "]");
        // 边界条件
        if (left == right) {
            System.out.println("Y解决子问题" + "[" + left + "," + right + "]");
            return;
        }
        int middle = (left + right) >> 1;
        // 开始分治  -->  大问题拆分成一个个胡小问题
        partition(nums, left, middle, tempNums);
        partition(nums, middle + 1, right, tempNums);
        // 开始归并  -->  递归求解个个小问题
        merge(nums, left, middle, right, tempNums);
        System.out.println("Y解决子问题" + "[" + left + "," + right + "]");
    }

    private static void merge(int[] nums, int left, int middle, int right, int[] tempNums) {
        // 复制数组(方便下面的操作)
        for (int i = left; i <= right; i++) {
            tempNums[i] = nums[i];
        }
        // index for nums;   tempLeft, tempRight for tempNums
        int index = left, tempLeft = left, tempRight = middle + 1;
        // 未超出边界的情况下，把较小的数放在数组的前面（数值来自tempNums），其中 tempLeft <= middle 可以保持数组的稳定性
        while (tempLeft <= middle && tempRight <= right) {
            if (tempNums[tempLeft] <= tempNums[tempRight]) {
                nums[index++] = tempNums[tempLeft++];
            } else {
                nums[index++] = tempNums[tempRight++];
            }
        }
        // 当有一边已超出边界，另一边直接将数值直接复制到原数组中即可
        while (tempLeft <= middle) {
            nums[index++] = tempNums[tempLeft++];
        }
        while (tempRight <= right) {
            nums[index++] = tempNums[tempRight++];
        }
    }
}
