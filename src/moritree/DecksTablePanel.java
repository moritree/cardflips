package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DecksTablePanel extends JPanel {
    public DecksTablePanel(Deck[] decks) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < decks.length; i ++) {
            Deck deck = decks[i];

            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(getPreferredSize().width, 20));
            cell.setMinimumSize(new Dimension(getPreferredSize().width, 20));
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

            add(cell);
        }
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
