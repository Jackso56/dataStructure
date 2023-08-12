package jason.saortingAlgorithm;

import java.util.*;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/23 11:46:12
 **/
@SuppressWarnings({"all"})
public class DSortAlgorithm {

    /**
     * 希尔排序又称增量排序：时间复杂度 O(n^1.3),空间复杂度 O(1)
     */
    static class HillSort {

        public static void main(String[] args) {
            int[] arr = {5, 2, 3, 4, 5, 7, 4, 5};
            hillSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        public static void hillSort(int[] arr) {
            int length = arr.length, preIndex = 0, currentValue = 0;
            // 设置增量为  2^(1/2)
            for (int i = length >>> 1; i > 0; i >>>= 1) {
                // 开始排序
                for (int j = i; j < length; j++) {
                    // 记录当前值
                    currentValue = arr[j];
                    // 记录前一个元素的下标
                    preIndex = j - i;
                    // 符合条件 preVal 覆盖 currVal, preIndex 后移一个增量
                    while (preIndex >= 0 && currentValue < arr[preIndex]) {
                        arr[preIndex + i] = arr[preIndex];
                        preIndex -= i;
                    }
                    // 找到当前值的位置
                    arr[preIndex + i] = currentValue;
                }
            }
        }

    }


    /**
     * 相对名次
     * <p>
     * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
     * <p>
     * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
     * <p>
     * 名次第 1 的运动员获金牌 "Gold Medal" 。
     * 名次第 2 的运动员获银牌 "Silver Medal" 。
     * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
     * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
     * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/sort-algorithms/et905c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_01 {
        public String[] findRelativeRanks(int[] score) {
            int n = score.length;
            String[] desc = {"Gold Medal", "Silver Medal", "Bronze Medal"};
            int[][] arr = new int[n][2];

            for (int i = 0; i < n; ++i) {
                arr[i][0] = score[i];
                arr[i][1] = i;
            }
            Arrays.sort(arr, (a, b) -> b[0] - a[0]);
            String[] ans = new String[n];
            for (int i = 0; i < n; ++i) {
                if (i >= 3) {
                    ans[arr[i][1]] = Integer.toString(i + 1);
                } else {
                    ans[arr[i][1]] = desc[i];
                }
            }
            return ans;
        }
    }


    /**
     * 前 K 个高频元素
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */

    class Solution_02 {
        public List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> res = new ArrayList();
            // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
            HashMap<Integer,Integer> map = new HashMap();
            for(int num : nums){
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }

            //桶排序
            //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
            List<Integer>[] list = new List[nums.length+1];
            for(int key : map.keySet()){
                // 获取出现的次数作为下标
                int i = map.get(key);
                if(list[i] == null){
                    list[i] = new ArrayList();
                }
                list[i].add(key);
            }

            // 倒序遍历数组获取出现顺序从大到小的排列
            for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
                if(list[i] == null) continue;
                res.addAll(list[i]);
            }
            return res;
        }
    }

}
