package jason.recursion;


/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/5 14:01:11
 **/
@SuppressWarnings({"all"})
public class DInDepthUnderStandingRecursionTwo {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 合并两个有序数组
     * <p>
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 来源：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rnkn82/
     */
    class Solution_01 {

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            if (list1.val < list2.val) {
                // 递归  l1 被选出，谁小谁在前面
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                // 递归  l2 被选出，谁小谁在前面
                list2.next = mergeTwoLists(list2.next, list1);
                return list2;
            }
        }
    }


    /**
     * 翻转链表
     * <p>
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    static class Solution_02 {

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            ListNode nextNode = head.next;
            // 递归至末尾
            ListNode newNode = reverseList(nextNode);
            // 归并至开头
            nextNode.next = head;
            head.next = null;
            return newNode;
        }
    }


    /**
     * 移除链表元素
     * <p>
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     */
    class Solution_03 {

        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return head;
            } else {
                head.next = removeElements(head.next, val);
                if (head.val == val) {
                    head = head.next;
                }
                return head;
            }
        }
    }


    /**
     * 两两交换链表中的节点
     * <p>
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     */
    class Solution_04 {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            } else {
                ListNode nextOne = head.next;
                ListNode nextTwo = nextOne.next;
                nextOne.next = head;
                head.next = swapPairs(nextTwo);
                return nextOne;
            }
        }

    }


    /**
     * 重排链表
     * <p>
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * <p>
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     * <p>
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rn8qg1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_05 {

        /**
         * 链表的中间结点
         * <p>
         * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
         * <p>
         * 如果有两个中间结点，则返回第二个中间结点。
         */
        class Solution_01 {
            public ListNode middleNode(ListNode head) {
                if (head.next == null) {
                    return head;
                }
                ListNode slow = head;
                ListNode fast = head.next;
                return getMiddle(slow, fast);
            }

            private ListNode getMiddle(ListNode slow, ListNode fast) {
                while (fast.next != null && fast.next.next != null) {
                    slow = slow.next;
                    fast = fast.next.next;
                }
                return slow.next;
            }

        }

        /**
         * 反转链表
         * <p>
         * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
         */
        class Solution_2 {
            public ListNode reverseList(ListNode head) {
                if (head == null || head.next == null) {
                    return head;
                }
                ListNode nextNode = head.next;
                ListNode newNode = reverseList(nextNode);
                nextNode.next = head;
                head.next = null;
                return newNode;
            }
        }


        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            ListNode mid = getMiddle(head);
            ListNode node1 = head;
            ListNode node2 = mid.next;
            mid.next = null;
            node2 = reverseLinkList(node2);
            mergeListList(node1, node2);
        }

        private ListNode getMiddle(ListNode head) {
            if (head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.next;
        }

        private ListNode reverseLinkList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode nextNode = head.next;
            ListNode newNode = reverseLinkList(nextNode);
            nextNode.next = head;
            head.next = null;
            return newNode;
        }

        private void mergeListList(ListNode node1, ListNode node2) {
            ListNode temp1 = null;
            ListNode temp2 = null;
            while (node1 != null && node2 != null) {
                temp1 = node1.next;
                temp2 = node2.next;
                node1.next = node2;
                node1 = temp1;
                node2.next = node1;
                node2 = temp2;
            }
        }


    }


    /**
     * 反转链表 II
     * <p>
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/recursion-and-divide-and-conquer/rn3tk7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution_06 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;

            ListNode pre = dummyNode;
            // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
            // 建议写在 for 循环里，语义清晰
            for (int i = 0; i < left - 1; i++) {
                pre = pre.next;
            }

            // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
            ListNode rightNode = pre;
            for (int i = 0; i < right - left + 1; i++) {
                rightNode = rightNode.next;
            }

            // 第 3 步：切断出一个子链表（截取链表）
            ListNode leftNode = pre.next;
            ListNode curr = rightNode.next;

            // 注意：切断链接
            pre.next = null;
            rightNode.next = null;

            // 第 4 步：同第 206 题，反转链表的子区间
            reverseLinkList(leftNode);

            // 第 5 步：接回到原来的链表中
            pre.next = rightNode;
            leftNode.next = curr;
            return dummyNode.next;
        }

        private ListNode reverseLinkList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode nextNode = head.next;
            ListNode newNode = reverseLinkList(nextNode);
            nextNode.next = head;
            head.next = null;
            return newNode;
        }
    }
}
