import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;

    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("no null key");
        }
        return (get(key) != null);

    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("no null key");
        }
        return get(key, root);
    }

    /** helper method for get */
    private V get(K key, Node N) {
        if (N == null ) {
            return null;
        } else if (key.compareTo(N.key) < 0) {
            return get(key, N.left);
        } else if (key.compareTo(N.key) > 0) {
            return get(key, N.right);
        }
        return N.value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size += 1;
    }

    /** helper method for put */
    private Node put(Node N, K key, V value) {
        if (N == null) {
            return new Node(key, value);
        }

        int ind = key.compareTo(N.key);

        if(ind < 0) {
            N.left = put(N.left, key, value);
        } else if (ind > 0) {
            N.right = put(N.right, key, value);
        }  else {
            N.value = value;
        }
        return N;
    }

    /**  prints out your BSTMap in order of increasing Key */
    public void printInOrder() {
        printNode(root);
    }

    /** helper method for print */
    public void printNode(Node n) {
        if(n == null) return;
        printNode(n.left);
        System.out.print(" " + n.value + " ");
        printNode(n.right);
    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        return null;
    }


    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("unsupported method");
    }
}


