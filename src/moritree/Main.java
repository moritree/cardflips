package moritree;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Deck[] DECKS;
    private static final boolean TESTING = true;
    private static final String DECKS_DIR = "decks";

    public static void main(String[] args) {
        File decksDir = new File(DECKS_DIR);
        if (!decksDir.exists()) decksDir.mkdir();

        DECKS = TESTING ? generateSampleDecks() : Deck.loadAllDecksFromDirectory(new File(DECKS_DIR));
        if (DECKS != null) Arrays.sort(DECKS, Comparator.comparing(Deck::getName));

        Gui gui = new Gui();
    }

    public static String getDecksDir() {
        return DECKS_DIR;
    }

    public static Deck[] getDECKS() {
        return DECKS;
    }

    public static boolean isTESTING() {
        return TESTING;
    }

    private static Deck[] generateSampleDecks() {
        Card[] sampleCards = {
                new Card("abcabcabcabcabcabcabcabc", "defdefdefdefdefdefdefdef", LocalDateTime.now()),
                new Card("ghi", "jkl", null),
                new Card("mnomnomnomnomnomnomnomnomnomnomno", "pqrpqr", null),
                new Card("stustustu", "vwxvwxvwxvwxvwxvwxvwxvwx", LocalDateTime.now())
        };

        Deck[] decks = new Deck[(int)Math.ceil(Math.random() * 20)];
        for (int i = 0; i < decks.length; i++) {
            Card[] cards = new Card[(int)Math.ceil(Math.random() * 50)];
            for(int j = 0; j < cards.length; j ++) {
                int nextInt = (int)Math.floor(Math.random() * (sampleCards.length));
                cards[j] = sampleCards[nextInt];
            }
            decks[i] = new Deck("Deck " + i, cards);
        }

        return decks;
    }
}
