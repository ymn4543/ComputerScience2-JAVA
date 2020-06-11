/**
 * Description: Backend implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Backend extends Object {
    /** the database of courses */
    private CourseDB courseDB;
    /** the database of users */
    private UserDB userDB;

    /**
     * Backend Constructor. Creates the backend by initializing
     * the course and user databases.
     * @param courseFile the input file for courses
     * @param professorFile the input file for professors
     * @param studentFile the input file for students
     * @throws FileNotFoundException
     */
    public Backend(String courseFile, String professorFile, String studentFile)throws FileNotFoundException{
        courseDB = new CourseDB();
        userDB = new UserDB();
        initializeCourseDB​(courseFile);
        initializeUserDB​(professorFile,studentFile);
        }

    /**
     * A method used by initializeUserDB when adding a professor or student
     * to a collection of courses.
     * @param user the user
     * @param courseIds collection of the course ids
     */
    private void addCourses​(User user, String[] courseIds){
        for(String c:courseIds){
            user.addCourse​(courseDB.courses.get(Integer.parseInt(c)));
        }
    }

    /**
     * Check whether a course exists or not.
     * @param id the course id
     * @return boolean
     */
    public boolean courseExists​(int id){
        return courseDB.courses.containsKey(id);
    }

    /**
     * Enrolls a student in a course. The student is added to the course and
     * the course is added to he student's courses.
     * @param username students username
     * @param courseId course id
     * @return boolean if enrolled or not
     */
    public boolean	enrollStudent​(String username, int courseId){
        boolean en = false;
        if(courseDB.courses.get(courseId).getStudents().contains(username)){
            return en;
        }
        else {
            userDB.getValue(username).addCourse​(courseDB.courses.get(courseId));
            courseDB.courses.get(courseId).addStudent(username);

            en = true;
        }
        return en;
    }


    /**
     * Get all the courses in the system.
     * @return collection of courses.
     */
    public Collection<Course> getAllCourses(){
        return courseDB.courses.values();
    }

    /**
     * Get all users in the system.
     * @return collection of users
     */
    public Collection<User>	getAllUsers(){
        return userDB.getAllValues();
    }

    /**
     * gets a course by id.
     * @param id course id
     * @return a course
     */
    public Course getCourse​(int id){
        return courseDB.courses.get(id);

    }

    /**
     * Get a user's courses
     * @param username user's username
     * @return user's courses
     */
    public Collection<Course> getCoursesUser​(String username){
        return userDB.getValue(username).getCourses();
    }


    /**
     * A utility method for initializing the course database.
     * @param courseFile the name of the course file
     * @throws FileNotFoundException
     */
    private void initializeCourseDB​(String courseFile) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(courseFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                courseDB.addValue(new Course(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2])));
                // fields[0] is the course id, as a String
                // fields[1] is the course name, as a String
                // fields[2] is the course level, as a String
            }

        }
    }

    /**
     * A utility method for initializing the user database.
     * @param professorFile the name of the professor file
     * @param studentFile the name of the student file
     * @throws FileNotFoundException
     */
    private void initializeUserDB​(String professorFile, String studentFile)throws FileNotFoundException{
        try (Scanner in = new Scanner(new File(professorFile))) {

            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                int l = fields.length;
                userDB.addValue(new Professor(fields[0]));
                addCourses​(userDB.getValue(fields[0]), Arrays.copyOfRange(fields,1,l));
                // fields[0] is the username, as a String
                for(String n :Arrays.copyOfRange(fields,1,l)){
                    Course a = courseDB.getValue(Integer.parseInt(n));
                    a.addProfessor(userDB.getValue(fields[0]).username);
                }

            }

        }

            try (Scanner in = new Scanner(new File(studentFile))) {
                while (in.hasNext()) {
                    String[] fields = in.nextLine().split(",");
                    int l = fields.length;
                    userDB.addValue(new Student(fields[0]));
                    addCourses​(userDB.getValue(fields[0]),Arrays.copyOfRange(fields,1,l));
                    for(String m :Arrays.copyOfRange(fields,1,l)){
                        Course c = courseDB.getValue(Integer.parseInt(m));
                        c.addStudent(userDB.getValue(fields[0]).username);
                    }
                    // fields[0] is the username, as a String
                }


            }
    }

    /**
     * Check whether a student exists.
     * @param username username of student being checked
     * @return boolean
     */
    public boolean	isStudent​(String username){
        boolean isStu = false;
        if(userDB.getAllValues().contains(userDB.getValue(username))){
            if(userDB.getValue(username).getType().equals(User.UserType.STUDENT)){
                isStu = true;
            }
        }
        return isStu;
    }

    /**
     * Unenroll a student from a course.
     * This requires the student to be removed from the course,
     * and the couse to be removed from the student's courses.
     * @param username student's username
     * @param courseId course's id
     * @return whether the student was unenrolled or not
     */
    public boolean	unenrollStudent​(String username, int courseId){
        boolean we = false;
        if(courseDB.courses.get(courseId).getStudents().contains(username)){
            we = true;
            courseDB.courses.get(courseId).removeStudent(username);
            userDB.getValue(username).removeCourse​(courseDB.courses.get(courseId));
        }
        return we;
    }

    /**
     * Checks if a username exists.
     * @param username username
     * @return boolean
     */
    public boolean	userExists​(String username){
        return userDB.getAllValues().contains(userDB.getValue(username));

    }








}
