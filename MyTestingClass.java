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
    }
}