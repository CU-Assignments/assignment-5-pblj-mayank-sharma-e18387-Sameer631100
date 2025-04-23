import java.util.ArrayList;
import java.util.Scanner;

public class SumWithAutoboxing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();
        String[] tokens = input.split("\\s+");

        // Autoboxing: Convert strings to Integer objects
        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token); // parsing string to int
                numbers.add(num); // autoboxing: int to Integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + token);
            }
        }

        // Unboxing: Integer to int while summing
        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // unboxing happens here
        }

        System.out.println("Sum of numbers: " + sum);
    }
}
