package jason.linkAbout;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/8/10 18:26:55
 **/
@SuppressWarnings({"all"})
public class ALinkQuestion {

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode() {
        }
    }

    /**
     * 两数相加 |
     * <p>
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    static class SolutionOne {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode preHead = new ListNode(0);
            ListNode current = preHead;
            int sum = 0, remainder = 0, v1 = 0, v2 = 0;
            while (l1 != null || l2 != null) {
                v1 = l1 == null ? 0 : l1.val;
                v2 = l2 == null ? 0 : l2.val;
                sum = v1 + v2 + remainder;
                // 取整进位
                remainder = sum / 10;
                // 取余填值
                sum = sum % 10;
                current.next = new ListNode(sum);
                current = current.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (remainder > 0) {
                current.next = new ListNode(1);
            }
            return preHead.next;
        }
    }

    /**
     * 删除链表的倒数第 N 个结点
     * <p>
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    static class SolutionTwo {
        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode temp = head;
            for (int i = 0; i < 4; i++) {
                head.next = new ListNode(i + 2);
                head = head.next;
            }
            removeNthFromEnd(temp, 2);
        }

        /**
         * 双指针：指针相距 n 个节点
         *
         * @param head 头结点
         * @param n    删除节点的序号
         * @return 头结点
         */
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode pre = new ListNode(0);
            pre.next = head;
            ListNode start = pre, end = pre;
            while (n != 0) {
                start = start.next;
                n--;
            }
            while (start.next != null) {
                start = start.next;
                end = end.next;
            }
            end.next = end.next.next;
            return pre.next;
        }
    }

    /**
     * 旋转链表
     * <p>
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    static class SolutionThree {

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode temp = head;
            for (int i = 0; i < 4; i++) {
                head.next = new ListNode(i + 2);
                head = head.next;
            }
            rotateRight(temp, 2);
        }

        /**
         * 双指针：pre,curr --> tail,head
         *
         * @param head
         * @param k
         * @return
         */
        public static ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return head;
            }

            // 计算链表中节点个数
            int len = calculateLen(head);
            k = k % len;

            // 慢指针初始指向头节点
            ListNode slow = head;
            // 快指针初始指向头节点
            ListNode fast = head;

            // 快指针先向前移动k步
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            // 快慢指针同时向前移动，直到快指针指向的节点的
            // 下一个节点为null
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }

            // 快指针此时在链表末尾
            // 然后其指向的节点的后继指针指向头节点
            // 这时链表首尾相连成环
            fast.next = head;
            // 新的头节点是慢指针所指节点的下一个节点
            head = slow.next;
            // 慢指针所指节点的的后继指针指向null
            // 断开环
            slow.next = null;
            return head;
        }

        private static int calculateLen(ListNode head) {
            int len = 0;
            while (head != null) {
                head = head.next;
                len++;
            }
            return len;
        }
    }

    /**
     * 删除排序链表中的重复元素 II
     * <p>
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
     */
    static class SolutionFour {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return head;
            }
            // 添加哨兵
            ListNode orgin = new ListNode(0, head);
            ListNode curr = orgin;
            while (curr.next != null && curr.next.next != null) {
                if (curr.next.val == curr.next.next.val) {
                    int sameValue = curr.next.val;
                    while (curr.next != null && curr.next.val == sameValue) {
                        curr.next = curr.next.next;
                    }
                } else {
                    curr = curr.next;
                }
            }
            return orgin.next;
        }
    }

    /**
     * 分隔链表
     * <p>
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * <p>
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     */
    static class SolutionFive {
        public static void main(String[] args) {
            ListNode n1 = new ListNode(2);
            ListNode n2 = new ListNode(5, n1);
            ListNode n3 = new ListNode(2, n2);
            ListNode n4 = new ListNode(3, n3);
            ListNode n5 = new ListNode(4, n4);
            ListNode n6 = new ListNode(1, n5);
            partition(n6, 3);
        }

        /**
         * 双指针：先分链，再合并
         *
         * @param head
         * @param x
         * @return
         */
        public static ListNode partition(ListNode head, int x) {
            if (head == null) {
                return head;
            }
            ListNode lt = new ListNode(0);
            ListNode gt = new ListNode(0);
            ListNode l = lt, g = gt;
            while (head != null) {
                if (head.val < x) {
                    // 注意需要通过创建节点的方式来指向，反之会出现循环指向的问题
                    lt.next = new ListNode(head.val);
                    lt = lt.next;
                } else {
                    gt.next = new ListNode(head.val);
                    gt = gt.next;
                }
                head = head.next;
            }
            lt.next = g.next;
            return l.next;
        }
    }


    /**
     * 奇偶链表
     * <p>
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * <p>
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     * <p>
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * <p>
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */
    static class SolutionSix {
        public static void main(String[] args) {
            ListNode n5 = new ListNode(5);
            ListNode n4 = new ListNode(4, n5);
            ListNode n3 = new ListNode(3, n4);
            ListNode n2 = new ListNode(2, n3);
            ListNode n1 = new ListNode(1, n2);
            oddEvenList(n1);
        }

        public static ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode temp = head.next;
            ListNode even = head.next; // 偶数
            ListNode odd = head; // 奇数
            while (even.next != null && odd.next != null) {
                if (odd.next.next != null) {
                    odd.next = odd.next.next;
                    odd = odd.next;
                }
                if (even.next.next != null) {
                    even.next = even.next.next;
                    even = even.next;
                }
            }
            even.next = null;
            odd.next = temp;
            return head;
        }
    }

    /**
     * 排序链表
     * <p>
     * 算法实现：归并排序
     *
     * <p>
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     */
    static class Solutionseven {
        public static ListNode sortList(ListNode head) {
            return mergeSort(head);
        }

        public static ListNode getMiddle(ListNode head) {
            if (head == null) {
                return head;
            }
            // slow fast 的起点不同，可以适应偶数个节点的情况
            ListNode slow = head;
            ListNode fast = head.next;
            // 奇数个节点时 fast == null   偶数个节点时 fast.next == null
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public static ListNode mergeSort(ListNode head) {
            // 一个节点无需排序
            if (head == null || head.next == null) {
                return head;
            }
            ListNode mid = getMiddle(head);
            ListNode next = mid.next;
            // 切割链表，分别排序
            mid.next = null;
            ListNode left = mergeSort(head);
            ListNode right = mergeSort(next);
            return merge(left, right);
        }

        public static ListNode merge(ListNode start, ListNode end) {
            ListNode head = new ListNode(0);
            ListNode temp = head;
            while (start != null && end != null) {
                if (start.val < end.val) {
                    temp.next = start;
                    start = start.next;
                } else {
                    temp.next = end;
                    end = end.next;
                }
                temp = temp.next;
            }
            // 链表的特性
            if (start != null) {
                temp.next = start;
            }
            if (end != null) {
                temp.next = end;
            }
            return head.next;
        }
    }

    /**
     * 链表求和
     * <p>
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * <p>
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * <p>
     * 编写函数对这两个整数求和，并用链表形式返回结果。
     */
    static class SolutionEight {
        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode preHead = new ListNode(0);
            ListNode current = preHead;
            int sum = 0, remainder = 0, v1 = 0, v2 = 0;
            while (l1 != null || l2 != null) {
                v1 = l1 == null ? 0 : l1.val;
                v2 = l2 == null ? 0 : l2.val;
                sum = v1 + v2 + remainder;
                // 取整进位
                remainder = sum / 10;
                // 取余填值
                sum = sum % 10;
                current.next = new ListNode(sum);
                current = current.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (remainder > 0) {
                current.next = new ListNode(1);
            }
            return preHead.next;
        }
    }

    /**
     * 重排链表
     * <p>
     * 算法实现：找中点，反转后半部分链表，以”v形“重排链表
     * <p>
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * <p>
     * L0 → L1 → … → Ln-1 → Ln
     * 请将其重新排列后变为：
     * <p>
     * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
     * <p>
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    class SolutionNine {
        public void reorderList(ListNode head) {
            if (head.next == null || head.next.next == null) {
                return;
            }
            // 1. 找到链表中点
            ListNode mid = getMiddle(head);
            // 2. 反转尾部链表
            ListNode cur1 = mid.next;
            mid.next = null;
            cur1 = reverse(cur1);
            // 3. 合并
            ListNode cur = head;
            merge(cur, cur1);
        }

        private void merge(ListNode cur, ListNode cur1) {
            ListNode l1;
            ListNode l2;
            // 这样奇数个和偶数个节点的情况都符合
            while (cur != null && cur1 != null) {
                l1 = cur.next;
                l2 = cur1.next;

                cur.next = cur1;
                cur = l1;

                cur1.next = l1;
                cur1 = l2;
            }
        }

        private ListNode reverse(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            return pre;
        }

        private ListNode getMiddle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }

    /**
     * 两数相加 ||
     *
     * 算法实现：反转链表，两数相加 |
     * <p>
     * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * <p>
     * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
     */
    class SolutionTen {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return ans(l1, l2);
        }

        public ListNode ans(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            ListNode tempNode = head;
            l1 = reverse(l1);
            l2 = reverse(l2);
            int sum = 0, tempNumber = 0, v1 = 0, v2 = 0;
            while (l1 != null || l2 != null) {
                // 空值填0
                v1 = l1 == null ? 0 : l1.val;
                v2 = l2 == null ? 0 : l2.val;

                sum = v1 + v2 + tempNumber;
                tempNumber = sum / 10;

                head.next = new ListNode(sum % 10);
                head = head.next;

                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (tempNumber > 0) {
                head.next = new ListNode(1);
            }
            return reverse(tempNode.next);
        }

        public ListNode reverse(ListNode head) {
            ListNode pre = null, cur = head, temp = head;
            while (cur != null) {
                temp = temp.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            return pre;
        }
    }
}




















