public class MyTestingClass {
    private int id;
    private String name;
    private int age;

    public MyTestingClass(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public int HashCode() {
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
    }
}