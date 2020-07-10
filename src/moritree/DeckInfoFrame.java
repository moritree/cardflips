package moritree;

import javax.swing.*;
import java.awt.*;

public class DeckInfoFrame extends JFrame {
    Deck deck;

    public DeckInfoFrame(Deck deck) {
        this.deck = deck;
        setSize(600, 400);
        setMinimumSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAndShowGui();
    }

    private void createAndShowGui() {
        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(" " + deck.name);
        topBarPanel.add(nameLabel, BorderLayout.WEST);

        JButton studyButton = new JButton("Study");
        topBarPanel.add(studyButton, BorderLayout.EAST);

        add(topBarPanel, BorderLayout.NORTH);
        setVisible(true);
    }
}
