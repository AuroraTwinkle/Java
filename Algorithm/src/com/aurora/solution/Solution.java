package com.aurora.solution;

import javax.swing.*;
import java.util.*;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

}
class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> array=new ArrayList<>();
        int row=matrix.length;
        int col=matrix[0].length;
        int turns= Math.min(row,col);
        for(int i=0;i<Math.ceil(turns/2.0);i++){
            for(int j=i;j<col-i;j++){
                array.add(matrix[i][j]);
            }
            for(int k=i+1;k<row-i;k++){
                array.add(matrix[k][col-1-i]);
            }
            for(int j=col-2-i;j>=i&&(row-i-1!=i);j--){
                array.add(matrix[row-1-i][j]);
            }
            for(int k=row-i-2;k>i&&(col-i-1!=i);k--){
                array.add(matrix[k][i]);
            }
        }
        return array;
    }
    public void Mirror(TreeNode root) {
        if(root==null){
            return;
        }
        Deque<TreeNode> stack=new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode treeNode=stack.pop();
            TreeNode tmp=treeNode.left;
            treeNode.left=treeNode.right;
            treeNode.right=tmp;
            if(treeNode.left!=null){
                stack.push(treeNode.left);
            }
            if(treeNode.right!=null){
                stack.push(treeNode.right);
            }
        }
    }
    private boolean isSubTree(TreeNode t1,TreeNode t2){
        if(t2==null){
            return true;
        }
        if(t1==null){
            return false;
        }
        if(t1.val!=t2.val){
            return false;
        }
        else {
            return isSubTree(t1.left,t2.left)&&isSubTree(t1.right,t2.right);
        }
    }
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null||root2==null){
            return false;
        }
        return isSubTree(root1,root2)||HasSubtree(root1.left,root2)||HasSubtree(root1.right,root2);
    }
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head=new ListNode(-10);
        ListNode p=head;
        ListNode p1=list1,p2=list2;
        while(p1!=null&&p2!=null){
            if(p1.val<=p2.val){
                p.next=p1;
                p=p1;
                p1=p1.next;
            }
            else {
                p.next=p2;
                p=p2;
                p2=p2.next;
            }
        }
        if(p1!=null){
            p.next=p1;
        }
        if(p2!=null){
            p.next=p2;
        }
        return head.next;
    }
    public ListNode ReverseList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode pre=null;
        ListNode cur=head;
        ListNode next=head;
        while(next!=null){
            next=next.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }

        return pre;
    }
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head==null){
            return null;
        }
        ListNode p1=head;
        ListNode p2=head;
        while(p2!=null&&k-->0){
            p2=p2.next;
        }
        while(p2!=null){
            p1=p1.next;
            p2=p2.next;
        }
        return k>0?null:p1;
    }

    public void reOrderArray(int [] array) {
        if(array.length==0){
            return;
        }
        for(int i=0;i<array.length;i++){
            for(int j=1;j<array.length-i;j++){
                if(array[j-1]%2==0&&array[j]%2==1){
                    int tmp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=tmp;
                }
            }
        }
    }
    /*
    如果一个整数不为0，那么这个整数至少有一位是1。如果我们把这个整数减1，
    那么原来处在整数最右边的1就会变为0，原来在1后面的所有的0都会变成1
    (如果最右边的1后面还有0的话)。其余所有位将不会受到影响。举个例子：
    一个二进制数1100，从右边数起第三位是处于最右边的一个1。减去1后，
    第三位变成0，它后面的两位0变成了1，而前面的1保持不变，因此得到的
    结果是1011.我们发现减1的结果是把最右边的一个1开始的所有位都取反了。
    这个时候如果我们再把原来的整数和减去1之后的结果做与运算，从原来整数
    最右边一个1那一位开始所有位都会变成0。如1100&1011=1000.也就是说，
    把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0.那么
    一个整数的二进制有多少个1，就可以进行多少次这样的操作。
     */
    public int NumberOf1(int n) {
        int ret=0;
        while (n!=0){
            ret++;
            n=n&(n-1);
        }
        return ret;
    }
    public double Power(double base, int exponent) {
        int p = Math.abs(exponent);
        Math.pow(1,1);
        double r = 1.0;
        while(p!=0){
            if((p & 1)!=0) r *= base;
            base *= base;
             p >>= 1;
        }
        return exponent < 0 ? 1/ r : r;
    }
    public int JumpFloor(int target,int cur,int nex) {
        if(1==target){
            return cur;
        }
        if(2==target){
            return nex;
        }
        return JumpFloor(target-1,nex,cur+nex);
    }
    public int JumpFloorII(int target) {

        if(0>=target){
            return 0;
        }
        else if(1==target){
            return 1;
        }
        else{return 2*JumpFloorII(target-1);}
    }
    public int JumpFloor(int target){
        if(0==target){
            return 0;
        }
        if(1==target){return 1;}
        if(2==target){
            return 2;
        }
        int pre=1,cur=1,nex=0;
        for(int i=0;i<target;i++){
            nex=pre+cur;
            pre=cur;
            cur=nex;
        }
        return pre;
    }
}
