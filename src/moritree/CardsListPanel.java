package moritree;

import org.jsoup.Jsoup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class CardsListPanel extends JPanel {
    public CardsListPanel(Card[] cards, String match) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < cards.length; i ++) {
            Card card = cards[i];

            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(getPreferredSize().width, 20));
            cell.setMinimumSize(new Dimension(getPreferredSize().width, 20));
            cell.setLayout(new BorderLayout());

            JLabel cardFrontLabel = new JLabel(Jsoup.parse(card.front).text());
            cardFrontLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cardFrontLabel.setName(Jsoup.parse(card.front).text());

            JLabel cardBackLabel = new JLabel(Jsoup.parse(card.reverse).text());
            cardBackLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cardBackLabel.setName(Jsoup.parse(card.reverse).text());

            JLabel dateDueLabel = new JLabel(card.due == null ? "NEW" : card.due.toString());
            dateDueLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            dateDueLabel.setName(dateDueLabel.getText());

            cell.add(cardFrontLabel, BorderLayout.WEST);
//            cell.add(dateDueLabel, BorderLayout.CENTER);
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
                                    String truncated =
                                            text.substring(0, text.length() - (text.endsWith("...") ? 4 : 1)) + "...";
                                    label.setText(truncated);
                                    width = met.stringWidth(label.getText());
                                }
                            });
                    super.componentResized(e);
                }
            });

            addMouseListenerToCell(cell, card);
            add(cell);
        }
    }

    private void addMouseListenerToCell(JPanel cell, Card card) {
        cell.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CardInfoFrame(card);
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
