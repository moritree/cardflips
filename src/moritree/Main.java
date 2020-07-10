package moritree;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static String DECKS_DIR = "decks";
    public static Deck[] DECKS;

    public static void main(String[] args) {
        Card card1 = new Card("abc", "def", LocalDateTime.now());
        Card card2 = new Card("ghi", "jkl", null);

        Deck testDeck = new Deck("Test Deck", new Card[]{card1, card2});

        String decksDirPath = "decks";

        File decksDir = new File(decksDirPath);
        if (!decksDir.exists()) decksDir.mkdir();

        testDeck.save(decksDirPath);

        Deck resDeck = new Deck(decksDirPath + "/" + testDeck.name.replace(' ', '_') + ".csv");

        DECKS = new Deck[] {testDeck, resDeck};

        Gui gui = new Gui();
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
