package ui;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    private BudgetTrackingAppUI controller;
    private static final String PROMPT_USER = "Choose from one of the following choices:";
    private JLabel promptUser;
    private JButton add;
    private JButton print;
    private JButton save;
    private JButton load;
    private JButton seeCategory;
    private JButton quit;
    private PrintTableUI table;

    public OptionPanel(BudgetTrackingAppUI controller) {
        this.controller = controller;
    }

    private void setPromptUser() {
        promptUser = new JLabel(PROMPT_USER, JLabel.CENTER);
        promptUser.setSize(WIDTH, HEIGHT / 3);
        this.add(promptUser);
    }

    private void setOptionButtons() {
        add = new JButton("Add expense");
        print = new JButton("See all expenses");
        save = new JButton("Save expense");
        load = new JButton("Load previous expenses");
        seeCategory = new JButton("See expenses in category");
        quit = new JButton("Quit application");

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(add);
        p.add(print);
        p.add(save);
        p.add(load);
        p.add(seeCategory);
        p.add(quit);
        p.setSize(WIDTH, HEIGHT / 6);
    }

    private void processOptions() {
        add.addActionListener(e -> {
            
        });

        print.addActionListener(e -> {

        });

        save.addActionListener(e -> {

        });

        load.addActionListener(e -> {

        });

        seeCategory.addActionListener(e -> {

        });

        quit.addActionListener(e -> {

        });
    }
}
