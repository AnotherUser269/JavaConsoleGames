package game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class InputParser {
    private final char delimiter;
    private final BufferedReader br;

    private static final int[] INVALID = new int[]{-1, -1};

    public InputParser(char delimiter) {
        if (Character.isDigit(delimiter)) {
            throw new IllegalArgumentException("Delimiter should not be a digit.");
        }

        this.delimiter = delimiter;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int[] getMove(int[] lastCoords) {
        // Returns coords [int, int]
        // If wrong input, returns [-1, -1]
        String rawInput;

        try {
            rawInput = askForInput(lastCoords);
            if (rawInput == null) return null;

        } catch (IOException e) {
            System.err.println("[ERROR] Failed to read input: " + e.getMessage());
            return null;
        }

        rawInput = rawInput.trim();
        String[] components = rawInput.split(Pattern.quote(String.valueOf(delimiter)));

        if (components.length != 2) {
            System.err.println("[ERROR] Wrong notation provided");
            return INVALID;
        }

        String a = components[0].trim();
        String b = components[1].trim();

        if (a.isEmpty() || b.isEmpty()) {
            System.err.println("[ERROR] Missing coordinate.");
            return INVALID;
        }

        try {
            if (a.length() != 1 || b.length() != 1) {
                throw new NumberFormatException("Not a number or letter");
            }

            int y = Integer.parseInt(a, 36);
            int x = Integer.parseInt(b, 36);

            return new int[]{y, x};
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] Wrong coordinates provided.");
            return INVALID;
        }
    }

    private String askForInput(int[] lastCoords) throws IOException {
        System.out.printf("* Enter source and destination towers for moving the top disk (e.g., \"0%c1\").\n", delimiter);

        if (lastCoords == null) {
            System.out.println("* Last input: None.\n");
        } else {
            System.out.printf(
                    "* Last input: %s%s%s.\n\n",
                    Integer.toString(lastCoords[0], 36),
                    delimiter,
                    Integer.toString(lastCoords[1], 36)
            );
        }

        System.out.print("[INPUT] Your choice: ");

        return br.readLine();
    }

    public void askForStart() {
        System.out.print("Press ENTER to start...");
        try {
            br.readLine();
        } catch (IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }

    public void askForEnd() {
        System.out.print("Press ENTER to exit...");
        try {
            br.readLine();
        } catch (IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }

}
