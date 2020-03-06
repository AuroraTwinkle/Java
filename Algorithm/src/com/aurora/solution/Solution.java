package com.aurora.solution;

import javax.swing.*;
import java.lang.reflect.Array;
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

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Solution {
    ArrayList<ArrayList<Integer>> resultLists = new ArrayList<>();
    ArrayList<Integer> arrayList = new ArrayList<>();

    public int getWaterButtons(int n){
        if(n<3){
            return 0;
        }
        int count=n/3;
        int empty=count+n%3;
        for(;empty>=2;empty=empty/3+empty%3){
            if(empty==2){
                count+=1;
                break;
            }
            count+=empty/3;
        }
        return count;
    }
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        if(n<=0){
            return count;
        }
        int j=0;
        for(int i=1;i<=n;i*=10){
            int div=i*10;
            count+=(n/div)*i;
            if(n%div>=Math.pow(10,j)+Math.pow(10,j)-1){
                count+=i;
            }
            else if(n%div>=Math.pow(10,j)){
                count+=n%div-Math.pow(10,j)+1;
            }else {
                count+=0;
            }
            j++;
        }
        return count;
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int sumMax = Integer.MIN_VALUE;
        int sum = 0;
        for (int item : array) {
            if (sum <= 0) {
                sum = item;
            } else {
                sum += item;
            }
            if (sum > sumMax) {
                sumMax = sum;
            }
        }
        return sumMax;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Arrays.sort(input);
        if (input.length < k) {
            return arrayList;
        }
        for (int i = 0; i < k; i++) {
            arrayList.add(input[i]);
        }
        return arrayList;
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        int count = 0;
        int val = array[0];
        for (int element : array) {
            if (element == val) {
                count++;
            } else if (count != 0) {
                count--;
            } else {
                val = element;
            }
        }
        count = 0;
        for (int element : array) {
            if (element == val) {
                count++;
            }
        }
        if (count > array.length / 2) {
            return val;
        }
        return 0;
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode tmp = head;
        while (pHead.next != null) {
            tmp.next = new RandomListNode(pHead.next.label);
            if (pHead.random != null) {
                tmp.random = new RandomListNode(pHead.random.label);
            }
            tmp = tmp.next;
            pHead = pHead.next;
        }
        return head;
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return resultLists;
        }
        arrayList.add(root.val);
        target = target - root.val;
        if (target == 0 && root.right == null && root.left == null) {
            resultLists.add(new ArrayList<>(arrayList));
        }
        if (root.left != null) {
            FindPath(root.left, target);
        }
        if (root.right != null) {
            FindPath(root.right, target);
        }
        arrayList.remove(arrayList.size() - 1);
        return resultLists;
    }

    public boolean JudgeBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int i = start;
        while (i < end && sequence[i] < sequence[end]) {
            i++;
        }
        for (; i < end; i++) {
            if (sequence[i] < sequence[end]) {
                return false;
            }
        }
        return JudgeBST(sequence, 0, i - 1) && JudgeBST(sequence, i, end);
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }
        return JudgeBST(sequence, 0, sequence.length - 1);
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.remove();
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
            arrayList.add(treeNode.val);
        }
        return arrayList;
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (popA.length == 0 || pushA.length == 0) {
            return false;
        }
        int popAaIndex = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int item : pushA) {
            stack.push(item);
            while (!stack.isEmpty() && stack.peek() == popA[popAaIndex]) {
                stack.pop();
                popAaIndex++;
            }
        }
        return stack.isEmpty();
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> array = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int turns = Math.min(row, col);
        for (int i = 0; i < Math.ceil(turns / 2.0); i++) {
            for (int j = i; j < col - i; j++) {
                array.add(matrix[i][j]);
            }
            for (int k = i + 1; k < row - i; k++) {
                array.add(matrix[k][col - 1 - i]);
            }
            for (int j = col - 2 - i; j >= i && (row - i - 1 != i); j--) {
                array.add(matrix[row - 1 - i][j]);
            }
            for (int k = row - i - 2; k > i && (col - i - 1 != i); k--) {
                array.add(matrix[k][i]);
            }
        }
        return array;
    }

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            TreeNode tmp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = tmp;
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
        }
    }

    private boolean isSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        } else {
            return isSubTree(t1.left, t2.left) && isSubTree(t1.right, t2.right);
        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return isSubTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-10);
        ListNode p = head;
        ListNode p1 = list1, p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p = p2;
                p2 = p2.next;
            }
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return head.next;
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while (next != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && k-- > 0) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return k > 0 ? null : p1;
    }

    public void reOrderArray(int[] array) {
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] % 2 == 0 && array[j] % 2 == 1) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
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
        int ret = 0;
        while (n != 0) {
            ret++;
            n = n & (n - 1);
        }
        return ret;
    }

    public double Power(double base, int exponent) {
        int p = Math.abs(exponent);
        Math.pow(1, 1);
        double r = 1.0;
        while (p != 0) {
            if ((p & 1) != 0) r *= base;
            base *= base;
            p >>= 1;
        }
        return exponent < 0 ? 1 / r : r;
    }

    public int JumpFloor(int target, int cur, int nex) {
        if (1 == target) {
            return cur;
        }
        if (2 == target) {
            return nex;
        }
        return JumpFloor(target - 1, nex, cur + nex);
    }

    public int JumpFloorII(int target) {

        if (0 >= target) {
            return 0;
        } else if (1 == target) {
            return 1;
        } else {
            return 2 * JumpFloorII(target - 1);
        }
    }

    public int JumpFloor(int target) {
        if (0 == target) {
            return 0;
        }
        if (1 == target) {
            return 1;
        }
        if (2 == target) {
            return 2;
        }
        int pre = 1, cur = 1, nex = 0;
        for (int i = 0; i < target; i++) {
            nex = pre + cur;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
}
