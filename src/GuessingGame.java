import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void guessingGame() {
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        int guesses = 10;
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Guess what it is. You have " + guesses + " tries.");

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < guesses; i++) {
            System.out.print("Your guess: ");
            int guess;

            try {
                guess = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 100.");
                i--; // Decrement i to not count this as a guess
                continue;
            }

            if (guess == num) {
                System.out.println("You got it!");
                break;
            } else if (guess < num) {
                System.out.println("Nope! Too low. Try again.");
            } else {
                System.out.println("Nope! Too high. Try again.");
            }

            guesses--;
            if (guesses == 0) {
                System.out.println("You lost! The number was " + num);
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        guessingGame();
    }
}
