package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DecksListPanel extends JPanel {
    Deck[] decks;
    String searchString = "";
    JPanel decksTablePanel;
    JScrollPane scrollPane;

    public DecksListPanel() {
        this.decks = Main.getDECKS();
        setUpGui();
    }

    private void setUpGui() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());
        {
            JTextField searchBarField = new JTextField();
            searchBarField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    searchString = searchBarField.getText();
                    decksTablePanel = new DecksTablePanel(
                            Arrays.stream(decks)
                                    .filter(x -> x.getName().toUpperCase().contains(searchString.toUpperCase()))
                                    .toArray(Deck[]::new)
                    );
                    scrollPane.setViewportView(decksTablePanel);
                }

                @Override
                public void removeUpdate(DocumentEvent e) { insertUpdate(e); }

                @Override
                public void changedUpdate(DocumentEvent e) { insertUpdate(e); }
            });

            JButton addDeckButton = new JButton("+");
            addDeckButton.addActionListener(e -> {
                Deck d = new Deck("New Deck", new Card[]{});
                java.util.List<Deck> list = Arrays.stream(decks).collect(Collectors.toList());
                list.add(d);
                decks = list.toArray(Deck[]::new);
                new DeckInfoFrame(d);
            });

            topBarPanel.add(searchBarField, BorderLayout.CENTER);
            topBarPanel.add(addDeckButton, BorderLayout.EAST);
        }

        decksTablePanel = new DecksTablePanel(decks);

        scrollPane = new JScrollPane(decksTablePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(topBarPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
