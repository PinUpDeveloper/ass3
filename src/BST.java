import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size = 0;

    public static class Entry<K, V> {
        private final K key;
        private final V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Node curr = root;
        while (true) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                if (curr.left == null) {
                    curr.left = newNode;
                    size++;
                    return;
                }
                curr = curr.left;
            } else if (cmp > 0) {
                if (curr.right == null) {
                    curr.right = newNode;
                    size++;
                    return;
                }
                curr = curr.right;
            } else {
                curr.val = val;
                return;
            }
        }
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.val;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null, curr = root;
        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            curr = key.compareTo(curr.key) < 0 ? curr.left : curr.right;
        }
        if (curr == null) return;

        if (curr.left == null || curr.right == null) {
            Node newChild = (curr.left != null) ? curr.left : curr.right;
            if (parent == null) root = newChild;
            else if (parent.left == curr) parent.left = newChild;
            else parent.right = newChild;
        } else {
            Node successorParent = curr;
            Node successor = curr.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            curr.key = successor.key;
            curr.val = successor.val;
            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }
        size--;
    }

    public int size() {
        return size;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private Stack<Node> stack = new Stack<>();
            private Node current = root;

            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            public Entry<K, V> next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                Entry<K, V> entry = new Entry<>(current.key, current.val);
                current = current.right;
                return entry;
            }
        };
    }
}