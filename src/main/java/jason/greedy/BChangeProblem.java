package jason.greedy;

/**
 * @author Jason Black
 * @version The past cannot be redeemed, the future can be changed.
 * @CreateTime 2023/6/23 10:29:25
 **/
@SuppressWarnings({"all"})
public class BChangeProblem {
    /**
     * 找零钱问题
     *
     * 可以使用「贪心算法」的一类经典问题是找零钱问题。
     *
     * 在生活中，我们找给别人零钱，通常都是按照「先给出尽可能多的面值较大的纸币（硬币），
     * 然后再给出尽可能多的面值第二大的纸币（硬币）」，
     * 直到凑成了我们需要凑出的金额为止，
     * 这样找零钱得到的纸币（硬币）的张数（个数）最少。能够这样做，
     * 与 可选的硬币（纸币）的面值密切相关，大家可以仔细想一想这个问题，
     * 相信会是一个非常不错的思考问题。
     *
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/r2upwr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    /**
     * 找零钱问题
     * <p>
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     * <p>
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * <p>
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * <p>
     * 注意，一开始你手头没有任何零钱。
     * <p>
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     * <p>
     * 思路：
     * 由于顾客只可能给你三个面值的钞票，而且我们一开始没有任何钞票，因此我们拥有的钞票面值只可能是 55 美元，1010 美元和 2020 美元三种。基于此，我们可以进行如下的分类讨论。
     * <p>
     * 55 美元，由于柠檬水的价格也为 55 美元，因此我们直接收下即可。
     * <p>
     * 1010 美元，我们需要找回 55 美元，如果没有 55 美元面值的钞票，则无法正确找零。
     * <p>
     * 2020 美元，我们需要找回 1515 美元，此时有两种组合方式，一种是一张 1010 美元和 55 美元的钞票，一种是 33 张 55 美元的钞票，如果两种组合方式都没有，则无法正确找零。当可以正确找零时，两种找零的方式中我们更倾向于第一种，即如果存在 55 美元和 1010 美元，我们就按第一种方式找零，否则按第二种方式找零，因为需要使用 55 美元的找零场景会比需要使用 1010 美元的找零场景多，我们需要尽可能保留 55 美元的钞票。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/greedy/rvp34i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length <= 0) {
            return false;
        } else {
            int five = 0, ten = 0;
            for (int bill : bills) {
                if (bill == 5) {
                    five++;
                } else if (bill == 10) {
                    if (five == 0) {
                        return false;
                    } else {
                        five--;
                        ten++;
                    }
                } else {
                    if(five > 0 && ten >0){
                        five --;
                        ten --;
                    } else if(five >= 3){
                        five -= 3;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
