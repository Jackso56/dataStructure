package jason.saortingAlgorithm;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/8/1 17:03:51
 **/
@SuppressWarnings({"all"})
public class GSortAlgorithm {

    /**
     * 归并排序：时间复杂度 O(nlogn) 空间复杂度 O(n)
     */
    static class MergeSort {
        public static int[] mergeSort(int[] nums) {
            int len = nums.length;
            int[] tempNums = new int[len];
            partition(nums, 0, len - 1, tempNums);
            return nums;
        }

        private static void partition(int[] nums, int left, int right, int[] tempNums) {
            // 边界条件
            if (left == right) {
                return;
            }
            int middle = left + (left + right) >> 1;
            // 开始分治  -->  大问题拆分成一个个胡小问题
            partition(nums, left, middle, tempNums);
            partition(nums, middle + 1, right, tempNums);
            // 开始归并  -->  递归求解个个小问题
            merge(nums, left, middle, right, tempNums);
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


    /**
     * 合并排序的数组
     * <p>
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * <p>
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/sort-algorithms/osomav/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public void merge(int[] A, int m, int[] B, int n) {
            // 由后往前遍历
            while (m > 0 && n > 0) {
                A[m + n - 1] = A[m - 1] >= B[n - 1] ? A[--m] : B[n - 1];
            }
            // 特殊情况
            while (--n >= 0) {
                A[n] = B[n];
            }
        }
    }

}
