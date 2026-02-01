import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private String department;

    public Student(String name, int rollNumber, String grade, String department) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.department = department;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String toString() {
        return rollNumber + " | " + name + " | " + grade + " | " + department;
    }
}

public class SMGTSystem {

    private static final String FILE_NAME = "students.dat";
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public SMGTSystem() {
        loadFromFile();
    }

    public void addStudent() {
        System.out.print("Enter roll number: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        if (findStudent(roll) != null) {
            System.out.println("Student already exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        System.out.print("Enter department: ");
        String dept = scanner.nextLine();

        if (name.isEmpty() || grade.isEmpty() || dept.isEmpty()) {
            System.out.println("Fields cannot be empty.");
            return;
        }

        students.add(new Student(name, roll, grade, dept));
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public void removeStudent() {
        System.out.print("Enter roll number: ");
        int roll = scanner.nextInt();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(s);
        saveToFile();
        System.out.println("Student removed.");
    }

    public void editStudent() {
        System.out.print("Enter roll number: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new grade: ");
        String grade = scanner.nextLine();

        System.out.print("Enter new department: ");
        String dept = scanner.nextLine();

        if (!name.isEmpty()) s.setName(name);
        if (!grade.isEmpty()) s.setGrade(grade);
        if (!dept.isEmpty()) s.setDepartment(dept);

        saveToFile();
        System.out.println("Student updated.");
    }

    public void searchStudent() {
        System.out.print("Enter roll number: ");
        int roll = scanner.nextInt();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(s);
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private Student findStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                return s;
            }
        }
        return null;
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (Exception ignored) {}
    }

    private void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) in.readObject();
        } catch (Exception ignored) {}
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> editStudent();
                case 4 -> searchStudent();
                case 5 -> displayAllStudents();
                case 6 -> {
                    System.out.println("Exiting system.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void main(String[] args) {
        new SMGTSystem().start();
    }
}
