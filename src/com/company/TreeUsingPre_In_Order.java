package com.company;


public class TreeUsingPre_In_Order {

    public static BinaryTreeNode<Integer> buildTree(int[] preOrder, int[] inOrder) {
        //Your code goes here
        BinaryTreeNode<Integer> root = buildTree(preOrder, inOrder, 0 ,preOrder.length-1, 0, inOrder.length-1);
        return root;

    }

    public static BinaryTreeNode<Integer> buildTree(int[] preorder, int[] inorder,int siPre, int eiPre, int siIn, int eiIn)
    {
        //Base case - If number of elements in the pre-order is 0
        if (siPre>eiPre)
        {
            return null;
        }

        //Defining the root node for current recursion
        int rootData=preorder[siPre];
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);

        //Finding root data's location in Inorder (Assuming root data exists in Inorder)
        int rootIndexInorder=-1;
        for (int i=siIn;i<=eiIn;i++)
        {
            if (rootData==inorder[i])
            {
                rootIndexInorder=i;
                break;
            }
        }

        //Defining index limits for Left Subtree Inorder
        int siInLeft=siIn;
        int eiInLeft=rootIndexInorder-1;

        //Defining the index limits for Left Subtree Preorder
        int siPreLeft=siPre+1;
        int leftSubTreeLength = eiInLeft - siInLeft + 1;
        int eiPreLeft=(siPreLeft)+(leftSubTreeLength-1);

        //Defining index limits for Right Subtree Inorder
        int siInRight=rootIndexInorder+1;
        int eiInRight=eiIn;

        //Defining index limits for Right Subtree Preorder
        int siPreRight=eiPreLeft+1;
        int eiPreRight=eiPre;

        BinaryTreeNode<Integer> leftChild = buildTree(preorder, inorder, siPreLeft, eiPreLeft, siInLeft, eiInLeft);
        BinaryTreeNode<Integer> rightChild = buildTree(preorder, inorder, siPreRight, eiPreRight, siInRight, eiInRight);
        root.left=leftChild;
        root.right=rightChild;
        return root;
    }

    public static void printDetailTree(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data+":");
        if(root.left != null){
            System.out.print("L"+root.left.data+", ");
        }
        if(root.right != null){
            System.out.print("R"+root.right.data);
        }
        System.out.println(" ");

        printDetailTree(root.left);
        printDetailTree(root.right);
    }

    public static boolean searchBST(BinaryTreeNode<Integer> root, int data){
        if (root == null){
            return false;
        }
        if (root.data == data){
            return true;
        }
        if (root.data> data){
            return  searchBST(root.left,data);
        }
        return  searchBST(root.right,data);
    }

    public static void printBetweenK1K2(BinaryTreeNode<Integer> root, int k1, int k2){
        if (root == null){
            return;
        }
        if (root.data < k1){
            printBetweenK1K2(root.right,k1,k2);
        } else if (root.data > k2) {
            printBetweenK1K2(root.left,k1,k2);
        }
        else {
            System.out.println(root.data);
            printBetweenK1K2(root.left,k1,k2);
            printBetweenK1K2(root.right,k1,k2);
        }
    }

    public static void main(String[] args) {
        int pre[] = {4,2,1,3,6,5,7};
        int in[] = {1,2,3,4,5,6,7};
        BinaryTreeNode<Integer> root = buildTree(pre,in);
        printDetailTree(root);
        System.out.println(searchBST(root,20));
       printBetweenK1K2(root,3,6);
    }
}
