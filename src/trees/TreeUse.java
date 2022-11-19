package trees;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreeUse {

    

    public static void printTree(DynamicTree<Integer>root){
        if (root == null){
            return;
        }
        System.out.print(root.data + ": ");
        for (int i =0; i< root.children.size(); i++){
            System.out.print("C"+ root.children.get(i).data+" ");
        }
        System.out.println(" ");
        for (int i =0; i< root.children.size(); i++){
            DynamicTree<Integer> child = root.children.get(i);
            printTree(child);
        }
    }

    public static int numberOfNode(DynamicTree<Integer> root){
        int count = 1;
        for (int i =0; i<root.children.size(); i++){
            int childCount = numberOfNode(root.children.get(i));
            count += childCount;
        }
        return count;
    }


    public static int sumOfAllNode(DynamicTree<Integer> root){
        int sum =0;

        Queue<DynamicTree<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty())
        {
            DynamicTree<Integer> frontNode = queue.remove();
            if(frontNode == null){
                if(queue.isEmpty()){
                    break;
                }

                queue.add(null);
            }
            else{
                sum+=frontNode.data;
                for(int i=0;i<frontNode.children.size();i++){
                    queue.add(frontNode.children.get(i));
                }
            }

        }

        return sum;
    }

    public static DynamicTree<Integer> takeInput() throws QueueEmptyException {
        Scanner sc = new Scanner(System.in);
        QueueUsingLL<DynamicTree<Integer>> pendingNode = new QueueUsingLL<>();
        System.out.println("Enter the root data");
        int rootData = sc.nextInt();
        if (rootData == -1){
            return null;
        }
        DynamicTree<Integer> root = new DynamicTree<>(rootData);
        pendingNode.enqueue(root);
        while (!pendingNode.isEmpty()){
            DynamicTree<Integer> front = pendingNode.dequeue();
            System.out.println("Enter the no of child for " + front.data);
            int childSize = sc.nextInt();
            for (int i =0; i<childSize;i++){
                System.out.println("Enter the "+ i+ "th child of "+ front.data+" : ");
                int childData = sc.nextInt();
                DynamicTree<Integer> childNode = new DynamicTree<>(childData);
                front.children.add(childNode);
                pendingNode.enqueue(childNode);
            }

        }
        return root;
    }

    public static int numNodeGreater(DynamicTree<Integer> root,int x){
         if (root == null) {
                 return 0;
               }
        Queue<DynamicTree<Integer>> queue = new LinkedList<>();
        queue.add(root);

        int ans=0;

        while(!queue.isEmpty())
        {
            DynamicTree<Integer> frontNode = queue.remove();
            if(frontNode == null){
                if(queue.isEmpty()){
                    break;
                }

                queue.add(null);
            }else{
                if(x<frontNode.data)
                    ans++;
                for(int i=0;i<frontNode.children.size();i++){
                    queue.add(frontNode.children.get(i));
                }
            }

        }

        return ans;
    }

    public static int getHeight(DynamicTree<Integer> root){
        if (root == null){
            return 0;
        }
        int count =0;
        for (int i =0; i<root.children.size();i++){
            int countChild = getHeight(root.children.get(i));
            if (countChild > count){
                count = countChild;
            }
        }
        return 1+count;
    }

    public static int countLeafNodes(DynamicTree<Integer> root){

        int leaf = 0;
        if (root == null )
        {
            return 0;
        }

        if (root.children.size() == 0)
        {
            return 1;
        }

        for (DynamicTree<Integer> child : root.children)
        {
            leaf += countLeafNodes(child);
        }
        return leaf ;
    }

    public static DynamicTree<Integer> removeLeafNodes(DynamicTree<Integer> root) {
        if(root == null)
            return null;
        // when there are no children of the given root
        if(root.children.size()==0)
            return null;
        for(int i = 0;i<root.children.size();i++)
        {
            DynamicTree<Integer> temp = removeLeafNodes(root.children.get(i));
            if(temp == null)
            {
                root.children.remove(i);
                i--;
            }
        }
        return root;
    }



    public static void main(String[] args) throws QueueEmptyException {
//        DynamicTree<Integer> root = new DynamicTree<>(4);
//        DynamicTree<Integer> node1 = new DynamicTree<>(1);
//        DynamicTree<Integer> node2= new DynamicTree<>(3);
//        DynamicTree<Integer> node3= new DynamicTree<>(2);
//        DynamicTree<Integer> node4 = new DynamicTree<>(5);
//        DynamicTree<Integer> node5 = new DynamicTree<>(6);
//
//        root.children.add(node1);
//        root.children.add(node2);
//        root.children.add(node3);
//
//        node2.children.add(node4);
//        node2.children.add(node5);

        DynamicTree<Integer> root = takeInput();

        printTree(root);
        System.out.println("Numbers of node is: "+numberOfNode(root));
        System.out.println("Sum of the node is: " + sumOfAllNode(root));
        System.out.println(numNodeGreater(root,9));
        System.out.println("no of leaves node: "+countLeafNodes(root));
        removeLeafNodes(root);
    }
}
