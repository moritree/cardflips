package moritree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

            JLabel cardFrontLabel = new JLabel(card.front);
            cardFrontLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cardFrontLabel.setName(card.front);

            JLabel cardBackLabel = new JLabel(card.reverse);
            cardBackLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            cardBackLabel.setName(card.reverse);

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

            add(cell);
        }
    }
}
