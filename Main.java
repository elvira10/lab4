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
    }
}