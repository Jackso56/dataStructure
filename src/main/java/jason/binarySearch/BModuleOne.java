package jason.binarySearch;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/13 22:07:06
 **/
@SuppressWarnings({"all"})
public class BModuleOne {


    /**
     * x 的平方根
     * <p>
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * <p>
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xe9cog/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public int mySqrt(int x) {
            if (x < 2) return x;
            int start = 1, end = x;
            while (start <= end) {
                int mid = (end + start) >>> 1;
                if (x / mid < mid) {
                    end = mid - 1;
                } else if (x / mid > mid) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
            // end 更加接近目标值
            return end;
        }
    }


    /**
     * 猜数字大小
     * <p>
     * 猜数字游戏的规则如下：
     * <p>
     * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
     * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
     * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
     * <p>
     * -1：我选出的数字比你猜的数字小 pick < num <br>
     * 1：我选出的数字比你猜的数字大 pick > num<br>
     * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num<br>
     * 返回我选出的数字。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xee4ev/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_02 {
        public int guessNumber(int n) {
            int start = 1, end = n, mid = 0;
            while (start <= end) {
                mid = (start + end) >>> 1;
                if (guess(mid) > 0) {
                    start = mid + 1;
                } else if (guess(mid) < 0) {
                    end = mid - 1;
                } else {
                    return mid;
                }
            }
            return mid;
        }

        private int guess(int num) {
            return 0;
        }
    }


    /**
     * 搜索旋转排序数组
     * <p>
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * <p>
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/binary-search/xeog5j/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution_03 {

        public static void main(String[] args) {
            search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        }

        /**
         * 1.target in [start, mid - 1] <br>
         *  &nbsp;&nbsp;1.mid in right    nums[mid] = Integer.MAX_VALUE  保证可以二分查找  nums[mid] > nums[start] <br>
         * 2.target in [mid + 1, end]<br>
         *  &nbsp;&nbsp;1.mid in left    nums[mid] = Integer.MIN_VALUE  保证可以二分查找   nums[mid] > nums[start] <br>
         * @param nums
         * @param target
         * @return
         */
        public static int search(int[] nums, int target) {
            int length = nums.length;
            if (length < 2) return nums[0] == target ? 0 : -1;
            int start = 0,end = length - 1,mid = 0;
            while(start <= end){
                mid = (end + start) >>> 1;
                if (nums[mid] == target) return mid;

                // target in [start, mid - 1]
                if (target >= nums[0]){
                    // mid in right part    保证  nums[left] < nums[right]   以保证可以二分
                    if (nums[mid] < nums[0]){
                        nums[mid] = Integer.MAX_VALUE;
                    }
                // target in [mid + 1, end]
                } else {
                    // mid in left part     保证  nums[left] < nums[right]    以保证可以二分
                    if (nums[mid] >= nums[0]){
                        nums[mid] = Integer.MIN_VALUE;
                    }
                }

                if (nums[mid] < target){
                    start = mid + 1;
                } else {
                    end  = mid - 1;
                }
            }
            return -1;
        }
    }
}
