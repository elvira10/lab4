# Assignment 4
Implementing a hash table data structure using a chain to resolve collisions. The implementation supports universal key-value pairs and allows the user to add, extract and delete elements from a hash table.
# My Hash table
MyHashTable is a Java implementation of a hash table, a data structure that provides quick access to data based on key-value mapping. This implementation uses a chain for handlecollisions, where a linked list is used to store multiple values having the same hash code.

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    public MyHashTable(int M) {
        this.chainArray = new HashNode[M];
        this.size = 0;
        this.M = M;
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % M;
        if (index >= 0) {
            return index;
        } else {
            return index + M;
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> curr = chainArray[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    public int[] getBucketCount() {
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            int length = 0;
            HashNode<K, V> pointer = chainArray[i];
            while (pointer != null) {
                pointer = pointer.next;
                length++;
            }
            result[i] = length;
        }
        return result;
    }}
# My test class
The My Testing class is a simple class that is used as a key in the MyHashTable class. It has three instance variables: id, name and age. The hashCode() method is redefined to generate a unique hash code for each instance based on its ID, name, and age. The equals() method is also redefined to check the equality of two instances based on their ID, name, and age.
public class MyTestingClass {
    private int id;
    private String name;
    private int age;

    public MyTestingClass(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + Integer.hashCode(age);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MyTestingClass)) {
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id && name.equals(other.name) && age == other.age;
    }}
# Main
The main class is used to demonstrate the use of the MyHashTable class. It creates an instance of the class, adds 10,000 random items to the hash table, and then retrieves the number of items in each bucket using the getBucketCount() method. This is done by iterating through an array of segments and counting the number of elements in each segment using a pointer that traverses the linked list for each segment.
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> hashTable = new MyHashTable<>();
        Random random = new Random();

        // Add 10,000 random elements to the hash table
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1000);
            String name = "Person" + id;
            int age = random.nextInt(100);
            MyTestingClass key = new MyTestingClass(id, name, age);
            String value = "Value" + i;
            hashTable.put(key, value);
        }

        int[] bucketCount = hashTable.getBucketCount();
        for (int i = 0; i < bucketCount.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketCount[i] + " elements");
        }
    }}
