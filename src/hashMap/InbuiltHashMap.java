package hashMap;

import java.util.HashMap;
import java.util.Set;

public class InbuiltHashMap {
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();

        map.put("abc",10);
        map.put("def",13);
        map.put("ghi",17);
//        presence
        if (map.containsKey("abc")){
            System.out.println("Is present abc");
        }
        if (map.containsKey("abc1")){
            System.out.println("Is present abc1");
        }
        if (map.containsValue(13)){
            System.out.println("Value is present");
        }
//         Get value
        int v1 = map.get("def");
        System.out.println(v1);
        System.out.println(map);
//        remove
        map.remove("abc");
        System.out.println(map);
//        size
        System.out.println(map.size());
        System.out.println(map.isEmpty());
//        iterate
        Set<String> key = map.keySet();
        for (String s : key){
            System.out.println(s);
        }
    }
}
