package com.aurora.person;

import com.aurora.person.Person;
import com.aurora.person.Student;
import com.aurora.solution.Solution;
import com.aurora.test.Test;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.*;
import java.util.regex.Pattern;


public class Main {
    public static class Node{
        int values;
        Node next;
        Node(int val){
            this.values=val;
            this.next=null;
        }
    }
    public static int binaryFind(int []array,int key,int lows,int highs){
        if(array==null||array.length==0){
            return -1;
        }
        int low=lows,high=highs;
        int mid=0;
        while(low<high){
            mid=(low+high)/2;
            if(array[mid]==key){
                high=mid-1;
            }
            else if(array[mid]>key){
                high=mid;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    public static void insertSort(int []array){
        if(array==null||array.length==0)return;
        for(int i=1;i<array.length;i++){
            if(array[i]<array[i-1]){
                int index=binaryFind(array,array[i],0,i-1);
                int tmp=array[i];
                for(int j=i;j>index;j--){
                    array[j]=array[j-1];
                }
                array[index]=tmp;
            }

        }
    }
    public static Node reverse(Node head){
        if(head==null||head.next==null){
            return null;
        }
        Node q=head.next;
        Node r=q;
        head.next=null;
        while(q!=null){
            r=q.next;
            q.next= head.next;
            head.next=q;
            q=r;
        }
        return head;
    }
    public static void main(String[] args) {
        int []array={12,4,5,34,6,34,12,4,46,43};
        int []arrays={12,12,34,34,34,45,45};
        System.out.println(binaryFind(arrays,35,0,6));
        insertSort(array);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }

    }
}
//
//    class solution {
//        public boolean Match (String s, String p) {
//            // write code here
//            if(s.equals(p))return true;
//            char[] s_chars=s.toCharArray();
//            char[] p_chars=p.toCharArray();
//            int s_low=0;
//            int p_low=0;
//            boolean ret=true;
//            while (p_low<p_chars.length){
//                if(p_chars[p_low]==s_chars[s_low]){
//                    s_low++;
//                    p_low++;
//                }
//                else if(p_chars[p_low]=='.'){
//                    s_low++;
//                    p_low++;
//                }
//                else if(p_chars[p_low]=='*'){
//                    while (s_low<s_chars.length&&p_chars[p_low-1]==s_chars[s_low]){
//                        s_low++;
//                    }
//                }
//                else {
//                    ret=false;
//                    break;
//                }
//                ret=Pattern.matches(p,s);
//            }
//            return ret;
//        }
//
//        public void moveElement(int[] array) {
//            if (array == null || array.length == 0 || array.length == 1) return;
//            int low = 0;
//            int high = array.length - 1;
//            while (low < high) {
//                while (low < high && array[low] % 2 != 0) low++;
//                while (low < high && array[high] % 2 != 1) high--;
//                if (low < high) {
//                    int tmp = array[low];
//                    array[low] = array[high];
//                    array[high] = tmp;
//                    low++;
//                    high--;
//                }
//            }
//        }
//
//        public void twoQuickSort(int[] array, int start, int end) {
//            if (array.length == 0 || array.length == 1 || start >= end) {
//                return;
//            }
//            int key = array[start];
//            int low = start + 1;
//            int high = end;
//            while (true) {
//                while (low <= high && array[low] < key) low++;
//                while (high >= start + 1 && array[high] > key) high--;
//                if (low > high) {
//                    break;
//                }
//                swap(array, low, high);
//                high--;
//                low++;
//            }
//            swap(array, start, high);
//            twoQuickSort(array, start, high - 1);
//            twoQuickSort(array, high + 1, end);
//        }
//
//        public void threeQuickSort(int[] array, int start, int end) {
//            if (array.length == 0 || array.length == 1 || end < start) {
//                return;
//            }
//            if (start < end) {
//                int tmp = array[start];
//                int lt = start;
//                int gt = end;
//                int i = start + 1;
//                while (i <= gt) {
//                    if (array[i] < tmp) {
//                        swap(array, i, lt);
//                        i++;
//                        lt++;
//                    } else if (array[i] > tmp) {
//                        swap(array, i, gt);
//                        gt--;
//                    } else {
//                        i++;
//                    }
//                }
//
//                threeQuickSort(array, start, lt - 1);
//                threeQuickSort(array, gt + 1, end);
//            }
//        }
//
//        public void swap(int[] array, int i, int j) {
//            int tmp = array[i];
//            array[i] = array[j];
//            array[j] = tmp;
//        }
//
//        public int trapRainWater(int[][] heightMap) {
//            int ret = 0;
//            int n = heightMap.length;
//            int m = heightMap[0].length;
//            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((arr1, arr2) -> {
//                return arr1[2] - arr2[2];
//            });
//            boolean[][] visits = new boolean[n][m];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (i == 0 || j == 0 || j == m - 1 || i == n - 1) {
//                        priorityQueue.offer(new int[]{i, j, heightMap[i][j]});
//                        visits[i][j] = true;
//                    }
//                }
//            }
//            int[] dirs = new int[]{-1, 0, 1, 0, -1};
//            while (!priorityQueue.isEmpty()) {
//                int[] p = priorityQueue.poll();
//                for (int i = 0; i < 4; i++) {
//                    int nx = p[0] + dirs[i];
//                    int ny = p[1] + dirs[i + 1];
//                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visits[nx][ny]) {
//                        if (p[2] > heightMap[nx][ny]) {
//                            ret += p[2] - heightMap[nx][ny];
//                        }
//                        priorityQueue.offer(new int[]{nx, ny, Math.max(p[2], heightMap[nx][ny])});
//                        visits[nx][ny] = true;
//                    }
//                }
//            }
//            return ret;
//        }
//
//        public int[] spiralOrder(int[][] matrix) {
//            if (matrix.length == 0) {
//                return new int[0];
//            }
//            int n = matrix.length;
//            int m = matrix[0].length;
//            int turn = Math.min(m, n);
//            int[] array = new int[m * n];
//            int index = 0;
//            for (int i = 0; i < (turn + 1) / 2; i++) {
//                for (int j = i; j < m - i; j++) {
//                    array[index++] = matrix[i][j];
//                }
//                for (int j = i + 1; j < n - i; j++) {
//                    array[index++] = matrix[j][m - 1 - i];
//                }
//                for (int j = m - 2 - i; j >= i && (n - 1 - i != i); j--) {
//                    array[index++] = matrix[n - 1 - i][j];
//                }
//                for (int j = n - 2 - i; j > i && (m - 1 - i != i); j--) {
//                    array[index++] = matrix[j][i];
//                }
//            }
//            return array;
//        }
//
//        public void rotate(int[][] matrix) {
//            int n = matrix.length;
//            for (int i = 0; i < n / 2; i++) {
//                for (int j = i; j < n - 1 - i; j++) {
//                    int tmp = matrix[i][j];
//                    matrix[i][j] = matrix[n - 1 - j][i];
//                    matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
//                    matrix[n - 1 - i][n - 1 - j] = matrix[j][n - i - 1];
//                    matrix[j][n - i - 1] = tmp;
//                }
//            }
//        }
//
//        public int lengthOfLongestSubstring(String s) {
//            if (s.length() == 0) {
//                return 0;
//            }
//            if (s.length() < 2) {
//                return 1;
//            }
//            int ans = 1;
//            Map<Character, Integer> map = new HashMap<>();
//            for (int start = 0, end = 0; end < s.length(); end++) {
//                if (map.containsKey(s.charAt(end))) {
//                    start = Math.max(map.get(s.charAt(end)) + 1, start);
//                }
//                ans = Math.max(ans, end - start + 1);
//                map.put(s.charAt(end), end);
//            }
//            return ans;
//        }
//
//        public String longestPalindrome(String s) {
//            boolean[][] dp = new boolean[s.length()][s.length()];
//            for (int i = 0; i < s.length(); i++) {
//                dp[i][i] = true;
//            }
//            int maxLen = 1;
//            int start = 0;
//            for (int j = 1; j < s.length(); j++) {
//                for (int i = 0; i < j; i++) {
//                    if (s.charAt(i) == s.charAt(j)) {
//                        if (j - i < 3) {
//                            dp[i][j] = true;
//                        } else {
//                            dp[i][j] = dp[i + 1][j - 1];
//                        }
//                    } else {
//                        dp[i][j] = false;
//                    }
//                    if (dp[i][j]) {
//                        if (j - i + 1 > maxLen) {
//                            maxLen = j - i + 1;
//                            start = i;
//                        }
//                    }
//                }
//
//            }
//            return s.substring(start, start + maxLen);
//        }
//
//        int findMin(int[] nums) {
//            if (nums.length == 0) {
//                return Integer.MIN_VALUE;
//            }
//            if (nums.length == 1 || nums[0] < nums[nums.length - 1]) {
//                return nums[0];
//            }
//            int left = 0, right = nums.length - 1, mid = 0;
//            while (left < right) {
//                mid = (left + right) / 2;
//                if (nums[mid] < nums[right]) {
//                    right = mid;
//                } else if (nums[mid] > nums[right]) {
//                    left = mid + 1;
//                } else {
//                    right -= 1;
//                }
//            }
//            return nums[left];
//        }
//
//        public int lastIndex(int[] array, int key) {
//            if (array.length <= 0) {
//                return -1;
//            }
//            int left = 0, right = array.length;
//            while (left < right) {
//                int mid = (left + right) / 2;
//                if (array[mid] == key) {
//                    left = mid + 1;
//                } else if (array[mid] > key) {
//                    right = mid;
//                } else {
//                    left = mid + 1;
//                }
//            }
//            if (left <= 0) {
//                return -1;
//            }
//            return array[left - 1] == key ? left - 1 : -1;
//        }
//
//        public int firstIndex(int[] array, int key) {
//            if (array.length == 0) {
//                return -1;
//            }
//            int left = 0, right = array.length;
//            while (left < right) {
//                int mid = (left + right) / 2;
//                if (array[mid] == key) {
//                    right = mid;
//                } else if (array[mid] > key) {
//                    right = mid;
//                } else {
//                    left = mid + 1;
//                }
//            }
//            if (left >= array.length) {
//                return -1;
//            }
//            return array[left] == key ? left : -1;
//        }
//
//        public int minPathSum(int[][] grid) {
//            int m = grid.length;
//            int n = grid[0].length;
//            int[][] dp = new int[m][n];
//            dp[0][0] = 0;
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (i == 0 && j != 0) {
//                        dp[i][j] += dp[i][j - 1];
//                    }
//                    if (j == 0 && i != 0) {
//                        dp[i][j] += dp[i - 1][j];
//                    }
//                    if (i != 0 && j != 0) {
//                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
//                    }
//                }
//            }
//            return dp[m - 1][n - 1];
//        }
//    }
//}
// public static int getMinBox(int []array){
//        Map<Integer,Integer> map=new HashMap<>();
//        for (int value : array) {
//            if (map.containsKey(value)) {
//                map.put(value, map.get(value) + 1);
//            } else {
//                map.put(value, 1);
//            }
//        }
//        int ret=0;
//
//        for (int key:map.keySet()){
//            if(map.get(key)>ret){
//                ret=map.get(key);
//            }
//        }
//        return ret;
//    }
// public static String fixString(String string) {
//        char[] chars = string.toCharArray();
//        int j = 0;
//        for (int i = 0; i < string.length(); i++) {
//            chars[j++] = chars[i];
//            if (j >= 3 && chars[j - 1] == chars[j - 2] && chars[j - 2] == chars[j - 3]) {
//                j--;
//            }
//            if (j >= 4 && chars[j - 1] == chars[j - 2] && chars[j - 3] == chars[j - 4]) {
//                j--;
//            }
//        }
//        String str = new String(chars);
//        return str.substring(0, j);
//    }
//
//    public static void sortArray(Integer[] array, int low, int high) {
//        if (array.length <= 0) {
//            return;
//        }
//        if (low < high) {
//            int mid = getMid(array, low, high);
//            sortArray(array, 0, mid - 1);
//            sortArray(array, mid + 1, high);
//        }
//
//    }
//
//    public static int getMid(Integer[] array, int low, int high) {
//        int tmp = array[low];
//        while (low < high) {
//            while (array[high] >= tmp && low < high) {
//                high--;
//            }
//            array[low] = array[high];
//            while (low < high && array[low] <= tmp) {
//                low++;
//            }
//            array[high] = array[low];
//        }
//        array[low] = tmp;
//        return low;
//    }


//        int[] arr={2,-45,-4,6,-8,-6,1,3,-4,5,7};
//        Solution solution=new Solution();
////        solution.reOrderArray(arr);
//        System.out.println(solution.FindGreatestSumOfSubArray(arr));
//        for(String it:args){
//            if("-version".equals(it)){
//                System.out.println("version-1-1");
//            }
//        }

//        String string=new String(new char[]{'h','e','l','l','o'});
//        log.warn(String.class.getMethod("substring",int.class,int.class).invoke(string,0,2));
//        Class student=Student.class;
//        Field[] fields=student.getDeclaredFields();
//        Method method= student.getMethod("getName",null);
//        Student student1=new Student("tom",99,"201606",88);
//        System.out.println(method.invoke(student1));
//        log.info(student.getDeclaredField("grade"));
//        for(Field field:fields){
//            log.info(field.getName()+" "+field.getType()+" "+field.getModifiers());
//        }

//        Random r = new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(r.nextInt(100));
//        }
//        BigInteger n = new BigInteger("999999").pow(99);
//        float f = n.floatValue();
//        System.out.println(f);
//        BigDecimal bigDecimal=new BigDecimal("123.123");
//        System.out.println(Arrays.toString(bigDecimal.divideAndRemainder(new BigDecimal("12.23"))));
//        BeanInfo info= Introspector.getBeanInfo(Person.class);
//        for(PropertyDescriptor pd:info.getPropertyDescriptors()){
//            System.out.println(pd.getName());
//            System.out.println(pd.getReadMethod());
//            System.out.println(pd.getWriteMethod());
//        }
//        Integer x=1;
//        Integer y=Integer.valueOf(1);
//        System.out.println(x==y);
//        String s = " hello    fg ";
//        System.out.println(s.length());
//        String str=s.trim();
//        System.out.println(str.length());
//        System.out.println(s);
//        s = s.toUpperCase();
//        System.out.println(s);
//        int[] arr={12,243,24,24};
//        int[] arrClone=arr.clone();
//        arr[0]=13;
//        System.out.println(Arrays.toString(arrClone));
//        Test test=new Test();
//        test.setName(null);
//        System.out.println(Arrays.toString(test.getNames()));
//        int[] arr={45,454,8,8,45,787,846,46};
//        int[][][] arr3={{{1341,4325,36},{24,64,7}},{{12,124,34},{14,24,65}}};
//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.deepToString(arr3));
//        for(int item:arr){
//            System.out.printf("%d\t",item);
//        }
//        String fruit="apple";
//        Scanner scanner=new Scanner(System.in);
//        fruit=scanner.nextLine();
//        int opt=switch(fruit){
//            case "apple" ->1;
//            case "opera"->2;
//            default -> throw new IllegalStateException("Unexpected value: " + fruit);
//        };
//        System.out.println("opt="+opt);
//        Scanner scanner=new Scanner(System.in);
//        String name=scanner.nextLine();
//        double money=scanner.nextDouble();
//        System.out.printf("name=%s,money=%.5f",name,money);
