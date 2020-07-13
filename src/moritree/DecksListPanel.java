package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DecksListPanel extends JPanel {
    Deck[] decks;

    public DecksListPanel(Gui gui) {
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

            JButton addDeckButton = new JButton("+");

            topBarPanel.add(searchBarField, BorderLayout.CENTER);
            topBarPanel.add(addDeckButton, BorderLayout.EAST);
        }

        JPanel decksTablePanel = new JPanel();
        decksTablePanel.setLayout(new BoxLayout(decksTablePanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < this.decks.length; i ++) {
            Deck deck = this.decks[i];

            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(decksTablePanel.getPreferredSize().width, 20));
            cell.setMinimumSize(new Dimension(decksTablePanel.getPreferredSize().width, 20));
            cell.setLayout(new BorderLayout());

            JLabel deckNameLabel = new JLabel(
                    "<html>" +
                            deck.name +
                            " <i><FONT COLOR=\"a1a1a1\">(" +
                            deck.cards.length +
                            " cards)</FONT></i></html>"
            );
            deckNameLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

            cell.add(deckNameLabel, BorderLayout.WEST);

            if (i % 2 == 0) {
                cell.setBackground(Color.WHITE);
            }

            addMouseListenerToCell(cell, deck);

            decksTablePanel.add(cell);
        }

        JScrollPane scrollPane = new JScrollPane(decksTablePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(topBarPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addMouseListenerToCell(JPanel cell, Deck deck) {
        cell.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DeckInfoFrame(deck);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
