import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            table.put(new MyTestingClass(id), "Student" + id);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            var node = table.chainArray[i];
            while (node != null) {
                count++;
                node = node.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }

        BST<Integer, String> tree = new BST<>();
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");

        for (var e : tree) {
            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
        }
    }
}