/**
 * Description: User implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class User extends Object implements Comparable<User> {
    /** The courses the professor is teaching, or student is enrolled in */
    private TreeSet<Course> courses;
    /** The user type */
    private User.UserType type;
    /** The unique username of user */
    public String username;

    /**
     * User constructor
     * @param username username of user
     * @param type type of user, either student or professor
     * @param comp set of courses user enrolled in or teaching
     */
    public User(String username,  User.UserType type, Comparator<Course> comp){
        this.courses = new TreeSet<Course>(comp);
        this.username = username;
        this.type = type;
    }

    /**
     * Add a course for this user.
     * Professors add course to the courses they are teaching.
     * Students enroll in the course.
     * @param course course being added
     * @return boolean if course added or not
     */
    public boolean addCourse​(Course course){
        boolean added = false;
        if(getType() == UserType.STUDENT){
            if(!course.getStudents().contains(getUsername())){
                courses.add(course);
                added = true;
            }
        }
        if(getType() == UserType.PROFESSOR) {
            if (course.getProfessor() == null) {
                courses.add(course);
                added = true;
            } else {
                if (!course.getProfessor().equals(getUsername())) {
                    courses.add(course);
                    added = true;
                }
            }
        }

        return added;
        }

    /**
     * Users are naturally ordered alphabetically by username.
     * @param other other user
     * @return int 0 for same, 1 for greater than, -1 for less than
     */
    public int	compareTo(User other){
        if(this.getUsername().compareTo(other.username)==0){
            return 0;
        }
        if(this.getUsername().compareTo(other.username)>0){
            return 1;
        }
        else{
            return -1;
        }
    }

    /**
     * checks if 2 users have same username
     * @param other other user
     * @return boolean
     */
    public boolean equals​(Object other){
        User other1 = (User)other;
        return this.getUsername().equals(other1.getUsername());
    }

    /**
     * Returns the courses the user is currently teaching or enrolled in.
     * @return collection of courses
     */
    public Collection<Course> getCourses(){
        return courses;
    }

    /**
     * returns the type of user
     * @return STUDENT OR PROFESSOR
     */
    public User.UserType getType(){
        return type;
    }

    /**
     * returns user's username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * hashcode function for user, depends on their username.
     * @return int
     */
    public int	hashCode(){
        return username.hashCode();
    }

    /**
     * Removes a course for this user
     * @param course course being removed
     * @return boolean whether course was removed or not
     */
    public boolean	removeCourse​(Course course){
        if(courses.contains(course)){
            courses.remove(course);
            return true;
        }
        return false;
    }

    /**
     * toString override method for user
     * @return String representation of user
     */
    public String toString(){
        return "User{username=" + getUsername() +", type=" + getType() + ", courses=" + getCourses()+"}";
    }

    /**
     * UserType enumeration.
     * 2 types of users: STUDENT, PROFESSOR
     */
   public static enum UserType{
           PROFESSOR,
           STUDENT;
       }

   }

