package moritree;

import javax.swing.*;
import java.util.Arrays;

public class StatisticsPanel extends JPanel {
    public StatisticsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Deck[] decks = Main.getDECKS();

        String text =
                "<html>"
                    + "<h1>Decks</h1>"
                    + "<p>"
                        + "You have <b>" + Arrays.stream(decks).map(x -> x.cards.length).reduce(0, Integer::sum)
                        + "</b> cards in <b>" + decks.length + "</b> decks.<br>"
                    + "</p>"
                + "</html>";
        JLabel textLabel = new JLabel(text);
        add(textLabel);

        JPanel spacingPanel = new JPanel();
        spacingPanel.setPreferredSize(getPreferredSize());
        add(spacingPanel);
    }
}
