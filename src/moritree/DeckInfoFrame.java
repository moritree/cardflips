package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckInfoFrame extends JFrame {
    private Deck deck;
    private String searchString = "";
    private DeckInfoFrame f;

    public DeckInfoFrame(Deck deck) {
        this.deck = deck;
        this.setTitle(deck.name);
        setSize(600, 400);
        setMinimumSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        createAndShowGui();
        f = this;
    }

    private void createAndShowGui() {
        JPanel p = new JPanel();
        p.setName("p");
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(5, 5, 5, 5));

        {
            JPanel topBarPanel = new JPanel();
            topBarPanel.setLayout(new BorderLayout());

            {
                JButton studyButton = new JButton("Study");
                studyButton.setPreferredSize(new Dimension(topBarPanel.getWidth(), 50));
                topBarPanel.add(studyButton, BorderLayout.CENTER);
            }

            JPanel cardsListPanel = new JPanel();
            cardsListPanel.setName("cardsListPanel");
            cardsListPanel.setLayout(new BorderLayout());

            {
                JPanel cardsListMainPanel = new JPanel();
                cardsListMainPanel.setLayout(new BoxLayout(cardsListMainPanel, BoxLayout.Y_AXIS));

                {
                    fillCardsListMainPanel(cardsListMainPanel);
                }

                JPanel cardsListTopPanel = new JPanel();
                cardsListTopPanel.setLayout(new BorderLayout());

                {
                    JTextField searchBarField = new JTextField();
                    searchBarField.addActionListener(e -> {
                        searchString = searchBarField.getText();
                        System.out.println(searchBarField.getText());
                    });

                    JButton addDeckButton = new JButton("+");

                    cardsListTopPanel.add(searchBarField, BorderLayout.CENTER);
                    cardsListTopPanel.add(addDeckButton, BorderLayout.EAST);
                }

                JScrollPane scrollPane = new JScrollPane(cardsListMainPanel);
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

    private void fillCardsListMainPanel(JPanel p) {
        Card[] cards = Arrays.stream(deck.cards)
                .filter(x -> x.front.contains(searchString) || x.reverse.contains(searchString))
                .toArray(Card[]::new);

        for (int i = 0; i < cards.length; i ++) {
            Card card = cards[i];

            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(p.getPreferredSize().width, 20));
            cell.setMinimumSize(new Dimension(p.getPreferredSize().width, 20));
            cell.setLayout(new BorderLayout());

            JLabel cardFrontLabel = new JLabel(card.front);
            cardFrontLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cardFrontLabel.setName(card.front);

            JLabel cardBackLabel = new JLabel(card.reverse);
            cardBackLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cardBackLabel.setName(card.reverse);

            cell.add(cardFrontLabel, BorderLayout.WEST);
            cell.add(cardBackLabel, BorderLayout.EAST);

            if (i % 2 == 0) {
                cell.setBackground(Color.WHITE);
            }

            cell.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    JPanel c = (JPanel) e.getSource();
                    Arrays.stream(c.getComponents())
                            .filter(x -> x instanceof JLabel)
                            .forEach(x -> {
                                JLabel label = (JLabel)x;

                                label.setText(label.getName());
                                Graphics g = c.getGraphics();
                                FontMetrics met = g.getFontMetrics();
                                int width = met.stringWidth(label.getText());

                                while (width >= c.getWidth() / 2 - 10 && label.getText().length() > 3) {
                                    String text = label.getText();
                                    label.setText(text.substring(0, text.length() - 4) + "...");
                                    width = met.stringWidth(label.getText());
                                }
                            });
                    super.componentResized(e);
                }
            });

            p.add(cell);
        }
    }
}
