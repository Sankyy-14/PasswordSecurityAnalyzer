import java.util.Scanner;
import java.util.Random;

class NPasswordSecurityAnalyzer 
{ 
   // Method to check password strength
   public static int checkPassword(String password) 
    {
        int score = 0;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) 
        {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;
        }

        if (password.length() >= 8) score++;
        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        System.out.println("\nPassword Strength Result:");

        if (score <= 2) 
        {
            System.out.println("Strength: WEAK");
        } 
        else if (score <= 4) 
        {
            System.out.println("Strength: MEDIUM");
        } 
        else 
        {
            System.out.println("Strength: STRONG");
        }
        // Suggestions
        System.out.println("\nSuggestions to improve your password:");

        if (password.length() < 8)
            System.out.println("- Use at least 8 characters");
        if (!hasUpper)
            System.out.println("- Add uppercase letters (A-Z)");
        if (!hasLower)
            System.out.println("- Add lowercase letters (a-z)");
        if (!hasDigit)
            System.out.println("- Add numbers (0-9)");
        if (!hasSpecial)
            System.out.println("- Add special characters (!@#$%^&*)");
        
        // Check if password meets all criteria
        if (password.length() >= 8 && hasUpper && hasLower && hasDigit && hasSpecial)
        {
            System.out.println("- None, Your Password is Perfect!");
        }
        
        // Estimate brute-force time
        estimateBruteForceTime(password, hasUpper, hasLower, hasDigit, hasSpecial);
        return score;
   }

    // Method to estimate brute-force time
   public static void estimateBruteForceTime(String password, boolean upper, boolean lower,boolean digit,boolean special) 
    {
        int charsetSize = 0;
        if (upper) charsetSize += 26;
        if (lower) charsetSize += 26;
        if (digit) charsetSize += 10;
        if (special) charsetSize += 10; // common symbols

        double combinations = Math.pow(charsetSize, password.length());

        // Assume attacker tries 1 billion passwords per second
        double guessesPerSecond = 1_000_000_000;
        double seconds = combinations / guessesPerSecond;

        System.out.println("\nBrute Force Attack Estimation:");
        System.out.printf("Possible combinations: %.2e%n", combinations);

        if (seconds < 60)
            System.out.printf("Estimated crack time: %.2f seconds%n", seconds);
        else if (seconds < 3600)
            System.out.printf("Estimated crack time: %.2f minutes%n", seconds / 60);
        else if (seconds < 86400)
            System.out.printf("Estimated crack time: %.2f hours%n", seconds / 3600);
        else
            System.out.printf("Estimated crack time: %.2f years%n", seconds / 31536000);

        System.out.println("\nCybersecurity Tip:");
        System.out.println("Longer passwords exponentially increase brute-force resistance.");
    }
    // Generate strong password if needed
   public static String generateStrongPassword() 
    {
        Random rand = new Random();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";

        String allChars = upper + lower + digits + special;

        StringBuilder password = new StringBuilder();

        // Ensure strong password rules
        password.append(upper.charAt(rand.nextInt(upper.length())));
        password.append(lower.charAt(rand.nextInt(lower.length())));
        password.append(digits.charAt(rand.nextInt(digits.length())));
        password.append(special.charAt(rand.nextInt(special.length())));

        // Fill remaining characters
        for (int i = 4; i < 10; i++) 
        {
            password.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        return password.toString();
    }

    // Main method
   public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Password Security Assessment & Resilience Analyzer ===");
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        int score = checkPassword(password);

        if (score <= 4) 
        {
            System.out.println("\nYour password is not strong enough.");
            System.out.println("Suggested STRONG Password:");
            System.out.println(generateStrongPassword());
        }

        sc.close();
    }
}