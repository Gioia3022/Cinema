package Cinema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Guest extends JPanel {
    public Guest(JFrame frame){
        //Fonts
        Font f1= new Font(Font.SERIF,  Font.BOLD, 60);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  40);

         JButton login = new JButton("Login");
         login.setBackground(new Color(59,47,47));
         login.setForeground(new Color(239,223,187));
         login.setFont(f3);

         JButton register = new JButton("Register");
         register.setBackground(new Color(59,47,47));
         register.setForeground(new Color(239,223,187));
         register.setFont(f3);

         JButton anonyme= new JButton("Anonyme");
         anonyme.setBackground(new Color(59,47,47));
         anonyme.setForeground(new Color(239,223,187));
         anonyme.setFont(f3);

        //button to go back to menu
        JButton close= new JButton("Back");
        close.setBackground(new Color(59,47,47));
        close.setForeground(new Color(239,223,187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59,47,47));
        exit.setForeground(new Color(239,223,187));
        exit.setFont(f3);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();


        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(150, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /*
        //Add actionListener for each button
        login.addActionListener(e1 -> {
            frame.getContentPane().add(new Guest(frame));
            setVisible(false);
            new Guest(frame).setVisible(true);
        });

        register.addActionListener(e2 -> {
            frame.getContentPane().add(new Admin(frame));
            setVisible(false);
            new Admin(frame).setVisible(true);
        });

        anonyme.addActionListener(e3 -> {
            Admin b2 = new Admin(frame);
            frame.getContentPane().add(b2);
            setVisible(false);
            b2.setVisible(true);
        });*/

        close.addActionListener(e1 -> {
            Menu b1 = new Menu(frame);
            frame.getContentPane().add(b1);
            setVisible(false);
            b1.setVisible(true);
        });

        exit.addActionListener(e4 -> System.exit(0));

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239,223,187));

        //Add buttons to JPanel
        buttons.add(login, gbc);
        buttons.add(register, gbc);
        buttons.add(anonyme, gbc);
        buttons.add(close,gbc);
        buttons.add(exit, gbc);
        add(buttons, gbc);
    }

}
