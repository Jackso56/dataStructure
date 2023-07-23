package jason.recursion;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/25 08:10:13
 **/
@SuppressWarnings({"all"})
public class APractice {


    /**
     * Pow(x, n)
     * <p>
     * 核心：快速幂 + 递归
     *
     * <p>
     * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        // 此处需要使用 long 数据类型  double 可能会溢出
        long count = n;
        return n >= 0 ? quickPow(x, count) : 1.0 / quickPow(x, -count);
    }

    private static double quickPow(double x, long count) {
        if (count == 0) {
            return 1.0;
        }
        System.out.println(count / 2);
        double result = quickPow(x, count / 2);
        System.out.println(result);
        return count % 2 == 0 ? result * result : result * result * x;
    }


    /**
     * 不用加减乘除做加法
     * <p>
     * 基础知识：
     * 1. a^b  得到无进位和
     * 2. (a&b)<<1  得到进位
     * 3. (a^b) + ((a&b)<<1)  即为最终答案
     * <p>
     * <p>
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        // 边界条件
        if (b == 0) {
            return a;
        }
        // a ^ 0 == a
        return add(a ^ b, (a & b) << 1);
    }


    /**
     * 递归乘法
     * <p></>
     * 核心：第 0为 1 时 --> answer = max << 0; 第 i为 1 时 --> answer = max << i
     *
     * <p>
     * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     * <p></>
     *
     * @param A
     * @param B
     * @return
     */
    public static int multiply(int A, int B) {
        int max = A > B ? A : B;
        int min = max == A ? B : A;
        return recursionMultiply(min, max, 0);
    }

    private static int recursionMultiply(int min, int max, int index) {
        int answer = 0;
        if (min >> index == 0) {
            return 0;
        }
        if (((min >> index) & 1) == 1) {
            answer = max << index;
        }
        return answer + recursionMultiply(min, max, index++);
    }


}
