package moritree;

import javax.swing.*;
import java.awt.*;

public class DeckInfoFrame extends JFrame {
    Deck deck;

    public DeckInfoFrame(Deck deck) {
        this.deck = deck;
        this.setTitle(deck.name);
        setSize(600, 400);
        setMinimumSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        createAndShowGui();
    }

    private void createAndShowGui() {
        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());

        {
            JLabel nameLabel = new JLabel(" " + deck.name);
            topBarPanel.add(nameLabel, BorderLayout.WEST);

            JButton studyButton = new JButton("Study");
            topBarPanel.add(studyButton, BorderLayout.EAST);
        }

        JPanel cardsListPanel = new JPanel();
        cardsListPanel.setLayout(new BorderLayout());

        {
            JPanel cardsListTopPanel = new JPanel();
            cardsListTopPanel.setLayout(new BorderLayout());

            {
                JTextField searchBarField = new JTextField();

                JButton addDeckButton = new JButton("+");

                cardsListTopPanel.add(searchBarField, BorderLayout.CENTER);
                cardsListTopPanel.add(addDeckButton, BorderLayout.EAST);
            }

            cardsListPanel.add(cardsListTopPanel, BorderLayout.NORTH);
        }

        add(topBarPanel, BorderLayout.NORTH);
        add(cardsListPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
