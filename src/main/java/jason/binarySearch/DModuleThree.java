package jason.binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/20 18:29:23
 **/
@SuppressWarnings({"all"})
public class DModuleThree {

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * <p>
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public int[] searchRange(int[] nums, int target) {
            int leftIdx = binarySearch(nums, target, true);
            int rightIdx = binarySearch(nums, target, false) - 1;
            if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
                return new int[]{leftIdx, rightIdx};
            }
            return new int[]{-1, -1};
        }

        public int binarySearch(int[] nums, int target, boolean lower) {
            int left = 0, right = nums.length - 1, ans = nums.length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    right = mid - 1;
                    ans = mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }


    /**
     * 找到 K 个最接近的元素
     * <p>
     * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
     * <p>
     * 整数 a 比整数 b 更接近 x 需要满足：
     * <p>
     * |a - x| < |b - x| 或者 <br>
     * |a - x| == |b - x| 且 a < b
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xeve4m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_02 {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int right = binarySearch(arr, x);
            int left = right - 1;
            while(k-- > 0){
                // left < 0 , right > arr.length - 1  分开写是为了防止数组下标越界
                if (left < 0 ){
                    right ++;
                }else if(right > arr.length - 1) {
                    left--;
                }else if(arr[right] - x < x - arr[left]) {
                    right ++;
                }else {
                    left --;
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int i = left + 1; i < right; i++) {
                list.add(arr[i]);
            }
            return list;
        }

        private int binarySearch(int[] arr, int target) {
            int start = 0, end = arr.length - 1, mid = 0;
            while (start < end) {
                mid = (start + end) >>> 1;
                if (arr[mid] >= target) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return start;
        }
    }




}
