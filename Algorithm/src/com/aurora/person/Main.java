package com.aurora.person;
import com.aurora.person.Person;
import com.aurora.person.Student;
import com.aurora.solution.Solution;
import com.aurora.test.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args)  {
        Log log= LogFactory.getLog(Main.class);
        int[] arr={2,4,6,1,3,5,7};
        Solution solution=new Solution();
        solution.reOrderArray(arr);
        log.info(solution.JumpFloor(3));
        log.info(solution.JumpFloorII(3));
        log.info(solution.NumberOf1(-5));
        log.info(solution.Power(2,10));
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
    }
}
