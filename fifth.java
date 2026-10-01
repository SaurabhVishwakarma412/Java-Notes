// Lesson 5: recursion and how to reason about recursive methods.
public class fifth {
    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
        System.out.println("Sum 1..5 = " + sumTo(5));
        System.out.println("Fibonacci at index 8 = " + fibonacci(8));
        System.out.println("Digits in 12345 = " + digitCount(12345));
    }

    // Every recursive method needs a base case and progress toward that case.
    public static long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be non-negative");
        }
        if (number <= 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }

    public static int sumTo(int number) {
        if (number <= 0) {
            return 0;
        }
        return number + sumTo(number - 1);
    }

    // This simple version repeats work and takes exponential time; improve it with DP later.
    public static int fibonacci(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be non-negative");
        }
        if (index <= 1) {
            return index;
        }
        return fibonacci(index - 1) + fibonacci(index - 2);
    }

    public static int digitCount(int number) {
        if (number > -10 && number < 10) {
            return 1;
        }
        return 1 + digitCount(number / 10);
    }
}