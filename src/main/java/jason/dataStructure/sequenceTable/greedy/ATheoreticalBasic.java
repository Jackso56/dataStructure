package jason.dataStructure.sequenceTable.greedy;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/23 10:23:04
 **/
@SuppressWarnings({"all"})
public class ATheoreticalBasic {

    /**
     * 贪心算法概述：
     * 「贪心算法」由于适用的问题，每一个步骤只有一种选择，一般而言只需要记录与当前步骤相关的变量的值。
     *  <p>
     *  使用贪心算法的问题需要满足的条件
     *  最优子结构：规模较大的问题的解由规模较小的子问题的解组成，区别于「动态规划」，
     *  可以使用「贪心算法」的问题「规模较大的问题的解」只由其中一个「规模较小的子问题的解」决定；
     *  无后效性：后面阶段的求解不会修改前面阶段已经计算好的结果；
     *  贪心选择性质：从局部最优解可以得到全局最优解。
     *
     * 小结：
     * 「贪心算法」总是做出在当前看来最好的选择就可以完成任务；
     * <p>
     * 解决「贪心算法」几乎没有套路，到底如何贪心，贪什么与我们要解决的问题密切相关。因此刚开始学习「贪心算法」的时候需要学习和模仿，然后才有直觉，猜测一个问题可能需要使用「贪心算法」，进而尝试证明，学会证明。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvosc2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 分发饼干
     * <p>
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * <p>
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvrk1c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int lenG = g.length, lenS = s.length;
        int i = 0, j = 0;
        while (i < lenG && j < lenS) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
