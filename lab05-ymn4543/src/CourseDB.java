/**
 * Description: CourseDB implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class CourseDB implements DB<Integer,Course> {
    /** Hashmap that stores Courses with the key being their id */
    public HashMap<Integer,Course> courses;


    /**
     * CourseDB constructor. Creates the database.
     */
    public CourseDB() {
        this.courses  = new HashMap<>();
    }

    /**
     * Adds a value entry to the database
     * The key is based on the course id.
     * @param course the course being added to database
     * @return previous course in same location, null if no prev course.
     */
    public Course addValue(Course course){
        if(courses.get(course.hashCode())!=null){
            Course prev = courses.get(course.hashCode());
            courses.put(course.getId(),course);
            return prev;
        }
        courses.put(course.getId(),course);
        return null;
    }


    /**
     * Gets all the values in the database with
     * elements that are naturally ordered.
     * @return a collection of courses.
     */
    public Collection<Course> getAllValues(){
        ArrayList<Course> courseVals = new ArrayList<>(courses.values());
        Collections.sort(courseVals);
        return courseVals;
    }


    /**
     * Get the value for an associated key.
     * @param id course id, the hash key
     * @return course associated with id, the value
     */
    public Course getValue(Integer id){
        return courses.get(id);
    }


    /**
     * Checks if a key is in the database.
     * @param id key being checked
     * @return boolean
     */
    public boolean	hasKey(Integer id) {
        return courses.containsKey(id);
    }



}
