package jason.recursion;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/27 15:46:42
 **/
@SuppressWarnings({"all"})
public class Test {

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{7, 5, 6, 4}));
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
        if (nums[middle] <= nums[middle + 1]){
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

}
