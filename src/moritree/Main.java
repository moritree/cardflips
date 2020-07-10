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

        DECKS = new Deck[] {testDeck, testDeck, testDeck, testDeck, testDeck, testDeck};

        Gui gui = new Gui();
    }
}
