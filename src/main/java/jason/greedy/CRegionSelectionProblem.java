package jason.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/23 10:33:12
 **/
@SuppressWarnings({"all"})
public class CRegionSelectionProblem {

    /**
     * 区域选择问题
     *
     * 有一类使用「贪心算法」解决的问题称为「活动选择问题」，
     * 解决这一类问题的直觉是「优先选择活动最早的活动」。
     * 我们下面给出的三道例题都给出了对这一类问题的直觉的证明。
     * 如果还想了解「活动选择问题」的详细讨论，
     * 可以查看《算法导论》第 16.1 节「活动选择问题」的叙述。
     *
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/r2tens/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    /**
     * 无重叠区间
     * <p>
     * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     * <p>
     *  
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvvp3t/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[1] - arr2[1];
            }
        });
        int len = intervals.length;
        int answer = 1;
        int right = intervals[0][1];
        for (int i = 1; i < len; i++) {
            // 使上一区间的右边界小于下一区间的左边界
            if (intervals[i][0] >= right) {
                answer++;
                right = intervals[i][1];
            }
        }
        return len - answer;
    }


    /**
     * 用最少数量的箭引爆气球
     * <p>
     * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
     * <p>
     * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
     * <p>
     * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvwe8r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        // judge empty
        if (points.length == 0 || points == null) {
            return 0;
        }
        // sort array by right node
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int answer = 1;
        long right = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // right[0] > right  answer++
            if (points[i][0] > right) {
                answer++;
                right = points[i][1];
            }
        }
        return answer;
    }


    /**
     * 合并区间
     * <p>
     * 以数组 intervals 表示若干个区间的集合，
     * 其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，
     * 该数组需恰好覆盖输入中的所有区间 。
     *<p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvdxus/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals == null) {
            return null;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // left 左节点    right 右节点
        int left, right;
        // 存储答案
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            left = intervals[i][0];
            right = intervals[i][1];
            // list 为空  ||  没有重叠的情况
            if(list.size() == 0 || list.get(list.size()-1)[1] < left){
                list.add(new int[]{left, right});
            } else {
                // 有重叠的情况
                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
