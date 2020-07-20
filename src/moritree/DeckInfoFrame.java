package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DeckInfoFrame extends JFrame {
    private final Deck deck;
    private String searchString = "";
    private CardsListPanel cardsListMainPanel;
    private JScrollPane scrollPane;

    public DeckInfoFrame(Deck deck) {
        this.deck = deck;
        this.setTitle(deck.name);
        setSize(600, 400);
        setMinimumSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        createAndShowGui();
    }

    private void createAndShowGui() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(5, 5, 5, 5));

        {
            JPanel topBarPanel = new JPanel();
            topBarPanel.setLayout(new BorderLayout());

            {
                JButton studyButton = new JButton("Study");
                studyButton.setPreferredSize(new Dimension(topBarPanel.getWidth(), 50));
                studyButton.addActionListener(e -> new StudyFrame(deck));

                topBarPanel.add(studyButton, BorderLayout.CENTER);
            }

            JPanel cardsListPanel = new JPanel();
            cardsListPanel.setName("cardsListPanel");
            cardsListPanel.setLayout(new BorderLayout());

            {
                cardsListMainPanel = new CardsListPanel(this.deck.cards, searchString);

                JPanel cardsListTopPanel = new JPanel();
                cardsListTopPanel.setLayout(new BorderLayout());

                {
                    JTextField searchBarField = new JTextField();
                    searchBarField.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            searchString = searchBarField.getText();
                            cardsListMainPanel = new CardsListPanel(
                                    Arrays.stream(deck.cards)
                                            .filter(x ->
                                                    x.front.toUpperCase().contains(searchString.toUpperCase()) ||
                                                            x.reverse.toUpperCase().contains(searchString.toUpperCase()))
                                            .toArray(Card[]::new),
                                    searchString
                            );
                            scrollPane.setViewportView(cardsListMainPanel);
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) { insertUpdate(e); }

                        @Override
                        public void changedUpdate(DocumentEvent e) { insertUpdate(e); }
                    });

                    JButton addCardButton = new JButton("+");
                    addCardButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Card c = new Card("", "", null);
                            deck.addCard(c);
                            new CardInfoFrame(c);
                        }
                    });

                    cardsListTopPanel.add(searchBarField, BorderLayout.CENTER);
                    cardsListTopPanel.add(addCardButton, BorderLayout.EAST);
                }

                scrollPane = new JScrollPane(cardsListMainPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                cardsListPanel.add(cardsListTopPanel, BorderLayout.NORTH);
                cardsListPanel.add(scrollPane, BorderLayout.CENTER);
            }

            p.add(topBarPanel, BorderLayout.NORTH);
            p.add(cardsListPanel, BorderLayout.CENTER);
        }

        add(p, BorderLayout.CENTER);
        setVisible(true);
    }
}
