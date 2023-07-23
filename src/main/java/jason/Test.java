package jason;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/7/22 13:17:06
 **/
@SuppressWarnings({"all"})
public class Test {

    public static void main(String[] args) {
        Integer[] arr = {3, 4, 5, 4, 7, 8, 9, 5, 7, 8, 3, 4, 7, 44};
        Arrays.sort(arr, (n1, n2) -> n2 - n1);
        System.out.println(Arrays.toString(arr));
    }
}
