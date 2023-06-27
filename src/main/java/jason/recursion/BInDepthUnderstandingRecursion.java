package jason.recursion;

import java.util.Arrays;


/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/25 14:27:04
 **/

@SuppressWarnings({"all"})
public class BInDepthUnderstandingRecursion {


    public static void main(String[] args) {
        int[] array = {1, 8, 4, 7, 8, 2, 34, 8};
        System.out.println(Arrays.toString(array));
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 分治，递归的经典案例 -- 归并排序
     * <p>
     * partition() --> 实现分治  &nbsp;&nbsp; merge() --> 实现了归并(核心)
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
        int middle = left + (left + right) >> 1;
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


    /**
     * 分治，递归的经典案例 -- 快排
     * <p>
     * partition() --> 实现分治(核心)  &nbsp;&nbsp; quickSort() --> 实现了归并
     * <p>
     *
     * @param array
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        // 边界条件
        if (left >= right) {
            return;
        }
        // 获取中间下标
        int middle = partition(array, left, right);
        // 左边的快排
        quickSort(array, left, middle - 1);
        // 右边快排
        quickSort(array, middle + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        // 基数
        int middle = array[left];
        // 左边界
        int tempLeft = left + 1;
        // 右边界
        int tempRight = right;
        // 左右边界为相遇
        while (tempLeft < tempRight) {
            //  tempLeft++  &&  tempRight++   可能导致 tempLeft >= tempRight
            // 左右边界未相遇 && 当前值 < 基值
            while (tempLeft < tempRight && array[tempLeft] <= middle) {
                tempLeft++;
            }
            // 左右边界未相遇 && 当前值 > 基值
            while (tempLeft < tempRight && array[tempRight] >= middle) {
                tempRight--;
            }
            // 将左右区间值进行区分
            if (tempLeft < tempRight) {
                swap(array, tempLeft, tempRight);
                tempLeft++;
                tempRight--;
            }
        }
        // 将边界值放到中间
        if (tempLeft == tempRight && array[tempRight] > middle) {
            tempRight--;
        }
        // 将基值放在中间
        swap(array, left, tempRight);
        // 返回基值下标
        return tempRight;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }


}
