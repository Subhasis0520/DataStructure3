package hashMap;

import java.util.ArrayList;

public class Map<K,V> {

    ArrayList<MapNode<K,V>> buckets;
    int count;
    int numBuckets;

    public Map() {
        buckets = new ArrayList<>();
        numBuckets = 10;
        for (int i =0;i<numBuckets;i++){
            buckets.add(null);
        }
    }
    private int getBucketIndex(K key){
        int hc = key.hashCode();
        int index = hc%numBuckets;
        return index;
    }

    public void insert(K key, V value){
         int bucketIndex = getBucketIndex(key);
         MapNode<K,V> head = buckets.get(bucketIndex);
         while (head != null){
             if (head.key.equals(key)){
                 head.value = value;
                 return;
             }
             head = head.next;
         }
         head = buckets.get(bucketIndex);
         MapNode<K,V> newNode = new MapNode<>(key,value);
         newNode.next = head;
         buckets.set(bucketIndex,newNode);
         count++;

         double loadFactor = (1.0*count)/bucketIndex;
         if (loadFactor > 0.7){
             reHash();
         }
    }

    private void reHash() {
        ArrayList<MapNode<K,V>> temp = buckets;
        for (int i =0; i<2*numBuckets;i++){
            buckets.add(null);
        }
        count =0;
        numBuckets = 2*numBuckets;
        for (int i =0;i<temp.size();i++){
            MapNode<K,V> head = buckets.get(i);
            while (head != null){
                K key = head.key;
                V value = head.value;
                insert(key,value);
                head = head.next;
            }
        }
    }

    public int size(){
        return count;
    }

    public V getValue(K key){
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = buckets.get(bucketIndex);
        while (head != null){
            if (head.key.equals(key)){
                return  head.value ;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key){
        int bucketIndex = getBucketIndex(key);
       MapNode<K,V> head = buckets.get(bucketIndex);
        MapNode<K,V> prev = null;
        while (head != null){
            if (head.key.equals(key)){
                if (prev != null) {
                    prev.next = head.next;
                }
                else {
                    buckets.set(bucketIndex,head.next);
                }
                count--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String,Integer> map = new Map<>();
        for (int i =0;i<20;i++){
            map.insert("abc"+i, i+2);
        }
        System.out.println("size of the map is: "+map.size());
        map.remove("abc3");
        map.remove("abc7");
        for (int i =0;i<20;i++){
            System.out.println("abc" +i +" : "+ map.getValue("abc" +i));
        }

    }
}