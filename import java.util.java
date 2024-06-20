import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minRange = 1; // Minimum number in the range
        int maxRange = 100; // Maximum number in the range
        int attemptsLimit = 5; // Number of attempts allowed
        
        boolean playAgain = true;
        
        while (playAgain) {
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.printf("I'm thinking of a number between %d and %d. Can you guess what it is?\n", minRange, maxRange);
            
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (attempts < attemptsLimit && !guessedCorrectly) {
                System.out.printf("Attempt %d/%d - Enter your guess: ", attempts + 1, attemptsLimit);
                int guess = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                
                if (guess < randomNumber) {
                    System.out.println("Too low. Try again!");
                } else if (guess > randomNumber) {
                    System.out.println("Too high. Try again!");
                } else {
                    guessedCorrectly = true;
                    System.out.printf("Congratulations! You've guessed the number %d correctly in %d attempts!\n", randomNumber, attempts + 1);
                }
                
                attempts++;
            }
            
            if (!guessedCorrectly) {
                System.out.printf("Sorry, you've run out of attempts. The correct number was %d.\n", randomNumber);
            }
            
            System.out.print("Would you like to play again? (yes/no): ");
            String playAgainResponse = scanner.nextLine().toLowerCase();
            
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
                System.out.println("Thank you for playing!");
            }
        }
        
        scanner.close();
    }
}