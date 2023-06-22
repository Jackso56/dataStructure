package jason.dataStructure.sequenceTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/22 13:04:53
 **/
@SuppressWarnings({"all"})
public class Test {
    public static void main(String[] args) {
        Integer[] arr = {4, 5, 7, 3, 1, 9, 5};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(arr));

    }
}
