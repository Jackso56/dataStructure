package jason.saortingAlgorithm;

import java.util.Arrays;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/29 15:55:05
 **/
@SuppressWarnings({"all"})
public class ESortAlgorithm {

    /**
     * 堆排：时间复杂度 O(nlogn)  空间复杂度 O(1)
     */
    static class HeapSort {

        public static void main(String[] args) {
            int[] arr = {5, 7, 2, 3, 4, 95, 7, 9, 2, 8, 34,};
            heapSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        private static void heapSort(int[] arr) {
            int length = arr.length;
            maxHeap(arr);
            for (int i = length - 1; i > 0; i--) {
                swap(arr, 0, i);
                heapify(arr, 0, i);
            }
        }

        private static void maxHeap(int[] arr) {
            int length = arr.length;
            //  length / 2 - 1  表示最后一个非叶子结点的索引
            for (int i = length / 2 - 1; i >= 0; i--) {
                heapify(arr, i, length);
            }
        }

        private static void heapify(int[] arr, int index, int len) {
            int left = index * 2 + 1;
            int right = left + 1;
            int largest = index;
            // 小根堆实现
            if (left < len && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < len && arr[right] > arr[largest]) {
                largest = right;
            }
            // regin 大根堆实现

//            if (left < len && arr[left] < arr[largest]) {
//                largest = left;
//            }
//            if (right < len && arr[right] < arr[largest]) {
//                largest = right;
//            }

            // reginend
            if (index != largest) {
                swap(arr, index, largest);
                heapify(arr, largest, len);
            }
        }

        private static void swap(int[] arr, int left, int right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }

    /**
     * 最小的k个数
     * <p>
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    class Solution_01 {
        public int[] getLeastNumbers(int[] arr, int k) {
            heapSort(arr);
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) {
                ans[i] = arr[i];
            }
            return ans;
        }

        private void heapSort(int[] arr) {
            int length = arr.length;
            maxHeap(arr);
            for (int i = length - 1; i > 0; i--) {
                swap(arr, 0, i);
                heapify(arr, 0, i);
            }
        }

        private void maxHeap(int[] arr) {
            int length = arr.length;
            //  length / 2 - 1  表示最后一个非叶子结点的索引
            for (int i = length / 2 - 1; i >= 0; i--) {
                heapify(arr, i, length);
            }
        }

        private void heapify(int[] arr, int index, int len) {
            int left = index * 2 + 1;
            int right = left + 1;
            int largest = index;
            // 小根堆实现
            if (left < len && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < len && arr[right] > arr[largest]) {
                largest = right;
            }
            // regin 大根堆实现

//            if (left < len && arr[left] < arr[largest]) {
//                largest = left;
//            }
//            if (right < len && arr[right] < arr[largest]) {
//                largest = right;
//            }

            // reginend
            if (index != largest) {
                swap(arr, index, largest);
                heapify(arr, largest, len);
            }
        }

        private void swap(int[] arr, int left, int right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }

}
