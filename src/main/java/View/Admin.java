package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Admin extends JPanel {
    private Controller.Admin admin;
    private JFrame frame;


    public Admin(Controller.Admin ca, JFrame f) {
        this.admin = ca;
        this.frame = f;
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 30);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(200, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);


        Label a = new Label("   ADMIN LOGIN");
        a.setFont(f1);
        Label b = new Label("__________________");
        b.setFont(f1);

        Label d = new Label("Mot de passe oublie");
        d.setFont(f3);

        JTextField name = new JTextField("enter your name", 30);
        name.setForeground(new Color(59, 47, 47));
        name.setFont(f2);

        JTextField email = new JTextField("enter your email", 30);
        email.setForeground(new Color(59, 47, 47));
        email.setFont(f2);

        JTextField psw = new JTextField("enter your password", 30);
        psw.setForeground(new Color(59, 47, 47));
        psw.setFont(f2);

        JTextField psw_oublie = new JTextField("enter your new password", 30);
        psw_oublie.setForeground(new Color(59, 47, 47));
        psw_oublie.setFont(f2);

        JButton log = new JButton("Login");
        log.setBackground(new Color(59, 47, 47));
        log.setForeground(new Color(239, 223, 187));
        log.setFont(f3);

        JButton log_oublie = new JButton("Login");
        log_oublie.setBackground(new Color(59, 47, 47));
        log_oublie.setForeground(new Color(239, 223, 187));
        log_oublie.setFont(f3);

        JButton mp = new JButton("Admin: Mot de passe oublie");
        mp.setBackground(new Color(59, 47, 47));
        mp.setForeground(new Color(239, 223, 187));
        mp.setFont(f2);

        Label c = new Label(" ");
        c.setFont(f1);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        //button to go back to menu
        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        log.addActionListener(e0 -> {
            this.admin.login(email.getText(), psw.getText());
        });

        mp.addActionListener(e1 -> {
            this.admin.setMp_oublie(true);
            this.admin.oublie();
        });

        close.addActionListener(e2 -> {
            this.admin.menu();
            this.admin.setVisible(false);
            this.admin.getMenu().setVisible(true);
        });

        exit.addActionListener(e3 -> System.exit(0));

        log_oublie.addActionListener(e4->{
            System.out.println(name);
            this.admin.mp_oublie(name.getText(),email.getText(),psw_oublie.getText());
            System.out.println(name);
            System.exit(0);
        });

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        if (this.admin.getMp_oublie()) {
            buttons.add(d,gbc);
        } else {
            buttons.add(a, gbc);
        }
        buttons.add(b, gbc);

        if (!this.admin.getMp_oublie()) {
            buttons.add(email, gbc);
            buttons.add(psw, gbc);
            buttons.add(log, gbc);
            buttons.add(mp, gbc);
        } else {
            buttons.add(name,gbc);
            buttons.add(email, gbc);
            buttons.add(psw_oublie, gbc);
            buttons.add(log_oublie, gbc);
        }
        buttons.add(c, gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons, gbc);
    }

    public void mes1() {
        JOptionPane.showMessageDialog(this, "Login reussit");
    }

    public void mes2() {
        JOptionPane.showMessageDialog(this, "Erreur de saisie\nMot de passe ou mail incorect");
    }
}
