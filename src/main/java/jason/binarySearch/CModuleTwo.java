package jason.binarySearch;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/15 17:28:17
 **/
@SuppressWarnings({"all"})
public class CModuleTwo {


    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if (left != nums.length && nums[left] == target) return left;
        return -1;
    }


    /**
     * 第一个错误的版本
     * <p>
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * <p>
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * <p>
     *  
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xepthr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public int firstBadVersion(int n) {
            if (n < 2) return 1;
            int start = 1, end = n, mid = 0;
            while (start < end) {
                mid = (start + end) >>> 1;
                if (!isBadVersion(mid)) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }

        private boolean isBadVersion(int version) {
            return true;
        }
    }


    /**
     * 寻找峰值
     * <p>
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * <p>
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * <p>
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * <p>
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xem7js/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution_02 {

        public static void main(String[] args) {
            findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4});
        }

        public static int findPeakElement(int[] nums) {
            if (nums.length < 2) return 0;
            int start = 0, end = nums.length - 1, mid = 0;
            while (start < end) {
                mid = (start + end) >>> 1;
                if (nums[mid] > nums[mid + 1]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return start;
        }
    }


    /**
     * 寻找旋转排序数组中的最小值
     * <p>
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * <p>
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * <p>
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xeawbd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_03 {
        public int findMin(int[] nums) {
            if (nums.length < 2) return nums[0];
            int start = 0, end = nums.length - 1, mid = 0;
            while (start < end) {
                mid = (start + end) >>> 1;
                if (nums[mid] < nums[end]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return nums[start];
        }
    }



}
