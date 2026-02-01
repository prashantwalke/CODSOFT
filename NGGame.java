import java.util.Random;
import java.util.Scanner;

public class NGGame {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;
        boolean playAgain = true;

        System.out.println(" Welcome to the Number Guessing Game!");

        while (playAgain) {
            int secretNumber = random.nextInt(MAX - MIN + 1) + MIN;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\n Round " + round);
            System.out.println("I have chosen a number between " + MIN + " and " + MAX);

            while (attemptsLeft > 0 && !guessedCorrectly) {
                System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");

                if (!input.hasNextInt()) {
                    System.out.println(" Invalid input! Please enter a number.");
                    input.next();
                    continue;
                }

                int guess = input.nextInt();
                attemptsLeft--;

                if (guess == secretNumber) {
                    int attemptsUsed = MAX_ATTEMPTS - attemptsLeft;
                    int roundScore = (MAX_ATTEMPTS - attemptsUsed + 1) * 10;
                    totalScore += roundScore;

                    System.out.println(" Correct! The number was " + secretNumber);
                    System.out.println(" Round score: " + roundScore);
                    guessedCorrectly = true;

                } else if (guess < secretNumber) {
                    System.out.println(" Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" No attempts left. The number was " + secretNumber);
            }

            System.out.println(" Total score: " + totalScore);
            System.out.print("\nPlay another round? (y/n): ");
            playAgain = input.next().equalsIgnoreCase("y");
            round++;
        }

        System.out.println("\n Thanks for playing!");
        System.out.println(" Final score: " + totalScore);
        input.close();
    }
}
