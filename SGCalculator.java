import java.util.Scanner;

public class SGCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int subjectCount = scanner.nextInt();

        int totalMarks = 0;

        for (int i = 1; i <= subjectCount; i++) {
            int marks;

            while (true) {
                System.out.print("Enter marks for subject " + i + " (0 to 100): ");
                marks = scanner.nextInt();

                if (marks >= 0 && marks <= 100) {
                    break;
                } else {
                    System.out.println("Invalid input. Marks must be between 0 and 100.");
                }
            }

            totalMarks += marks;
        }

        double averagePercentage = (double) totalMarks / subjectCount;
        String grade;

        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println();
        System.out.println("Student Result");
        System.out.println("----------------------------");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
