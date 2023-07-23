package jason.binarySearch;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/13 21:41:43
 **/
@SuppressWarnings({"all"})
public class AFirstBinarySearch {


    /**
     * 二分查找
     * <p>
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xexoac/
     * 来源：力扣（LeetCode）
     */
    class Solution {
        public int search(int[] nums, int target) {
            if (nums.length < 1) return -1;
            int start = 0, end = nums.length - 1;
            while (start <= end) {
                int mid = (start + end) >>> 1;
                if (nums[mid] > target) {
                    end = mid - 1;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
    }
}
