package jason.recursion;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/27 13:20:16
 **/
@SuppressWarnings({"all"})
public class CInDepthUnderStandingRecursion {

    public static void main(String[] args) {
        int result = reversePairs(new int[]{7, 5, 6, 4});
        System.out.println(result);
    }


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

    public static int reversePairs(int[] nums) {
        int len = nums.length;
        // 当数组长度小于2时，会出现栈溢出的情况
        if (len < 2) {
            return 0;
        }
        int[] tempNums = new int[len];
        return pairs(nums, 0, len - 1, tempNums);
    }

    private static int pairs(int[] nums, int start, int end, int[] tempNums) {
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

    private static int partition(int[] nums, int start, int middle, int end, int[] tempNums) {
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
