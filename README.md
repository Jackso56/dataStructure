## 项目简介

> 大二小白自学数据结构与算法 <br>
> 题目均来自leetcode(https://leetcode.cn/)

### 学习内容：六大算法思想

1. 贪心算法
2. 递归算法
3. 枚举算法
4. 回溯算法
5. 分治算法
6. 动态规划
   <br>
   <br>
   <br>

#### 贪心算法（greedy）

1. 可以使用「贪心算法」的问题需要满足的条件
    1. 最优子结构：规模较大的问题的解由规模较小的子问题的解组成，区别于「动态规划」，可以使用「贪心算法」的问题「规模较大的问题的解」只由其中一个「规模较小的子问题的解」决定；
    2. 无后效性：后面阶段的求解不会修改前面阶段已经计算好的结果；
    3. 贪心选择性质：从局部最优解可以得到全局最优解。
2. 小结
    1. 「贪心算法」总是做出在当前看来最好的选择就可以完成任务；
    2. 解决「贪心算法」几乎没有套路，到底如何贪心，贪什么与我们要解决的问题密切相关。因此刚开始学习「贪心算法」的时候需要学习和模仿，然后才有直觉，猜测一个问题可能需要使用「贪心算法」，进而尝试证明，学会证明。
3. 题目:(package:src/main/java/jason/greedy)
    1. ATheoreticalBasic &nbsp;&nbsp;--->&nbsp;&nbsp;贪心算法的理论知识
    2. BChangeProblem&nbsp;&nbsp;--->&nbsp;&nbsp;找零钱问题
    3. CRegionSelectionProblem &nbsp;&nbsp;--->&nbsp;&nbsp;区域选择问题
    4. DJumpProblem &nbsp;&nbsp;--->&nbsp;&nbsp; 跳跃问题
    5. EUsualProblemOfGreed &nbsp;&nbsp;--->&nbsp;&nbsp; 总结与练习--基础题
    6. FProblemOfAdvancedGreed &nbsp;&nbsp;--->&nbsp;&nbsp; 总结与练习--进阶题
