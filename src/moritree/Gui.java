package moritree;

import javax.swing.*;
import java.awt.*;

public class Gui {
    public Gui() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame f = new JFrame("Main GUI");

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Decks", new DecksListPanel());

        f.setSize(600, 400);
        f.setMinimumSize(new Dimension(300, 200));
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(tabbedPane, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
