package moritree;

import javax.swing.*;
import java.awt.*;

public class Gui {
    public Gui(Deck[] decks) {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame f = new JFrame("Main GUI");

        JPanel p = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Decks", new DecksListPanel(this));

        f.setSize(400, 400);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(tabbedPane, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
