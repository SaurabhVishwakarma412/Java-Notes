import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

// Lesson 19: fast token input for programming contests and large input. Practice: practice-questions.md#19-fast-input.
public class nineteenth {
    public static void main(String[] args) throws IOException {
        // StringReader provides sample input so this lesson runs without waiting for the keyboard.
        FastScanner scanner = new FastScanner(new BufferedReader(
                new StringReader("5 12 7 30 4 9")));
        int count = scanner.nextInt();
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += scanner.nextInt();
        }
        System.out.println("Read " + count + " numbers; total = " + total);

        // For a real console solution, construct it with:
        // FastScanner scanner = new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
        // Catch IOException in main or declare 'throws IOException' as this main does.
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(BufferedReader reader) {
            this.reader = reader;
        }

        String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new IOException("No more input");
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}