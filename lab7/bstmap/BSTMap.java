package bstmap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yang
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size;

    private Entry root;

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(this.root, key) != null;
    }

    @Override
    public V get(K key) {
        Entry res = get(this.root, key);
        return res != null ? res.value : null;
    }

    private Entry get(Entry node, K key) {
        if (node == null) {
            return null;
        } else if (node.key.compareTo(key) > 0) {
            return get(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        this.root = put(this.root, key, value);
    }

    private Entry put(Entry node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Entry(key, value);
        }

        if (node.key.compareTo(key) == 0) {
            node.value = value;
        } else if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }

        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new BstMapIterator();
    }

    private class BstMapIterator implements Iterator<K> {

        private final Queue<K> keyQueue = new LinkedList<>();

        public BstMapIterator() {
            Entry root = BSTMap.this.root;
            init(root);
        }

        private void init(Entry root) {
            if (root == null) {
                return;
            }
            init(root.left);
            this.keyQueue.add(root.key);
            init(root.right);
        }

        @Override
        public boolean hasNext() {
            return !this.keyQueue.isEmpty();
        }

        @Override
        public K next() {
            return this.keyQueue.remove();
        }

    }

    private class Entry {

        K key;

        V value;

        Entry left;

        Entry right;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
