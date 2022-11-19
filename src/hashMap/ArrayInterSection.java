package hashMap;

import java.util.HashMap;

public class ArrayInterSection {
    public static void printInterSection(int arr1[], int arr2[]){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i =0; i<arr1.length;i++){
            if (map.containsKey(arr1[i])){
                int value = map.get(arr1[i]);
                map.put(arr1[i], value+1);
            }else {
                map.put(arr1[i],1);
            }
        }
        for (int i =0; i<arr2.length;i++){
            if (map.containsKey(arr2[i])){
                int freq = map.get(arr2[i]);
                if (freq>0){
                    System.out.print(arr2[i]+ " ");
                    map.put(arr2[i],freq-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr1[] = {2,3,5,4,6,2,6,3,1,5,9,2};
        int arr2[] = {2,3,5,6,5,10,7,3,2,8,9,8};
        printInterSection(arr1,arr2);
    }
}
