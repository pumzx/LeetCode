import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
public class Main {

    public static void main(String[] agrs) {
        //第1题
        /*int[] s = twoSum(new int[]{2, 7, 11, 15}, 9);
        if (s.length > 0)
            System.out.println(s[0] + "," + s[1]);*/

        //第2题
        /*ListNode l1=new ListNode(5);
        l1.next=null;
        ListNode l2=new ListNode(5);
        ListNode s=addTwoNumbers(l1,l2);
        while(s!=null){
            System.out.println(s.val);
            s=s.next;
        }*/

        //第3题
        //System.out.println( lengthOfLongestSubstring("abcabcbb"));

        //第8题
        //System.out.println(myAtoi("    10522545459"));


        //携程笔试题：求数组的最大差值，（并给出两个最大差值的元素的位置）
        /*Scanner scanner = new Scanner(System.in);
        String str=scanner.nextLine();

        String[] w=str.split(",");
        int n=w.length;
        int[] a= new int[n];
        for(int i=0;i<n;i++){
            a[i]=Integer.parseInt(w[i]);
        }
        System.out.println(getResultByOnpow(a,n));
        System.out.println("*******");
        System.out.println(getResultByOn(a,n));*/

        //快速排序+二分查找的递归和非递归
        int[] a={3,5,1,7,3,5,9,3,2};
        quickSort(a,0,a.length-1);
        print(a);
        System.out.println(BinarySearchRecursion(a,2,0,a.length-1));//二分查找
        System.out.println(BinarySearchRecursion2(a,2));//二分查找非递归
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
     * NO2
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode result=head;
        int b=0;
        for(;l1!=null || l2!=null;l1=l1.next,l2=l2.next){
            if(l1==null && l2!=null){
                l1=new ListNode(0);
            }
            if(l2==null && l1!=null){
                l2=new ListNode(0);
            }

            int val=(l1.val+l2.val+b)%10;
            result.next=new ListNode(val);
            result=result.next;
            b=(l1.val+l2.val+b)/10;
            if(l1.next==null&&l2.next==null&&b>0){
                ListNode l = new ListNode(b);
                result.next=l;
                return head.next;
            }
        }
        return head.next;
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




    /**
     * 求数组中相差最大的值，并给出哪两个元素相差最大，
     * 例如：{5,3,2,8,1,3,9,5}，相差最大的值是8，他们的分别是1，9，对应的索引是4，6.
     * 复杂度为O(N)
     * @param a
     * @param n
     * @return
     */
    public static int getResultByOn(int[] a,int n){
        int s=0;
        int max=0;
        int minIndex=0;
        int maxIndex=0;
        for(int i=0;i<n-1;i++){
            int temp=s;
            s=s<0?(a[i+1]-a[i]):(a[i+1]-a[i]+s);
            if(s<0){
                minIndex=i+1;
            }
            if(s>max){
                maxIndex=i+1;
                max=s;
            }
        }
        System.out.println("minIndex:"+minIndex+" ,  maxIndex:"+maxIndex);
        if(max<0)
            return 0;
        return max;
    }


    /**
    * 求数组中相差最大的值，并给出哪两个元素相差最大，
            * 例如：{5,3,2,8,1,3,9,5}，相差最大的值是8，他们的分别是1，9，对应的索引是4，6.
            * 复杂度为O(N^2)
    * @param a 数组
    * @param n 数组长度
    * @return
     */
    public static int getResultByOnpow(int[] a,int n){
        int max=0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(a[j]>a[i]){
                    int temp=a[j]-a[i];
                    if(temp>max){
                        max=temp;
                    }
                }
            }
        }
        return max;
    }

    public static void quickSort(int[] a, int left, int right){
        int index=-1;
        if(left<=right) {
            index = partition(a, left, right);
            if(index<right) {
                quickSort(a, index + 1, right);
            }
            if(index>left){
                quickSort(a, left, index - 1);
            }
        }
    }

    public static int partition(int[] a , int left , int right){
        int first=a[left];
        int beginIndex=left;
        while(left<right) {
            while (left < right && a[right] >= first) {
                right--;
            }
            while (left < right && a[left] <= first) {
                left++;
            }
            swap(a,left,right);
            if(left>=right){
                break;
            }
        }
            int temp = a[left];
            a[left] = first;
            a[beginIndex] = temp;
            return left;
    }

    public static void swap(int[] a,int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    public static void print(int[] a){
        for(int var :a ){
            System.out.print(var+" ");
        }
        System.out.println();
    }

    //递归实现二分查找
    public static int BinarySearchRecursion(int[] a,int target,int left,int right){
        int len=left+(right-left)/2;
        if(a[len]==target)
            return len;
        else if (target>a[len]){
           return BinarySearchRecursion(a,target,len+1,right);
        }
        else {
            return BinarySearchRecursion(a,target,left,len-1);
        }
    }

    //非递归二分查找
    public static int BinarySearchRecursion2(int[] a,int target){
        int bottom=0,top=a.length-1;
        while(bottom<=top){
            int middle=bottom+(top-bottom)/2;
            if(a[middle]==target) {
                return middle;
            }
            else if(target>a[middle]){
                bottom=middle+1;
            }
            else{
                top=middle-1;
            }
        }
        return -1;
    }
}
