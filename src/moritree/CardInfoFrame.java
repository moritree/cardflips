package moritree;

import org.jsoup.Jsoup;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CardInfoFrame extends JFrame {
    private final Card card;
    private JTabbedPane tabbedPane;

    public CardInfoFrame(Card card) {
        this.card = card;

        this.setTitle("Card: " + Jsoup.parse(card.front).text());
        setSize(600, 400);
        setMinimumSize(new Dimension(300, 200));
        createAndShowGui();
    }

    private void createAndShowGui() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("View", viewPanel());
        tabbedPane.addTab("Edit", editPanel());

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel viewPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        {
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

            p.add(frontPanel);
            p.add(backPanel);
        }
        return p;
    }

    private JPanel editPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        {
            JPanel frontPanel = new JPanel();
            frontPanel.setBorder(
                    BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Front")
            );
            frontPanel.setLayout(new BorderLayout());

            {
                JTextArea frontTextArea = new JTextArea();
                frontTextArea.setText(card.front);

                frontTextArea.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        changedUpdate(e);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        changedUpdate(e);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        card.front = frontTextArea.getText();
                        tabbedPane.removeTabAt(0);
                        tabbedPane.insertTab("View", null, viewPanel(), "View", 0);
                    }
                });

                JScrollPane frontTextAreaScrollPane = new JScrollPane(frontTextArea);
                frontTextAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                frontTextAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                frontPanel.add(frontTextAreaScrollPane, BorderLayout.CENTER);
            }

            JPanel backPanel = new JPanel();
            backPanel.setBorder(
                    BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Reverse")
            );
            backPanel.setLayout(new BorderLayout());

            {
                JTextArea backTextArea = new JTextArea();
                backTextArea.setText(card.reverse);

                backTextArea.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        changedUpdate(e);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        changedUpdate(e);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        card.reverse = backTextArea.getText();
                        tabbedPane.removeTabAt(0);
                        tabbedPane.insertTab("View", null, viewPanel(), "View", 0);
                    }
                });

                JScrollPane backTextAreaScrollPane = new JScrollPane(backTextArea);
                backTextAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                backTextAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                backPanel.add(backTextAreaScrollPane, BorderLayout.CENTER);
            }

            frontPanel.setPreferredSize(backPanel.getPreferredSize());

            p.add(frontPanel);
            p.add(backPanel);
        }
        return p;
    }
}
