/**
 * Description: Student implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */
import java.util.Comparator;

public class Student extends User {

    /**
     * Creates a new Student.
     * @param username student's username
     */
    public Student(String username){
        super(username, UserType.STUDENT, COMPARE_BY_NAME);
    }

    /**
     * A comparator that orders courses alphabetically by course name.
     */
    public static Comparator<Course> COMPARE_BY_NAME = new Comparator<Course>() {
        public int compare(Course one, Course other) {
            return one.getName().compareTo(other.getName());
        }

    };



}
