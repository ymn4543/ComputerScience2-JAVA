/**
 * Description: CourseComparator implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */

import java.util.Comparator;

/**
 * A class that overrides the natural order comparison of courses to order
 * them alphabetically by course name.
 *
 * @author Youssef Naguib
 */
public class CourseComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        if(o1.getName().compareTo(o2.getName()) == 0){
            return 0;
        }
        if(o1.getName().compareTo(o2.getName()) > 0){
            return 1;
        }
        return -1;

    }
}
