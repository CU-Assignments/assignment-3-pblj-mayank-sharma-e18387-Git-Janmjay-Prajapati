import java.util.Scanner;
import java.util.HashMap;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

public class UniversityEnrollmentSystem {
    private static final int MAX_STUDENTS = 3;
    private static HashMap<String, Integer> courseEnrollments = new HashMap<>();
    private static HashMap<String, String> prerequisites = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Defining prerequisites
        prerequisites.put("Advanced Java", "Core Java");
        
        System.out.print("Enroll in Course: ");
        String course = scanner.nextLine();
        
        System.out.print("Prerequisite: ");
        String completedCourse = scanner.nextLine();
        
        try {
            enrollStudent(course, completedCourse);
            System.out.println("Enrollment successful in " + course);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    public static void enrollStudent(String course, String completedCourse) throws CourseFullException, PrerequisiteNotMetException {
        if (prerequisites.containsKey(course) && !prerequisites.get(course).equals(completedCourse)) {
            throw new PrerequisiteNotMetException("Complete " + prerequisites.get(course) + " before enrolling in " + course + ".");
        }
        
        courseEnrollments.putIfAbsent(course, 0);
        if (courseEnrollments.get(course) >= MAX_STUDENTS) {
            throw new CourseFullException(course + " is already full.");
        }
        
        courseEnrollments.put(course, courseEnrollments.get(course) + 1);
    }
}
