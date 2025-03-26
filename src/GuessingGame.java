import java.util.Random;
import java.util.Scanner;

/**
 * Implements command line games with number guessing and rock-paper-scissors
 * @author Lab 3 Partner Group 13 [Puryear, Kwame & Callahan, William]
 * @since 2025-03-24
 */
public class GuessingGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int maxGuesses = 10;
    private static final int numberRange = 100;

    /**
     * Number guessing game: generates a random number between 1 and 100
     * and gives the user a defined number of tries to guess it
     * @author Kwame Puryear
     * @return true if user guesses correctly, false otherwise
     */
    public static boolean guessingGame() {
        Random random = new Random();
        int num = random.nextInt(numberRange) + 1;
        int guesses = maxGuesses;
        System.out.println("I'm thinking of a number between 1 and " + numberRange + ".");
        System.out.println("Guess what it is. You have " + guesses + " tries.");

        for (int i = 0; i < guesses; i++) {
            System.out.print("Your guess: ");
            int guess;

            try {
                guess = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and " + numberRange + ".");
                i--; // Decrement i to not count this as a guess
                continue;
            }

            if (guess == num) {
                System.out.println("You got it!");
                return true;
            } else if (guess < num) {
                System.out.println("Nope! Too low. Try again.");
            } else {
                System.out.println("Nope! Too high. Try again.");
            }
        }
        return false;
    }

    /**
     * Rock-paper-scissors game where the player chooses paper (1), scissors (2), or rock (3)
     * and competes against a random computer choice from the same set of choices
     * @author William Callahan
     * @return true if player wins, false if computer wins or tie
     */
    public static boolean rockPaperScissors() {
        System.out.println("1. Paper | 2. Scissors | 3. Rock");
        try {
            int player = Integer.parseInt(scanner.nextLine());
            if (player < 1 || player > 3) {
                System.out.println("Invalid choice");
                return false;
            }
            
            int computer = new Random().nextInt(3) + 1;
            System.out.printf("Computer chose: %s%n", 
                switch(computer) {
                    case 1 -> "Paper";
                    case 2 -> "Scissors";
                    case 3 -> "Rock";
                    default -> throw new IllegalStateException();
                });
            
            if (player == computer) {
                System.out.println("Tie!");
                return false;
            }
            
            boolean win = (player == 1 && computer == 3) || 
                         (player == 2 && computer == 1) || 
                         (player == 3 && computer == 2);
            System.out.println(win ? "You win!" : "Computer wins!");
            return win;
            
        } catch (NumberFormatException e) {
            System.out.println("Numbers only please");
            return false;
        }
    }

    /**
     * Displays game selection menu and handles user input
     * @author William Callahan
     * @return true to continue playing, false to exit
     */
    private static boolean showMenu() {
        System.out.println("\n=== Game Selection ===");
        System.out.println("1. Number Guessing");
        System.out.println("2. Rock-Paper-Scissors");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1: return playGameSession("guessing");
                case 2: return playGameSession("rps");
                case 3: return false;
                default: System.out.println("Invalid option"); return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Numbers only please");
            return true;
        }
    }

    /**
     * Creates game session and asks if user wants to play again
     * @param gameType the type of game to play ("guessing" or "rps")
     * @return true if user wants to play again, false otherwise
     */
    private static boolean playGameSession(String gameType) {
        boolean result = gameType.equals("guessing") ? 
            guessingGame() : rockPaperScissors();
        
        System.out.println(result ? "You won!" : "Better luck next time!");
        System.out.print("Play again? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    /**
     * Main entry point for the game suite
     * @param args none -- user is prompted to enter a number
     */
    public static void main(String[] args) {
        boolean running = true;
        while(running) {
            running = showMenu();
        }
        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
