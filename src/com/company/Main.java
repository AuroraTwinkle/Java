package com.company;

public class Main {

    public static void main(String[] args) {
        int data[]={4564,54,8,89,8,8,5,8979,3125};
        insertSort(data);
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+"\t");
        }
    }
  static void insertSort(int arr[]){
        for(int i=1;i<arr.length;i++){
            int j=i;
            while(j>0&&arr[j-1]>arr[j]){
                arr[j]=arr[j-1]+arr[j];
                arr[j-1]=arr[j]-arr[j-1];
                arr[j]=arr[j]-arr[j-1];
                j--;
            }
        }
    }

}
