package moritree;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class Deck {
    String name;
    Card[] cards;

    /**
     * Create a deck from a name and array of cards
     *
     * @param name the name of this deck
     * @param cards an array of the cards that make up the deck
     */
    public Deck(String name, Card[] cards) {
        this.name = name;
        this.cards = cards;
    }

    /**
     * Load deck from a CSV file.
     *
     * @param path the path to the deck's CSV file
     */
    public Deck(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            Vector<String> lines = new Vector<>();
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
            br.close();

            String[] pathParts = path.split("/");
            String nameCsv = pathParts[pathParts.length - 1].replace('_', ' ');
            this.name = nameCsv.substring(0, nameCsv.length() - 4);
            this.cards = lines
                    .stream()
                    .map(x -> x.split(","))
                    .map(x -> new Card(
                            removeQuotes(x[0]),
                            removeQuotes(x[1]),
                            x[2].equals("\"\"") ? null : LocalDateTime.parse(removeQuotes(x[2]))
                    ))
                    .collect(Collectors.toList())
                    .toArray(new Card[lines.size()]);
        } catch(Exception e) {
            throw new RuntimeException("Failed to read file");
        }
    }

    @Override
    public String toString() {
        return Arrays.stream(this.cards).map(Card::toString).reduce("", (x, y) -> x + y);
    }

    private String removeQuotes(String str) {
        if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
            return str.substring(1, str.length() - 2);
        } else throw new RuntimeException("The string is not wrapped in quotation marks, so they cannot be removed.");
    }

    /**
     * Save this deck's information in CSV format into the given directory
     *
     * @param dir the path to the directory in which to write the CSV
     */
    public void save(String dir) {
        try {
            PrintWriter pw = new PrintWriter(
                    (new File(dir + "/" + this.name.replace(' ', '_') + ".csv"))
            );
            pw.write(this.toString());
            pw.close();
        } catch(Exception e) {
            throw new RuntimeException("Write to file failed");
        }
    }

    public static Deck[] loadDecks(File dir) {
        String[] list = dir.list();
        if (list == null) return null;

        return Arrays.stream(list)
                .map(x -> new Deck(dir + "/" + x))
                .collect(Collectors.toList())
                .toArray(new Deck[list.length]);
    }
}
