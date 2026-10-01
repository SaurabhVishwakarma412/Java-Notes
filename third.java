import java.util.Arrays;

// Lesson 3: Java fundamentals used in DSA. Practice: practice-questions.md#3-java-fundamentals.
public class third {
	public static void main(String[] args) {
		// Primitive values store a value directly. int is the usual whole-number type for DSA.
		int count = 7;
		long largeCount = 3_000_000_000L;
		double average = 7.0 / 2;
		boolean ready = count > 0;
		char initial = 'J';
		System.out.println(count + " " + largeCount + " " + average + " " + ready + " " + initial);

		// Integer division discards the fraction. Use a double operand for decimal division.
		System.out.println("Integer division: " + (7 / 2));
		System.out.println("Decimal division: " + (7.0 / 2));
		System.out.println("Remainder: " + (7 % 2));

		// Convert types explicitly when narrowing, because data can be lost.
		double price = 12.8;
		int wholePrice = (int) price;
		System.out.println("Narrowed value: " + wholePrice);

		// if/else chooses which branch to run. Use == for primitive values.
		int score = 82;
		if (score >= 90) {
			System.out.println("Excellent");
		} else if (score >= 60) {
			System.out.println("Passed");
		} else {
			System.out.println("Try again");
		}

		// A for loop is useful when the number of repetitions is known.
		int total = 0;
		for (int number = 1; number <= 5; number++) {
			total += number;
		}
		System.out.println("Sum from 1 to 5: " + total);

		// while loops are useful when repetition depends on a condition.
		int countdown = 3;
		while (countdown > 0) {
			System.out.println(countdown);
			countdown--;
		}

		// && and || short-circuit. Check bounds before using an array index.
		int[] values = {4, 8, 12};
		int index = 1;
		if (index >= 0 && index < values.length) {
			System.out.println("Value at index: " + values[index]);
		}
		System.out.println("Array contents: " + Arrays.toString(values));

		// Use break to stop a loop and continue to skip its current iteration.
		for (int number = 0; number < 6; number++) {
			if (number == 2) {
				continue;
			}
			if (number == 5) {
				break;
			}
			System.out.print(number + " ");
		}
		System.out.println();
	}
}
