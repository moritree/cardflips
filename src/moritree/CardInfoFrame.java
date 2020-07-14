package moritree;

import org.jsoup.Jsoup;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CardInfoFrame extends JFrame {
    private final Card card;

    public CardInfoFrame(Card card) {
        this.card = card;

        this.setTitle(Jsoup.parse(card.front).text());
        setSize(600, 400);
        setMinimumSize(new Dimension(300, 200));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        createAndShowGui();
    }

    private void createAndShowGui() {
        JPanel frontPanel = new JPanel();
        frontPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Front")
        );

        {
            JLabel frontLabel = new JLabel(card.front);
            frontLabel.setHorizontalAlignment(SwingConstants.CENTER);
            frontLabel.setVerticalAlignment(SwingConstants.CENTER);

            frontPanel.add(frontLabel);
        }

        JPanel backPanel = new JPanel();
        backPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Reverse")
        );

        {
            JLabel backLabel = new JLabel(card.reverse);
            backLabel.setHorizontalAlignment(SwingConstants.CENTER);
            backLabel.setVerticalAlignment(SwingConstants.CENTER);

            backPanel.add(backLabel);
        }

        add(frontPanel);
        add(backPanel);

        setVisible(true);
    }
}
