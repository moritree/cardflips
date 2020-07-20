package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudyFrame extends JFrame {
    private Deck deck;
    private Card[] dueCards;
    private Card[] doneCards;

    public StudyFrame(Deck deck) {
        this.deck = deck;
        List<Card> dueList = Arrays.stream(deck.cards)
                .filter(x -> x.due != null && x.due.isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        Collections.shuffle(dueList);
        dueCards = dueList.toArray(Card[]::new);
        setMinimumSize(new Dimension(300, 200));
        setPreferredSize(new Dimension(600, 400));
        createAndShowFrontGui();
    }

    private void createAndShowFrontGui() {
        setLayout(new BorderLayout());

        JPanel mainCardPanel = new JPanel();
        mainCardPanel.setLayout(new BorderLayout());

        {
            JLabel mainTextLabel = new JLabel("<html>" + dueCards[0].front + "</html>");
            mainTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainTextLabel.setVerticalAlignment(SwingConstants.CENTER);
            mainCardPanel.add(mainTextLabel, BorderLayout.CENTER);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setViewportView(mainCardPanel);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomControlPanel = new JPanel();
        bottomControlPanel.setLayout(new FlowLayout());

        {
            JPanel bottomMiddlePanel = new JPanel();
            bottomMiddlePanel.setLayout(new BorderLayout());

            {
                JButton flipButton = new JButton("Flip card");
                flipButton.addActionListener(e -> {
                    getContentPane().removeAll();
                    createAndShowBackGui();
                    getContentPane().repaint();
                });
                JLabel infoLabel = new JLabel(
                        "<html>"
                                + "<FONT COLOR=\"BB513A\">"
                                    + dueCards.length
                                + "</FONT>"
                        + "</html>");
                infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                infoLabel.setVerticalAlignment(SwingConstants.CENTER);

                bottomMiddlePanel.add(infoLabel, BorderLayout.NORTH);
                bottomMiddlePanel.add(flipButton, BorderLayout.CENTER);
            }

            bottomControlPanel.add(bottomMiddlePanel);
        }

        add(bottomControlPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void createAndShowBackGui() {
        setLayout(new BorderLayout());

        JPanel mainCardPanel = new JPanel();
        mainCardPanel.setLayout(new BorderLayout());

        {
            JLabel mainTextLabel = new JLabel("<html>" + dueCards[0].reverse + "</html>");
            mainTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainTextLabel.setVerticalAlignment(SwingConstants.CENTER);
            mainCardPanel.add(mainTextLabel, BorderLayout.CENTER);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setViewportView(mainCardPanel);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomControlPanel = new JPanel();
        bottomControlPanel.setLayout(new FlowLayout());

        {
            JPanel againPanel = new JPanel();
            againPanel.setLayout(new BoxLayout(againPanel, BoxLayout.Y_AXIS));
            {
                JLabel timeLabel = new JLabel("1m");
                JButton againButton = new JButton("Again");

                againPanel.add(timeLabel);
                againPanel.add(againButton);
            }

            JPanel hardPanel = new JPanel();
            hardPanel.setLayout(new BoxLayout(hardPanel, BoxLayout.Y_AXIS));
            {
                JLabel timeLabel = new JLabel("10m");
                JButton hardButton = new JButton("Hard");

                hardPanel.add(timeLabel);
                hardPanel.add(hardButton);
            }

            JPanel goodPanel = new JPanel();
            goodPanel.setLayout(new BoxLayout(goodPanel, BoxLayout.Y_AXIS));
            {
                JLabel timeLabel = new JLabel("1d");
                JButton goodButton = new JButton("Good");

                goodPanel.add(timeLabel);
                goodPanel.add(goodButton);
            }

            bottomControlPanel.add(againPanel);
            bottomControlPanel.add(hardPanel);
            bottomControlPanel.add(goodPanel);
        }

        add(bottomControlPanel, BorderLayout.SOUTH);
        bottomControlPanel.setMinimumSize(new Dimension(getWidth(), bottomControlPanel.getHeight()));
        setVisible(true);
    }
}
