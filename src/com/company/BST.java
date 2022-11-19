package com.company;

class BSTImplement {
    private BinaryTreeNode<Integer> root;
    private int size;

   private static boolean isPresentHelper(BinaryTreeNode<Integer> node, int x){
       if (node == null){
           return false;
       }
       if (node.data == x){
           return true;
       }
       if (node.data>x){
           return isPresentHelper(node.left,x);
       }else {
           return isPresentHelper(node.right,x);
       }
   }
    public boolean isPresent(int x){
        return isPresentHelper(root,x);
    }

    private static int minimum(BinaryTreeNode<Integer> root){
        if (root == null){
            return Integer.MAX_VALUE;
        }
        int leftMin = minimum(root.left);
        int rightMin = minimum(root.right);

        return Math.min(root.data,Math.min(leftMin,rightMin));
    }


    private static BinaryTreeNode<Integer> insertHelper(BinaryTreeNode<Integer> node, int x){
       if (node == null){
           BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<>(x);
           return newRoot;
       }
       if (x<= node.data){
          node.left = insertHelper(node.left,x);
       }else {
          node.right = insertHelper(node.right,x);
       }
       return node;
    }
    public void insertData(int x){
       root = insertHelper(root,x);
       size++;
    }

    private static BSTDeleteReturn deleteHelper(BinaryTreeNode<Integer> root, int x){
   if (root == null){
       return new BSTDeleteReturn(null,false);
   }

   if (root.data < x){
       BSTDeleteReturn outputRight = deleteHelper(root.right,x);
       root.right = outputRight.root;
       outputRight.root = root;
       return outputRight;
   }
        if (root.data > x){
            BSTDeleteReturn outputLeft = deleteHelper(root.left,x);
            root.left = outputLeft.root;
            outputLeft.root = root;
            return outputLeft;
        }
        if (root.left == null && root.right == null){
            return new BSTDeleteReturn(null,true);
        }
        if (root.left != null && root.right == null){
            return new BSTDeleteReturn(root.left,true);
        }
        if (root.left == null && root.right != null){
            return new BSTDeleteReturn(root.right,true);
        }
        int rightMin = minimum(root.right);
        root.data = rightMin;
        BSTDeleteReturn outputRight = deleteHelper(root.right,x);
        root.right = outputRight.root;
        return new BSTDeleteReturn(root,true);
    }

    public boolean deleteData(int x){
        BSTDeleteReturn output = deleteHelper(root,x);
        root = output.root;
        if (output.deleted){
            size--;
        }
        return output.deleted;
    }

    public int size(){
        return size;
    }


    private static void printTreeHelper(BinaryTreeNode<Integer> node){

        if(node == null) {
            return;
        }
        System.out.print(node.data+":");
        if(node.left != null){
            System.out.print("L"+node.left.data+", ");
        }
        if(node.right != null){
            System.out.print("R"+node.right.data);
        }
        System.out.println(" ");

        printTreeHelper(node.left);
        printTreeHelper(node.right);
    }
    public void printTree(){
       printTreeHelper(root);
    }
}


public class BST{
    public static void main(String[] args) {
        BSTImplement b = new BSTImplement();

        b.insertData(5);
        b.insertData(2);
        b.insertData(7);
        b.insertData(1);
        b.insertData(3);
        b.insertData(6);
        b.insertData(8);

        b.printTree();

        System.out.println(b.size());
        System.out.println(b.isPresent(5));
        System.out.println(b.deleteData(5));
        System.out.println(b.isPresent(5));

        b.printTree();
    }

}
