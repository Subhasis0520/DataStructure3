package trees;

import java.util.ArrayList;

public class DynamicTree<T> {
    T data;
    ArrayList<DynamicTree<T>> children;

    public DynamicTree(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}
