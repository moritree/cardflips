package moritree;

import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame f;
    private JTabbedPane tabbedPane;

    public Gui() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        f = new JFrame("Main GUI");

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Decks", new DecksListPanel());
        tabbedPane.addTab("Statistics", new StatisticsPanel());

        f.setSize(600, 400);
        f.setMinimumSize(new Dimension(300, 200));
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(tabbedPane, BorderLayout.CENTER);
        f.setVisible(true);
    }

    public void reload() {
        for (int i = 0; i <= tabbedPane.getTabCount(); i ++) {
            tabbedPane.removeTabAt(0);
        }

        tabbedPane.addTab("Decks", new DecksListPanel());
        tabbedPane.addTab("Statistics", new StatisticsPanel());
    }
}
