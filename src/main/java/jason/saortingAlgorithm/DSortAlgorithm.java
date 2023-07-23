package jason.saortingAlgorithm;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/23 11:46:12
 **/
@SuppressWarnings({"all"})
public class DSortAlgorithm {

    public static void shellSort(int[] arr) {
        // 间隔序列，在希尔排序中我们称之为增量序列
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
            for (int i = gap; i < arr.length; i++) {
                // currentNumber 站起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }

}
