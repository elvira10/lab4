public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
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
    private int hash(K key){
        int hashCode = key.hashCode();
        int index = hashCode%M;
        if (index>=0){
            return index;
        }
        else{
            return index+M;
        }
    }

}