package moritree;

import java.io.File;
import java.time.LocalDateTime;

public class CardTesting {
    public static void main(String[] args) {
        Card card1 = new Card("abc", "def", LocalDateTime.now());
        Card card2 = new Card("ghi", "jkl", null);

        Deck testDeck = new Deck("Test Deck", new Card[]{card1, card2});

        String decksDirPath = "decks";

        File decksDir = new File(decksDirPath);
        if (!decksDir.exists()) decksDir.mkdir();

        testDeck.save(decksDirPath);

        Deck resDeck = new Deck(decksDirPath + "/" + testDeck.name.replace(' ', '_') + ".csv");

        System.out.println(resDeck);
    }
}
