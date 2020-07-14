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
                new Card("私", "わたし", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("僕", "ぼく", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("君", "きみ", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("お前", "おまえ", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("男", "おとこ", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("赤ん坊", "あかんぼ", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("子", "こ", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("お前はもう死んでいる", "おまえはもうしんでいる", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("女", "おんな", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("I/me/my", "Ich/mich/mein", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("You/you/your (informal)", "Du/dich/dein", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("You/you/your (formal)", "Sie/sie/ihr", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("Woman", "die Frau", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("Man", "der Mann", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("Child", "das Kind", Math.random() > 0.5 ? LocalDateTime.now() : null),
                new Card("You are small and clean", "Du bist klein und sauber", Math.random() > 0.5 ? LocalDateTime.now() : null),
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
