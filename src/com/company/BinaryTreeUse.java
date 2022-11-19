package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BinaryTreeNode<T> {
public T data;
public BinaryTreeNode<T> left;
public BinaryTreeNode<T> right;

public BinaryTreeNode(T data) {
        this.data = data;
        }
  }
public class BinaryTreeUse {
 
    public static BinaryTreeNode<Integer> takeInputTreeBetter(boolean isRoot, int parentData, boolean isLeft){
        Scanner sc = new Scanner(System.in);
        if (isRoot) {
            System.out.println("Enter root data");
        }else {
            if (isLeft){
                System.out.println("Enter left child of " + parentData);
            }else {
                System.out.println("Enter right child of " + parentData);
            }
        }

        int rootData = sc.nextInt();
        if (rootData == -1){
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        BinaryTreeNode<Integer> leftChild = takeInputTreeBetter(false, rootData,true);
        BinaryTreeNode<Integer> rightChild = takeInputTreeBetter(false,rootData,false);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }


      public static BinaryTreeNode<Integer> takeInputTree(){
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter root data");
          int rootData = sc.nextInt();
          if (rootData == -1){
              return null;
          }
          BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
          BinaryTreeNode<Integer> leftChild = takeInputTree();
          BinaryTreeNode<Integer> rightChild = takeInputTree();
          root.left = leftChild;
          root.right = rightChild;
          return root;
      }
    public static void printDetailTree(BinaryTreeNode<Integer> root) {

        if(root == null) {
            return;
        }
        System.out.print(root.data+":");
        if(root.left != null){
            System.out.print("L"+root.left.data);
        }
        if(root.right != null){
            System.out.print(", R"+root.right.data);
        }
        System.out.println(" ");

        printDetailTree(root.left);
        printDetailTree(root.right);
    }
public static void  printAtDepthK(BinaryTreeNode<Integer> root, int k){
     if (k==0){
         System.out.println(root.data);
         return;
     }
     printAtDepthK(root.left,k-1);
     printAtDepthK(root.right,k-1);
}

    public static boolean isNodePresent(BinaryTreeNode<Integer> root, int x) {
        //Your code goes here
        if(root == null){
            return false;
        }
        if(root.data == x){
            return true;
        }
        else{
            return (isNodePresent(root.left , x)|| isNodePresent(root.right , x));
        }
    }

    public static int leaves(BinaryTreeNode<Integer> root){
        if(root==null){
            return 0;
        }
        if(root.left==null &&root.right==null){
            return 1;
        }
        return leaves(root.left)+leaves(root.right);
    }

    public static int numNodes(BinaryTreeNode<Integer> root){
        if(root==null) return 0;
        int leftNodeCount= numNodes(root.left);
        int rightNodeCount= numNodes(root.right);
        return 1+leftNodeCount+rightNodeCount;
    }

    public static int largest(BinaryTreeNode<Integer> root){
        if(root==null){
            return -1;
        }
        int largestLeft= largest(root.left);
        int largestRight= largest(root.right);
        return Math.max(root.data, Math.max(largestLeft, largestRight));
    }


    public static void preOrder(BinaryTreeNode<Integer> root) {
        //Your code goes here
        if (root==null)
        {
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static int countNodesGreaterThanX(BinaryTreeNode<Integer> root, int x) {
        //Your code goes here
        if (root==null)
        {
            return 0;
        }

        int smallOutput=countNodesGreaterThanX(root.left,x)+countNodesGreaterThanX(root.right,x);
        if (root.data>x)
        {
            return smallOutput+1;
        }
        else
        {
            return smallOutput;
        }
    }

    public static int height(BinaryTreeNode<Integer> root) {
        //Your code goes here
        if (root == null) {
            return 0;
        }
        int smallleft = height(root.left);
        int smallright = height(root.right);

        return 1+ Math.max(smallleft,smallright);
        }

    public static BinaryTreeNode<Integer> removeLeaves(BinaryTreeNode<Integer> root){
        if (root == null){
            return null;
        }
        if (root.left == null&& root.right == null){
            return null;
        }
        root.left = removeLeaves(root.left);
        root.right =removeLeaves(root.right);
        return root;
    }

    public static void mirrorBinaryTree(BinaryTreeNode<Integer> root){

        if (root==null)
        {
            return;
        }

        BinaryTreeNode<Integer> temp=root.left;
        root.left=root.right;
        root.right=temp;
        mirrorBinaryTree(root.left);
        mirrorBinaryTree(root.right);

    }

    public static boolean isBalanced(BinaryTreeNode<Integer> root){
        if (root == null){
            return true;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (Math.abs(leftHeight - rightHeight) >1){
            return false;
        }
        boolean isLeftBalanced = isBalanced(root.left);
        boolean isRightBalanced = isBalanced(root.right);

        return isLeftBalanced && isRightBalanced;
    }

public static BalancedTreeReturn isBetterBalanced(BinaryTreeNode<Integer> root){
        if (root == null){
            int height = 0;
            boolean isBal = true;
            BalancedTreeReturn ans = new BalancedTreeReturn();
            ans.height = height;
            ans.isBalanced = isBal;
            return ans;
        }
        BalancedTreeReturn leftOutput = isBetterBalanced(root.left);
        BalancedTreeReturn rightOutput = isBetterBalanced(root.right);
        boolean isBal =true;
        int height = 1+ Math.max(leftOutput.height,rightOutput.height);
        if (Math.abs(leftOutput.height - rightOutput.height) >1){
            isBal = false;
        }
        if (!leftOutput.isBalanced || !rightOutput.isBalanced){
            isBal = false;
        }
    BalancedTreeReturn ans = new BalancedTreeReturn();
    ans.height = height;
    ans.isBalanced = isBal;
        return ans;
}

public static int diameterOfBinaryTree(BinaryTreeNode<Integer> root){
        if (root == null){
            return 0;
        }
        return 1+ findHeight(root.left)+ findHeight(root.right);
}
public static int findHeight(BinaryTreeNode<Integer> root){
        if (root == null){
            return 0;
        }
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

    if(leftHeight>rightHeight)
    {
        return leftHeight+1;
    }
    else
    {
        return rightHeight+1;
    }
}

    public static BinaryTreeNode<Integer> takeInputLevelWise() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the root data: ");
        int rootData = sc.nextInt();
        if (rootData == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        Queue<BinaryTreeNode<Integer>> pendingChild = new LinkedList<BinaryTreeNode<Integer>>();
        pendingChild.add(root);
        while (!pendingChild.isEmpty()) {
            BinaryTreeNode<Integer> front = pendingChild.poll();
            System.out.println("Enter left child of " + front.data);
            int left = sc.nextInt();
            if (left != -1) {
                BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<>(left);
                front.left = leftChild;
                pendingChild.add(leftChild);
            }

            System.out.println("Enter right child of " + front.data);
            int right = sc.nextInt();
            if (right != -1) {
                BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<>(right);
                front.left = rightChild;
                pendingChild.add(rightChild);
            }
        }
        return root;
    }


        public static void printLevelWise(BinaryTreeNode<Integer> root) {
            if (root==null)
            {
                return;
            }
            Queue<BinaryTreeNode<Integer>> nodesToPrint=new LinkedList<BinaryTreeNode<Integer>>();
            nodesToPrint.add(root);
            while(!nodesToPrint.isEmpty())
            {
                BinaryTreeNode<Integer> front = nodesToPrint.poll();
                System.out.print(front.data+":");
                if (front.left!=null)
                {
                    nodesToPrint.add(front.left);
                    System.out.print("L:"+front.left.data);
                }
                else
                {
                    System.out.print("L:-1");
                }

                if (front.right!=null)
                {
                    nodesToPrint.add(front.right);
                    System.out.print(",R:"+front.right.data);
                }
                else
                {
                    System.out.print(",R:-1");
                }
                System.out.println();
            }
        }

       public static int maximum(BinaryTreeNode<Integer> root){
        if (root == null){
            return Integer.MIN_VALUE;
        }
        int leftMax = maximum(root.left);
        int rightMax = maximum(root.right);

        return Math.max(root.data,Math.max(leftMax,rightMax));
    }

    public static int minimum(BinaryTreeNode<Integer> root){
        if (root == null){
            return Integer.MAX_VALUE;
        }
        int leftMin = maximum(root.left);
        int rightMin = maximum(root.right);

        return Math.min(root.data,Math.min(leftMin,rightMin));
    }

        public static boolean isBST(BinaryTreeNode<Integer> root){
        if (root == null){
            return true;
        }
        int leftMax = maximum(root.left);
        if (root.data <= leftMax){
            return false;
        }
        int rightMin = minimum(root.right);
        if (root.data > rightMin){
            return false;
        }
        boolean isLeftBST = isBST(root.left);
        boolean isRightBST = isBST(root.right);

           return isLeftBST && isRightBST;
        }


        public static ArrayList<Integer> nodeToRoot(BinaryTreeNode<Integer> root, int x){
        if (root == null){
            return null;
        }
        if (root.data == x){
            ArrayList<Integer> output = new ArrayList<Integer>();
            output.add(root.data);
            return output;
        }
        ArrayList<Integer> leftOutput = nodeToRoot(root.left,x);
        if (leftOutput != null){
            leftOutput.add(root.data);
            return leftOutput;
        } ArrayList<Integer> rightOutput = nodeToRoot(root.right,x);
            if (rightOutput != null){
                rightOutput.add(root.data);
                return rightOutput;
            }
        return null;
        }

        static void inorder(BinaryTreeNode<Integer> root){
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }
    static void preorder(BinaryTreeNode<Integer> root){
        if (root==null){
            return;
        }
        System.out.print(root.data+" ");
        inorder(root.left);
        inorder(root.right);
    }
    static void postorder(BinaryTreeNode<Integer> root){
        if (root==null){
            return;
        }
        inorder(root.left);
        inorder(root.right);
        System.out.print(root.data+" ");
    }




    public static void main(String[] args) {
//        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
//
//        BinaryTreeNode<Integer> rootLeft = new BinaryTreeNode<Integer>(2);
//        BinaryTreeNode<Integer> rootRight = new BinaryTreeNode<Integer>(3);
//        root.left = rootLeft;
//        root.right = rootRight;
//
//        BinaryTreeNode<Integer> twoRight = new BinaryTreeNode<Integer>(4);
//        BinaryTreeNode<Integer> threeLeft = new BinaryTreeNode<Integer>(5);
//        rootLeft.right = twoRight;
//        rootRight.left = threeLeft;

//       BinaryTreeNode<Integer> root = takeInputTree();

//        BinaryTreeNode<Integer> root = takeInputTreeBetter(true,0,true);

        BinaryTreeNode<Integer> root = takeInputLevelWise();
        printLevelWise(root);
//        printDetailTree(root);
        System.out.println("Numbers of node is: " +numNodes(root));
        System.out.println("largest node is: "+ largest(root));
        System.out.println("No of leaves are: "+ leaves(root));
        System.out.println("Height of the tree is: "+ height(root));
        System.out.println("diameter is: " + diameterOfBinaryTree(root));
        System.out.println(isNodePresent(root,70));
        System.out.println("Nodes are in depth k are: ");
       // printAtDepthK(root,2);
        System.out.println("is balanced " + isBetterBalanced(root).isBalanced);
        BinaryTreeNode<Integer> newRoot = removeLeaves(root);
        System.out.println(newRoot);
        System.out.println(isBST(root));
        
//        ArrayList<Integer> path = nodeToRoot(root,5);
//        if (path == null){
//            System.out.println("Not found");
//        }else {
//            for (int i:path) {
//                System.out.println(i);
//            }
//        }

    }

}

