import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] agrs) {
        //第1题
        /*int[] s = twoSum(new int[]{2, 7, 11, 15}, 9);
        if (s.length > 0)
            System.out.println(s[0] + "," + s[1]);*/

        //第3题
        //System.out.println( lengthOfLongestSubstring("abcabcbb"));

        //第8题
        //System.out.println(myAtoi("    10522545459"));
    }

    /**
     * @param nums
     * @param target
     * @return
     * @NO:1
     * @problem Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution.
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }


    /**
     * @param c
     * @return
     * @NO:3
     * @problem Given a string, find the length of the longest substring without repeating characters.
     * Examples:
     * <p>
     * Given "abcabcbb", the answer is "abc", which the length is 3.
     * <p>
     * Given "bbbbb", the answer is "b", with the length of 1.
     * <p>
     * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    public static int lengthOfLongestSubstring(String c) {
        int[] pos = new int[128];
        for (int i = 0; i < 128; i++) {
            pos[i] = -1;
        }
        int max = 1;
        int start = 0;
        int len = 1;
        if (c.length() == 0)
            return 0;
        char[] s = c.toCharArray();
        pos[s[0]] = 0;
        for (int i = 1; i < s.length; i++) {
            if (pos[s[i]] < 0) {
                pos[s[i]] = i;
                len++;
                if (len > max) {
                    max = len;
                }
            } else {
                int p = pos[s[i]];
                for (int j = start; j < p; j++) {
                    pos[s[j]] = -1;
                }
                pos[s[i]] = i;
                start = p + 1;
                len = i - start + 1;
                if (len > max) {
                    max = len;
                }
            }
        }
        return max;
    }


    /**
     * @param str
     * @return
     * @NO:8
     * @problem: String to Integer (atoi)  QuestionEditorial Solution  My Submissions
     * Total Accepted: 122367
     * Total Submissions: 890569
     * Difficulty: Easy
     * Implement atoi to convert a string to an integer.
     * <p>
     * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
     * <p>
     * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
     */
    public static int myAtoi(String str) {
        if (str.length() == 0 || str == "")
            return 0;
        char[] c = str.toCharArray();

        char[] f = new char[c.length + 1];
        int flag = 1;
        int flag1 = 1;
        int index = 0;
        while (index < c.length && c[index] == ' ') {
            index++;
        }
        for (int i = index; i < c.length; i++) {
            if (c[i] >= '0' && c[i] <= '9' || c[i] == '+' || c[i] == '-') {
                int j = i - 1;
                f[0] = '+';
                if (c[i] == '-' || c[i] == '+') {
                    f[0] = c[i];
                    j++;
                }
                int k = 1;
                while (++j >= 0) {
                    if (j == c.length) {
                        flag1 = 0;
                        break;
                    }
                    if (c[j] >= '0' && c[j] <= '9') {
                        flag = 0;
                        f[k++] = c[j];
                    } else {
                        flag1 = 0;
                        break;
                    }
                }
            } else {
                break;
            }
            if (flag1 == 0) {
                break;
            }
        }
        if (flag == 0) {
            int result = f[1] - '0';

            for (int j = 2; j < f.length && f[j] != '\0'; j++) {
                if (f[0] == '+' && result > Integer.MAX_VALUE / 10) {
                    return Integer.MAX_VALUE;
                }
                if (f[0] == '-' && result > Integer.MAX_VALUE / 10) {
                    return Integer.MIN_VALUE;
                }
                int tmp = result * 10 + f[j] - '0';
                if (f[0] == '+') {
                    result = result <= tmp ? tmp : Integer.MAX_VALUE;
                }
                if (f[0] == '-') {
                    result = result <= tmp ? tmp : Integer.MIN_VALUE;
                }
            }
            if (f[0] == '-' && result != Integer.MIN_VALUE) result *= -1;

            return result;
        }
        return 0;
    }
}
