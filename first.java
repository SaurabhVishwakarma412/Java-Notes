
import java.util.Arrays;
import java.util.Scanner;

// A beginner's guide to Strings in Java.
public class first {
    public static void main(String[] args) {
        // 1. A String stores text. String is a class, so a String is an object.
        String greeting = "Hello";
        String anotherGreeting = new String("Hello");
        System.out.println("Greeting: " + greeting);
        System.out.println("Length: " + greeting.length());
        System.out.println("Empty string length: " + "".length());

        // String objects are immutable: methods return new Strings, not edits to greeting.
        String changedGreeting = greeting.toUpperCase();
        System.out.println("Uppercase copy: " + changedGreeting);
        System.out.println("Original is unchanged: " + greeting);

        // 2. Use equals to compare text. == compares object references, not text content.
        System.out.println("Same text with equals: " + greeting.equals(anotherGreeting));
        System.out.println("Same reference with ==: " + (greeting == anotherGreeting));
        System.out.println("Ignore letter case: " + greeting.equalsIgnoreCase("HELLO"));

        // 3. Character indexes start at 0. substring's end index is not included.
        System.out.println("Character at index 1: " + greeting.charAt(1));
        System.out.println("Characters 0 through 2: " + greeting.substring(0, 3));
        System.out.println("From index 2 to the end: " + greeting.substring(2));

        // 4. Search methods return -1 when the requested text is not found.
        String phrase = "Java makes learning Java fun";
        System.out.println("Contains Java: " + phrase.contains("Java"));
        System.out.println("First Java index: " + phrase.indexOf("Java"));
        System.out.println("Last Java index: " + phrase.lastIndexOf("Java"));
        System.out.println("Missing word index: " + phrase.indexOf("Python"));
        System.out.println("Starts with Java: " + phrase.startsWith("Java"));
        System.out.println("Ends with fun: " + phrase.endsWith("fun"));

        // 5. Transformations also return new Strings. trim removes surrounding basic whitespace.
        String spaced = "  Java  ";
        System.out.println("Trimmed: [" + spaced.trim() + "]");
        System.out.println("Lowercase: " + greeting.toLowerCase());
        System.out.println("Replace characters: " + greeting.replace("l", "m"));
        System.out.println("Replace first regex match: " + greeting.replaceFirst("l", "m"));
        System.out.println("Replace every regex match: " + greeting.replaceAll("l", "m"));
        System.out.println("Join words: " + String.join(" - ", "learn", "Java", "today"));

        // compareTo returns 0 when equal, a negative number when earlier, and positive when later.
        System.out.println("Compare Java with Java: " + "Java".compareTo("Java"));
        System.out.println("Compare ignoring case: " + "java".compareToIgnoreCase("Java"));

        // 6. Convert between Strings and character arrays.
        char[] letters = greeting.toCharArray();
        System.out.println("Character array: " + Arrays.toString(letters));
        String rebuiltGreeting = String.valueOf(letters);
        System.out.println("String from character array: " + rebuiltGreeting);

        // 7. Use StringBuilder when building or changing text repeatedly.
        StringBuilder message = new StringBuilder("Java");
        message.append(" is fun");
        message.insert(0, "Learning ");
        System.out.println("Built message: " + message);
        System.out.println("StringBuilder converted to String: " + message.toString());

        // 8. Read a full line from the console. nextLine can include spaces.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "!");
        scanner.close();
    }
}