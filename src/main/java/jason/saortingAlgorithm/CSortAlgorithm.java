package jason.saortingAlgorithm;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/23 10:36:00
 **/
@SuppressWarnings({"all"})
public class CSortAlgorithm {

    // regin
    // 小结：冒泡排序和插入排序具有稳定性，选择排序的交换次数最小
    // regin

    /**
     * 插入排序：时间复杂度 O(n^2),空间复杂度 O(1)
     * <p>
     * 冒泡排序 & 插入排序 对比：往后冒泡，往前插入
     */
    static class InsertSort {

        public static void main(String[] args) {
            int[] arr = {7, 5, 3, 4, 6, 5, 8, 7, 3, 4, 5, 6};
            insertSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        public static void insertSort(int[] arr) {
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                for (int j = i; j >= 1; j--) {
                    if (arr[j] < arr[j - 1]) swap(arr, j, j - 1);
                }
            }
        }

        private static void swap(int[] arr, int i1, int i2) {
            int temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }
    }


    /**
     * 对链表进行插入排序
     * <p>
     * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
     * <p>
     * 插入排序 算法的步骤:
     * <p>
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
     * <p>
     * 对链表进行插入排序。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/insertion-sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution {

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

        public ListNode insertionSortList(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode tempHead = new ListNode(Integer.MIN_VALUE), pre = head, lastIndex = head, curr = head.next;
            tempHead.next = head;
            while (curr != null) {
                if (curr.val >= lastIndex.val) {
                    lastIndex = lastIndex.next;
                } else {
                    pre = tempHead;
                    // pre.val <= curr.val  这种方式只能操作 节点值 >= 当前节点值 的节点
                    // pre.next.val <= curr.val  这种方式能操作 节点值 < 当前节点值 的节点
                    while (pre.next.val <= curr.val) {
                        pre = pre.next;
                    }
                    lastIndex.next = curr.next;
                    curr.next = pre.next;
                    pre.next = curr;
                }
                curr = lastIndex.next;
            }
            return tempHead.next;
        }
    }

}
