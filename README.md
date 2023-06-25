## 项目简介

> 大二小白自学数据结构与算法 <br>
> [题目均来自leetcode](https://leetcode.cn/)

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

> [笔记参考及题目来源地址](https://leetcode.cn/leetbook/read/greedy/r28jah/)

1. 可以使用「贪心算法」的问题需要满足的条件
    1. 最优子结构：规模较大的问题的解由规模较小的子问题的解组成，区别于「动态规划」，可以使用「贪心算法」的问题「规模较大的问题的解」只由其中一个「规模较小的子问题的解」决定；
    2. 无后效性：后面阶段的求解不会修改前面阶段已经计算好的结果；
    3. 贪心选择性质：从局部最优解可以得到全局最优解。
2. 小结
    1. 「贪心算法」总是做出在当前看来最好的选择就可以完成任务；
    2. 解决「贪心算法」几乎没有套路，到底如何贪心，贪什么与我们要解决的问题密切相关。因此刚开始学习「贪心算法」的时候需要学习和模仿，然后才有直觉，猜测一个问题可能需要使用「贪心算法」，进而尝试证明，学会证明。
3. 代码练习: package --> src/main/java/jason/greedy
    1. ATheoreticalBasic &nbsp;&nbsp;--->&nbsp;&nbsp;贪心算法的理论知识
    2. BChangeProblem&nbsp;&nbsp;--->&nbsp;&nbsp;找零钱问题
    3. CRegionSelectionProblem &nbsp;&nbsp;--->&nbsp;&nbsp;区域选择问题
    4. DJumpProblem &nbsp;&nbsp;--->&nbsp;&nbsp; 跳跃问题
    5. EUsualProblemOfGreed &nbsp;&nbsp;--->&nbsp;&nbsp; 总结与练习--基础题
    6. FProblemOfAdvancedGreed &nbsp;&nbsp;--->&nbsp;&nbsp; 总结与练习--进阶题

#### 递归/分治/会溯算法

> [笔记参考及题目来源地址](https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/r24abc/)

##### 分而治之的算法设计思想 

1. 递归 与 分治
    1. 递归是编程技巧，直接体现在代码上 ，即函数自己调用自己；在调用的函数执行完毕之后，程序会回到产生调用的地方，继续做一些其他事情。调用的过程被称作为递归，返回的过程被称作为回溯。
    2. 分治是一种算法设计的思想，将大问题分解成多个小问题，例如归并排序将大问题：「排序整个数组」，分解为小问题：「排序左半和右半」；绝大部分情况下「分治算法」通过「递归」实现。即：子问题的求解通过递归方法实现。
2. 递归函数的设计思想：分而治之（减而治之）
    1. 分而治之（Divide-and-Conquer）的思想分为如下三步：
        1. 拆分：将原问题拆分成若干个子问题；
        2. 解决：解决这些子问题；
        3. 合并：合并子问题的解得到原问题的解。
    2. 「分治思想」的特例是「减治思想（Decrease-and-Conquer）」：每一步将问题转换成为规模更小的子问题。
    3. 「减治思想」思想的典型应用是「二分查找」「选择排序」「插入排序」「快速排序」算法。
    4. 「分治」与「减治思想」的区别如下：
        1. 分治思想：将一个问题拆分成若干个子问题，然后再逐个求解，根据各个子问题得到的结果得到原问题的结果；
        2. 减治思想：在拆分子问题的时候，只将原问题转化成 一个 规模更小的子问题，因此子问题的结果就是上一层原问题的结果，每一步只需要解决一个规模更小的子问题，相比较于「分治思想」而言，它 没有「合并」的过程。
3. 自顶向下地解决问题
    1. 使用「递归」的思想解决问题的方案是：从「结果」推向「源头」，再从「源头」返回「结果」。
4. 递归为什么需要使用栈？
    1. 后拆分的子问题先得到了解决，整个过程恰好符合「后进先出」的规律 ，因此需要借助的数据结构是「栈」。
5. 拆分的时候「先走出去」，合并的时候「再走回来」
    1. 使用「递归」实现的算法需要走完下面两条路径：
        1. 先「自顶向下」拆分问题，直到不能拆分为止；
        2. 再「自底向上」逐层把底层的结果向上汇报，直至得到原问题的解。
    2. 总结
        1. 「分治」是思想，「减治」是分治的特例；
        2. 「递归」是代码表现形式；
        3. 「递归」有先拆分问题的过程，真正解决问题，需要在拆分到底以后，一层一层向上返回。
6. 递归 与 地推
   1. 「自顶向下」与「递归」：「自顶向下」是直接面对我们要解决的问题，逐层拆分，直到不能拆分为止，再按照拆分的顺序的逆序逐层解决，直至原问题得到了解决，这是「递归」。
   2. 「自底向上」与「递推」：如果我们非常清楚一个问题最开始的样子，并且也清楚一个问题是如何从它最开始的样子逐步演变成为我们想要求解的问题的样子，我们就可以通过「递推」的方式，从小规模的问题开始逐步「递推」得到最终要解决的大问题的解。
   3. 个人理解：递归 --> 先拆分问题，再解决问题（存在回溯）；  递推 --> 直接一步步解决问题（不存在回溯）
7. 代码练习：package --> src/main/java/jason/recursion

##### 递归函数的基本结构
1. 基本结构
   1. 写出递归终止条件（易忽略）
      1. 首先写出递归终止条件，也就是先写出不能再拆分的子问题。有些朋友在初学的时候，会由于忘记编写递归终止条件而导致递归调用栈满。
   2. 将原问题拆分成为规模更小的子问题（重点）
      1. 这一步是编写递归函数的关键，如何拆分子问题是我们需要关注的重点。
   3. 将子问题的结果进行合并（难点）
      1. 有一些逻辑恰好是发生在递归函数调用返回以后，我们在这个时机还可以编写一些逻辑，使得我们求解原问题变得更加简单。
2. 写好递归的一些建议：学好「递归」和编写代码一样，需要经历一个先模仿、再学习、然后思考和练习的过程。
   1. 写好「递归」方法不是一朝一夕的事情，和学习所有的算法问题一样，我们需要通过大量的练习来理解写对「递归」方法的技巧和细节；
   2. 「递归」方法与「分治思想」「减治思想」「深度优先遍历」「栈」有着千丝万缕的联系，在编写「递归」方法的同时，要有意识地思考它们之间的关系；
   3. 如果一时半会不能理解「递归」函数的语义，建议在逻辑的关键部分编写打印输出语句，以理解递归函数的调用过程。
3. 小结：学习好递归的重要方法是：先模仿，再练习。其实绝大部分知识的学习都需要反复经历「模仿」和「练习」的步骤。然后才会有自己的思考和总结。

##### 深入理解递归算法 -- part01
1. 使用「归并排序」实现排序数组: 
   1. 「归并排序」将数组不断拆分，直到拆到不能再拆分为止（即数组只有 1 个元素的时候）。由于 1 个元素的数组肯定是有序数组，然后我们「逐层向上」依次合并两个有序数组。通过这样的方式，我们实现了排序的功能。 
   2. 「拆分」与「合并」就通过递归的方式，方便地实现了它们的逻辑。


