package moritree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Deck implements Comparable<Deck> {
    String name;
    Card[] cards;

    /**
     * Create a deck from a name and array of cards
     *
     * @param name the name of this deck
     * @param cards an array of the cards that make up the deck
     */
    public Deck(String name, Card[] cards) {
        for (Card card : cards) {
            assert card != null && card.front != null && card.reverse != null;
        }

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
            this.name = nameCsv.substring(0, nameCsv.indexOf('.'));
            this.cards = lines
                    .stream()
                    .map(this::splitQuoteWrappedCsv)
                    .map(x -> new Card(
                            removeQuotes(x[0]),
                            removeQuotes(x[1]),
                            x[2].equals("``") ? null : LocalDateTime.parse(removeQuotes(x[2]))
                    ))
                    .collect(Collectors.toList())
                    .toArray(new Card[lines.size()]);
        } catch(Exception e) {
            throw new RuntimeException("Failed to read file");
        }
    }

    public String getName() { return this.name; }

    public int compareTo(Deck other) {
        return this.name.compareTo(other.name);
    }

    private String[] splitQuoteWrappedCsv(String str) {
        return str.split(",(?=(?:[^`]*`[^`]*`)*[^`]*$)", -1);
    }

    @Override
    public String toString() {
        return Arrays.stream(this.cards).map(Card::toString).reduce("", (x, y) -> x + y + "\n");
    }

    private String removeQuotes(String str) {
        if (str.charAt(0) == '`' && str.charAt(str.length() - 1) == '`') {
            return str.substring(1, str.length() - 2);
        } else throw new RuntimeException("The string is not wrapped in quotation marks, so they cannot be removed.");
    }

    public void addCard(Card card) {
        List<Card> list = Arrays.stream(cards).collect(Collectors.toList());
        list.add(card);
        cards = list.toArray(new Card[list.size()]);
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

    /**
     * When given a specified directory, loads all of the deck .csv files into an array of Decks
     *
     * @param dir The directory from which to load decks
     * @return an array of all of the decks loaded from that directory
     */
    public static Deck[] loadAllDecksFromDirectory(File dir) {
        String[] list = dir.list();
        if (list == null) return null;

        return Arrays.stream(list)
                .map(x -> new Deck(dir + "/" + x))
                .toArray(Deck[]::new);
    }
}
