package hashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorUse {
    public static void main(String[] args) {
        ArrayList<Integer> c = new ArrayList<>();
        c.add(10);
        c.add(20);
        c.add(30);
        c.add(40);
        c.add(50);

        ListIterator<Integer> d =c.listIterator();
       while (d.hasNext()){
            System.out.println(d.next());
        }
        while (d.hasPrevious()){
            System.out.println(d.previous());
        }
    }
}
