package moritree;

import javax.swing.*;
import java.awt.*;

public class DecksListPanel extends JPanel {
    Gui gui;

    public DecksListPanel(Gui gui) {
        setLayout(new BorderLayout());

        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());
        {
            JTextField searchBarField = new JTextField();

            JButton addDeckButton = new JButton("+");

            topBarPanel.add(searchBarField, BorderLayout.CENTER);
            topBarPanel.add(addDeckButton, BorderLayout.EAST);
        }

        add(topBarPanel, BorderLayout.NORTH);
    }
}
