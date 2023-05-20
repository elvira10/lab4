# Assignment 4
Implementing a hash table data structure using a chain to resolve collisions. The implementation supports universal key-value pairs and allows the user to add, extract and delete elements from a hash table.
# My Hash table
MyHashTable is a Java implementation of a hash table, a data structure that provides quick access to data based on key-value mapping. This implementation uses a chain for handlecollisions, where a linked list is used to store multiple values having the same hash code.
public class MyHashTable<K, V> {
    private class HashNode<K, V> { //representing a node in the hash table's linked list
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) { //constructor for the HashNode class
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() { //displaying node's key-value pair
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; //number of buckets in the hash table
    private int size; //number of key-value pairs in the hash table

    public MyHashTable() { //constructor for the MyHashTable class
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    public MyHashTable(int M) { //to specify the number of buckets
        this.chainArray = new HashNode[M];
        this.size = 0;
        this.M = M;
    }

    /**
     * calculates the index of a given key in the hash table
     * @param key the key to be hashed
     * @return the index of the key in the hash table
     */
    private int hash(K key) {
        int hashCode = key.hashCode(); //get the hash code of the key
        int index = hashCode % M; //calculate the index using the modulo operator
        if (index >= 0) { //if the index is positive
            return index;
        } else { //if the index is negative, add M to make it positive
            return index + M;
        }
    }

    /**
     * inserts a key-value pair into the hash table
     * @param key   the key to be inserted
     * @param value the value to be inserted
     */
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value; //update the value if the key already exists in the hash table
                return;
            }
            node = node.next;
        } //if not
        HashNode<K, V> newNode = new HashNode<>(key, value); //create a new HashNode
        newNode.next = chainArray[index];
        chainArray[index] = newNode; //add the new node to the beginning of the chain
        size++; //increment the size of the hash table
    }

    /**
     * retrieves the value associated with a given key from the hash table
     * @param key the key whose value is to be retrieved
     * @return the value associated with the key, or null if the key is not in the hash table
     */
    public V get(K key) { //retrieving the value associated with a given key
        int index = hash(key); //calculate the index of the key in the hash table
        HashNode<K, V> node = chainArray[index]; //retrieve the head node of the corresponding chain
        while (node != null) { //traverse the chain to find the node with the given key
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null; //return null if the key is not found
    }

    /**
     * removes the key-value pair with the specified key from the hash table
     * @param key the key of the key-value pair to be removed
     * @return the value associated with the removed key, or null if the key is not in the hash table
     */
    public V remove(K key) {
        int index = hash(key); //calculate the index of the pair based on the hash of the key
        HashNode<K, V> prev = null; //initialize the previous node to null
        HashNode<K, V> curr = chainArray[index]; //initialize the current node to the first node in the chain
        while (curr != null) { //iterate over the chain
            if (curr.key.equals(key)) { //if the current node contains the key to be removed
                if (prev == null) { //if the current node is the first node in the chain
                    chainArray[index] = curr.next; //set the chain head to the next node in the chain
                } else {
                    prev.next = curr.next; //link the previous node to the next node, skipping the current node
                }
                size--; //decrement the size of the hash table
                return curr.value; //return the value of the removed pair
            }
            prev = curr; //move the previous node to the current node
            curr = curr.next; //move the current node to the next node in the chain
        }
        return null; //if the key is not found, return null
    }

    /**
     * checks if the hash table contains a given value
     * @param value the value to be checked
     * @return true if the hash table contains the value, false otherwise
     */
    public boolean contains(V value) { //check if the hash table contains a given value
        for (int i = 0; i < chainArray.length; i++) { //loop through all elements in the chainArray
            HashNode<K, V> node = chainArray[i]; //get the HashNode at the current index i
            while (node != null) { //loop through all the nodes in the HashNode chain
                if (node.value.equals(value)) { //check if the current node contains the given value
                    return true; //return true if the value is found
                }
                node = node.next; //move to the next node in the chain
            }
        }
        return false; //return false if the value is not found in the hash table
    }

    /**
     * returns the key associated with the specified value in the hash table, if the value is present
     * @param value the value to search for in the hash table
     * @return the key associated with the specified value, or null if the value is not present in the hash table
     */

    public K getKey(V value) { //iterate through each chain in the hash table
        for (HashNode<K, V> node : chainArray) { //iterate through each node in the current chain
            while (node != null) {
                if (node.value.equals(value)) { //check if the value of the current node matches the specified value
                    return node.key;
                }
                node = node.next; //move to the next node in the chain
            }
        }
        return null; //if the value is not found, return null
    }

    /**
     * returns an array representing the number of elements in each bucket in the hash table
     * @return an array representing the number of elements in each bucket in the hash table
     */
    public int[] getBucketCount() { //initialize an array to hold the bucket counts
        int[] result = new int[M]; //iterate through each bucket in the hash table
        for (int i = 0; i < M; i++) {
            int length = 0;
            HashNode<K, V> pointer = chainArray[i]; //iterate through each node in the current bucket
            while (pointer != null) {
                pointer = pointer.next;
                length++;
            }
            result[i] = length; //store the number of nodes in the current bucket in the result array

        }
        return result; //return the array of bucket counts
    } }
# My test class
The My Testing class is a simple class that is used as a key in the MyHashTable class. It has three instance variables: id, name and age. The hashCode() method is redefined to generate a unique hash code for each instance based on its ID, name, and age. The equals() method is also redefined to check the equality of two instances based on their ID, name, and age.
public class MyTestingClass {
    private int id;
    private String name;
    private int age;

    public MyTestingClass(int id, String name, int age) { //constructor method to initialize object with given values
        this.id = id;
        this.name = name;
        this.age = age;
    }


    /**
     * calculates the hash code of the object
     * @return the hash code
     */
    public int hashCode() {
        int result = Integer.hashCode(id); //get the hash code of id
        result = 31 * result + name.hashCode(); //add the hash code of name to the result
        result = 31 * result + Integer.hashCode(age); //add the hash code of age to the result
        return result; //return the final hash code
    }


    /**
     * determines whether the object is equal to another object
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) { //check if the objects are the same instance
            return true;
        }
        if (!(obj instanceof MyTestingClass)) { //check if the objects are of the same class
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj; //cast the object to MyTestingClass
        return id == other.id && name.equals(other.name) && age == other.age; //compare the values of the objects
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
