package hashMap;

import java.util.HashMap;

public class MaximumTimeOfNumber {

    public static int maxValue(int arr[]){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<arr.length;i++)
        {
            if (map.containsKey(arr[i]))
            {
                int value = map.get(arr[i]);
                map.put(arr[i],value+1);
            }
            else
            {
                map.put(arr[i],1);
            }
        }

        int maxCount =  map.get(arr[0]);
        int maxEle =arr[0];
        for (int i=0;i<arr.length;i++)
        {
            if (map.get(arr[i])>maxCount)
            {
                maxCount=map.get(arr[i]);
                maxEle=arr[i];
            }
        }
        return maxEle;
    }

    public static void main(String[] args) {
        int arr[] = {0, 3, 1, 6, 5, 8, 4, 2};
        System.out.println(maxValue(arr));
    }
}
