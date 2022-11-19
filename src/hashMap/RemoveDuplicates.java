package hashMap;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoveDuplicates {
    public static ArrayList<Integer> removeDuplicate(int[] arr) {
        ArrayList<Integer> output = new ArrayList<>();
        HashMap<Integer,Boolean> map = new HashMap<>();
        for (int i =0; i<arr.length;i++){
            if (map.containsKey(arr[i])){
                continue;
            }
            output.add(arr[i]);
            map.put(arr[i],true);
        }
        return output;
    }


    public static void main(String[] args) {
        int arr[] = {1,2,3,7,9,5,2,4,1,3,2,8};
        ArrayList<Integer> output = removeDuplicate(arr);
        System.out.println(output);
    }

}
