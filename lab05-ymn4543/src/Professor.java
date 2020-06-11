/**
 * Description: Professor implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */
import java.util.Comparator;

public class Professor extends User {

    /**
     * Creates a new professor.
     * @param username professor's username
     */
    public Professor(String username){
        super(username,UserType.PROFESSOR,COMPARE_COURSES);
    }

    /**
     * A comparator that orders courses first by ascending course level
     * and second alphabetically by course name.
     */
    public static Comparator<Course> COMPARE_COURSES = new Comparator<Course>() {
        public int compare(Course one, Course other) {
            int result =  one.getLevel() - other.getLevel();
            if(result!=0){
                return result;
            }
            else{
                return one.getName().compareTo(other.getName());
            }
        }

    };
}
