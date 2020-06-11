import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


/**
 * The main program and "front end" for the Student Information System.  It
 * is run on the command line in two ways:<br>
 * <br>
 * $ java SIS course-file professor-file student-file<br>
 * $ java SIS course-file professor-file student-file input-file<br>
 * <br>
 * Its job is to create the backend by passing it the course, professor and
 * student input files (see Backend class documentation), and then run
 * an loop reading input (either from standard input (first way of running),
 * or from a file of input commands (second way of running)).<br>
 * <br>
 * The commands the system accepts can be found by running the 'help' command:<br>
 * <br>
 * course {id}: list a course<br>
 * courses: list all courses (by course id)<br>
 * enroll {username} {id}: enroll a student in a course<br>
 * help: this message<br>
 * professor {username}: list courses taught by professor (by course level then by course name)<br>
 * student {username}: list courses taken by student (by course name)<br>
 * unenroll {username} {id}: unenroll a student from a course<br>
 * users: list all users (alphabetically by username) <br>
 * quit: quit SIS<br>
 * <br>
 *
 * @author Sean Strout @ RITCS
 * @author Youssef Naguib
 */
public class SIS {
    /** the course command */
    public final static String COURSE = "course";
    /** the courses command */
    public final static String COURSES = "courses";
    /** the enroll command */
    public final static String ENROLL = "enroll";
    /** the help command */
    public final static String HELP = "help";
    /** the professor command */
    public final static String PROFESSOR = "professor";
    /** the student command */
    public final static String STUDENT = "student";
    /** the unenroll command */
    public final static String UNENROLL = "unenroll";
    /** the users command */
    public final static String USERS = "users";
    /** the quit command */
    public final static String QUIT = "quit";

    // TODO add the backend data member here
    private Backend	backend;


    /**
     * Create the backend.
     *
     * @param courseFile the course file
     * @param professorFile the professor file
     * @param studentFile the student file
     * @throws FileNotFoundException if any of the files cannot be found
     */
    public SIS(String courseFile, String professorFile, String studentFile) throws FileNotFoundException {
        this.backend = new Backend(courseFile,professorFile,studentFile);
    }


    /**
     * A helper method for the enroll command.
     * @param username username of student being enrolled
     * @param courseId course id of course student is enrolling into.
     */
    private void enrollStudent(String username, int courseId){
        if(verifyStudentCourse​(username,courseId)){
            if(backend.getCourse​(courseId).getStudents().contains(username)){
                System.out.println("student "+username+" already enrolled in course");
            }
            else{
                backend.enrollStudent​(username,courseId);
            }
        }
    }

    /**
     * A helper method used by enrollStudent() and unenrollStudent().
     * It verifies that the username exist, the user a student,
     * and that the course exists. Only returns true if all are true.
     * @param username username of user
     * @param courseId id of course
     * @return boolean
     */
    private boolean verifyStudentCourse​(String username, int courseId){
        boolean exists = true;
        if(!backend.userExists​(username)){
            exists = false;
            System.out.println("user "+username+" does not exist!");
            return exists;
        }
        if(!backend.isStudent​(username)){
            exists = false;
            System.out.println("user "+username+" is not a student!");
        }
        if(!backend.courseExists​(courseId)){
            exists = false;
            System.out.println("course "+courseId+" does not exist!");
            return exists;
        }
        return exists;
    }

    /**
     * A helper method for the course command.
     * @param courseId course id
     */
    private void listCourse​(int courseId){
        if(backend.courseExists​(courseId)){
            System.out.println(backend.getCourse​(courseId));
        }
        else{
            System.out.println("course "+courseId+" does not exist!");
        }

    }

    /**
     * A helper method that lists a collection of courses.
     * @param courses
     */
    private void listAllCourses​(Collection<Course> courses){
        for(Course i:courses){
            System.out.println(i);
        }
    }

    /**
     * A helper method for the users command. Lists a collection of users.
     */
    private void listAllUsers() {
        Collection<User> userlist = backend.getAllUsers();
        ArrayList<String> courseNames = new ArrayList<>();

        for (User u : userlist) {
            for(Course c: u.getCourses()){
                courseNames.add(c.getName());
            }
            System.out.println("User{username=" + u.getUsername() + ", type=" + u.getType() + ", courses=" +
                    courseNames + "}");
            courseNames.clear();
        }
    }

