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
import java.util.logging.Logger;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            System.out.println(fixString(str));
        }

    }

    public static String fixString(String string) {
        char[] chars = string.toCharArray();
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            chars[j++] = chars[i];
            if (j >= 3 && chars[j - 1] == chars[j - 2] && chars[j - 2] == chars[j - 3]) {
                j--;
            }
            if (j >= 4 && chars[j - 1] == chars[j - 2] && chars[j - 3] == chars[j - 4]) {
                j--;
            }
        }
        String str = new String(chars);
        return str.substring(0, j);
    }

    public static void sortArray(Integer[] array, int low, int high) {
        if (array.length <= 0) {
            return;
        }
        if (low < high) {
            int mid = getMid(array, low, high);
            sortArray(array, 0, mid - 1);
            sortArray(array, mid + 1, high);
        }

    }

    public static int getMid(Integer[] array, int low, int high) {
        int tmp = array[low];
        while (low < high) {
            while (array[high] >= tmp && low < high) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= tmp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = tmp;
        return low;
    }
}

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
