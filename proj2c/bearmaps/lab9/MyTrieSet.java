package bearmaps.lab9;

import java.util.*;

/**
 * @author yue
 * implements Hash-table based trie
 */

public class MyTrieSet implements TrieSet61B {

    private Node root;

    private class Node {
        private char val;
        private boolean isKey;
        private HashMap<Character, Node> map;


        public Node(char c, boolean key) {
            val = c;
            isKey = key;
            map = new HashMap<>();
    }

    }

    public MyTrieSet() {
        root = new Node('a', false);
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        Node curr = get(key);
        if (curr == null) {
            return false;
        }
        return curr.isKey;
    }


    /** return the last Node of the string */
    private Node get(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException("Please enter a string");
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }


    @Override
    /** Returns a list of all words that start with PREFIX
     * modified form https://algs4.cs.princeton.edu/52trie/TrieST.java.html*/
    public List<String> keysWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        Node last = get(prefix);
        StringBuilder builder = new StringBuilder(prefix);
        builder.deleteCharAt(builder.length()-1);
        collect(last, builder, results);
        return results;

    }

    private void collect(Node x, StringBuilder builder, List<String> results) {
        builder.append(x.val);
        if (x.isKey) {
            results.add(builder.toString());
        }
        for (Node y : x.map.values()) {
            collect(y, builder, results);
        }
        builder.deleteCharAt(builder.length()-1);
    }


    @Override
    /** Clears all items out of Trie */
    public void clear() {
        root = new Node('a', false);
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("not implemented");
    }

    public static void main(String[] args) {
        /* test hashmap

         */

    }
}