    /**
     * A helper method for the professor and student commands.
     * Lists the details for a specific user.
     * @param username username of user
     */
    private void listUser​(String username){
        if(!backend.userExists​(username)){
           System.out.println(username+" does not exist!");
        }
        else{
            listAllCourses​(backend.getCoursesUser​(username));
        }
    }

    /**
     * A helper method for the unenroll command.
     * Removes a student from a course.
     * @param username username of student
     * @param courseId course id
     */
    private void unenrollStudent(String username, int courseId){
        if(verifyStudentCourse​(username,courseId)){
            if(backend.getCoursesUser​(username).contains(backend.getCourse​(courseId))){
                backend.unenrollStudent​(username,courseId);
            }
            else{
                System.out.println("student "+username+" was not enrolled in course "+courseId+"!");
        }

        }
    }


    /**
     * A helper method for displaying the help message.
     */
    private void helpMessage() {
        System.out.println("course {id}: list a course");
        System.out.println("courses: list all courses (by course id)");
        System.out.println("enroll {username} {id}: enroll a student in a course");
        System.out.println("help: this message");
        System.out.println("professor {username}: list courses taught by professor (by course level then by course name)");
        System.out.println("student {username}: list courses taken by student (by course name)");
        System.out.println("unenroll {username} {id}: unenroll a student from a course");
        System.out.println("users: list all users (alphabetically by username) ");
        System.out.println("quit: quit SIS");
    }

    /**
     * The main loop runs through the input commands that 'in' is attached
     * to via the Scanner.  It prompts with '&gt;'.  If the command is valid it calls
     * the appropriate private helper method.  Otherwise it should display the message:<br>
     * <br>
     * Unrecognized command {command}<br>
     * <br>
     * And reprompts the user.
     *
     * @param in a Scanner attached to the input (either stdin or the input file)
     * @param stdin tells whether the scanner is attached to stdin or not.  If
     * not, the input command should be displayed to standard output so it is
     * easier to follow the output.
     */
    public void mainLoop(Scanner in, boolean stdin) {
        if (stdin) {
            System.out.println("Type 'help' for the list of commands.");
        }
        System.out.print("> ");
        // continue looping until the user enters "quit" or there is no more input
        boolean done = false;
        while (!done && in.hasNext()) {
            // read the next command and then call the appropriate method to process it
            String line = in.nextLine();
            if (!stdin) {
                System.out.println(line);
            }
            String fields[] = line.split("\\s+");

            // TODO handle commands here.  With a switch statement remember to put a break
            // in after each case or else one case will "fall into" another!
            switch (fields[0]) {
                case HELP:
                    helpMessage();
                    break;
                case QUIT:
                    done = true;
                    break;
                case COURSE:
                    listCourse​(Integer.parseInt(fields[1]));
                    break;
                case COURSES:
                    listAllCourses​(backend.getAllCourses());
                    break;
                case ENROLL:
                    enrollStudent(fields[1],Integer.parseInt(fields[2]));
                    break;
                case PROFESSOR:
                    listUser​(fields[1]);
                    break;
                case STUDENT:
                    listUser​(fields[1]);
                    break;
                case UNENROLL:
                    unenrollStudent(fields[1],Integer.parseInt(fields[2]));
                    break;
                case USERS:
                    listAllUsers();
                    break;
                default:
                    System.out.println("Unrecognized command " + fields[0]);
            }

            // reprompt
            System.out.print("> ");
        }
    }

    /**
     * The main method.
     *
     * @param args command line arguments (used - see class description)
     * @throws FileNotFoundException if a file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // display a usage message if the number of command line arguments
        // is not correct
        if (args.length < 3 || args.length > 4) {
            System.out.println("Usage: java SIS course-file professor-file student-file [input]");
            return;
        }

        // create the frontend and tie it to the backend
        SIS sis = new SIS(args[0], args[1], args[2]);

        // create a scanner that is tied to either standard input or an input file
        Scanner in;
        boolean stdin = true;
        // if no arguments, tie the scanner to standard input
        if (args.length == 3) {
            in = new Scanner(System.in);
        } else {
            // otherwise tie scanner to a file using the last command
            // line argument as the filename
            in = new Scanner(new File(args[3]));
            stdin = false;
        }

        // enter the main loop
        sis.mainLoop(in, stdin);

        // close the scanner
        in.close();
    }
}
