package jason.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/6 16:08:17
 **/
@SuppressWarnings({"all"})
public class EInDepthUnderStandingRecursionThree {



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 从前序与中序遍历序列构造二叉树
     * <p>
     * 本质：设置根节点
     * <p>
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution_01 {

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int preLen = preorder.length, inLen = inorder.length;
            if (preLen != inLen) {
                return null;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inLen; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);
        }

        private TreeNode buildTree(int[] pre, int preLeft, int preRight,
                                   Map<Integer, Integer> in, int inLeft, int inRight) {
            // 边界条件
            if (preLeft > preRight || inLeft > inRight) {
                return null;
            }
            // 获取根节点
            int rootVal = pre[preLeft];
            // 构造根节点
            TreeNode root = new TreeNode(rootVal);
            // 获取中序遍历中的根节点的 索引值
            int rootIndex = in.get(rootVal);
            // 递归构造左子树
            root.left = buildTree(pre, preLeft + 1, rootIndex - inLeft + preLeft,
                    in, inLeft, rootIndex - 1);
            // 递归构造右子树
            root.right = buildTree(pre, rootIndex - inLeft + preLeft + 1, preRight,
                    in, rootIndex + 1, inRight);
            return root;
        }
    }


    /**
     * 从中序与后序遍历序列构造二叉树
     * <p>
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class Solution_02 {

        private int rootIndex;
        private int rootValue;
        private int[] post;
        private Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.post = postorder;
            this.rootIndex = postorder.length - 1;
            for (int i = 0; i < inorder.length; i++) {
                inMap.put(inorder[i], i);
            }
            return resolve(0, inorder.length - 1);
        }

        private TreeNode resolve(int left, int right) {
            if (left > right) {
                return null;
            }
            rootValue = this.post[rootIndex];
            TreeNode root = new TreeNode(rootValue);
            int index = inMap.get(rootValue);
            rootIndex--;
            root.right = resolve(index + 1, right);
            root.left = resolve(left, index - 1);
            return root;
        }

    }


    /**
     * 将有序数组转换为二叉搜索树
     * <p>
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rn4cdv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_03 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return resolve(nums, 0, nums.length - 1);
        }

        private TreeNode resolve(int[] nums, int left, int right) {
            // 边界条件
            if (left > right) {
                return null;
            }
            // 取中点为各个树的根节点
            int mid = (left + right) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            // 递归创建 左右两颗子树
            root.left = resolve(nums, left, mid - 1);
            root.right = resolve(nums, mid + 1, right);
            return root;
        }
    }


    /**
     * 二叉树的最大深度
     * <p>
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     */
    class Solution_04 {
        public int maxDepth(TreeNode root) {
            // 边界条件
            if (root == null) {
                return 0;
            }
            // 递归 左右两颗子树的深度  maxDepth+1
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }


    /**
     * 验证二叉搜索树
     * <p>
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rn14vj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_05 {
        public boolean isValidBST(TreeNode root) {
            return resolve(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean resolve(TreeNode root, long min, long max) {
            // 边界条件  符合二叉搜索树  true
            if (root == null) {
                return true;
            }
            // 边界条件  left.value > root.value || right.value < root.value  不符合二叉搜索树  fasle
            if (root.val >= max || root.val <= min) {
                return false;
            }
            // 递归判断左右子树是否符合二叉搜索树
            return resolve(root.left, min, (long) root.val) && resolve(root.right, (long) root.val, max);
        }
    }


    /**
     * 平衡二叉树
     * <p>
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * <p>
     * 本题中，一棵高度平衡二叉树定义为：
     * <p>
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     */
    class Solution_06 {
        public boolean isBalanced(TreeNode root) {
            return resolve(root) != -1;
        }

        private int resolve(TreeNode root) {
            // 边界条件
            if (root == null) {
                return 0;
            }
            // 递归左、右子树高度  height == -1 终止递归至最外层  return -1
            int left = resolve(root.left);
            if (left == -1) {
                return -1;
            }
            int right = resolve(root.right);
            if (right == -1) {
                return -1;
            }
            // 核心： 高度差小于2 ？ 符合平衡二叉树  高度 +1 : 不符合平衡二叉树  -1
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }

    }


    /**
     * 二叉树中的最大路径和
     * <p>
     * 分两种情况：
     * 1. left - middle - right &nbsp;&nbsp;&nbsp;&nbsp;
     * 2. left - middle  ||  middle - right
     *
     * <p>
     * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * <p>
     * 路径和 是路径中各节点值的总和。
     * <p>
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution_07 {

        private int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            resulove(root);
            return max;
        }

        private int resulove(TreeNode root) {
            // 设置边界条件
            if (root.left == null && root.right == null) {
                max = root.val > max ? root.val : max;
                return root.val;
            }

            // 递归获取左子树的值和右子树的值
            int left = 0;
            int right = 0;
            if (root.left != null) {
                left = resulove(root.left);
            }
            if (root.right != null) {
                right = resulove(root.right);
            }

            // 排除负数的情况
            left = Math.max(left, 0);
            right = Math.max(right, 0);

            // 排除  left-middle-right的情况
            max = left + right + root.val > max ? left + right + root.val : max;

            // 获取  left-middle  ||  middle-right   情况中的较大值
            int tempMax = root.val + Math.max(left, right);
            max = max > tempMax ? max : tempMax;
            return tempMax;
        }
    }


    /**
     * 二叉树的右视图
     * <p>
     * 核心思想：深度优先遍历 + 递归（每一层的变量各自相对独立） +  left-root+right的遍历顺序
     * <p>
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     */
    class Solution_8 {

        private List<Integer> list = new ArrayList<Integer>();

        public List<Integer> rightSideView(TreeNode root) {
            resolve(root, 0);
            return list;
        }

        private void resolve(TreeNode root, int depth) {
            // 边界条件
            if (root == null) {
                return;
            }

            // 按照  right - root - left 的顺序遍历二叉树  把第一个被访问的元素放入list
            // 每一层的第一次访问通过 depth 来标记
            if (depth == list.size()) {
                list.add(root.val);
            }
            depth++;

            // 递归遍历 左、右两颗子树
            resolve(root.right, depth);
            resolve(root.left, depth);
        }
    }

    class Solution {

    }
}
