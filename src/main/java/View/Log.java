package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Log extends JPanel {
    private Controller.Log log_c;
    private JFrame frame;

    public Log(Controller.Log ca, JFrame f) {
        this.log_c = ca;
        this.frame = f;
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 40);

        Label a = new Label(" CONNEXION CLIENT");
        a.setFont(f1);
        Label b = new Label("__________________");
        b.setFont(f1);

        Label d = new Label("Connexion : Mot de passe oublié");
        d.setFont(f3);

        JTextField name = new JTextField("Entrez votre nom", 30);
        name.setForeground(new Color(59, 47, 47));
        name.setFont(f2);
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }
        });

        JTextField tel = new JTextField("Entrez votre numéro de téléphone", 30);
        tel.setForeground(new Color(59, 47, 47));
        tel.setFont(f2);
        tel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tel.setText("");
            }
        });

        JTextField email = new JTextField("Entrez votre email", 30);
        email.setForeground(new Color(59, 47, 47));
        email.setFont(f2);
        email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                email.setText("");
            }
        });

        JLabel p = new JLabel("Entrez votre mot de passe");
        JPasswordField psw = new JPasswordField(30);
        psw.setForeground(new Color(59, 47, 47));
        psw.setFont(f2);
        psw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                psw.setText("");
            }
        });

        JLabel p1 = new JLabel("Entrez votre nouveau mot de passe");
        JPasswordField psw_oublie = new JPasswordField(30);
        psw_oublie.setForeground(new Color(59, 47, 47));
        psw_oublie.setFont(f2);
        psw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                psw.setText("");
            }
        });

        JButton log = new JButton("Connexion");
        log.setBackground(new Color(59, 47, 47));
        log.setForeground(new Color(239, 223, 187));
        log.setFont(f3);

        JButton log_oub = new JButton("Connexion");
        log_oub.setBackground(new Color(59, 47, 47));
        log_oub.setForeground(new Color(239, 223, 187));
        log_oub.setFont(f3);

        JButton mp = new JButton("Mot de passe oublié");
        mp.setBackground(new Color(59, 47, 47));
        mp.setForeground(new Color(239, 223, 187));
        mp.setFont(f2);

        Label c = new Label(" ");
        c.setFont(f1);

        //button to go back to menu
        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();


        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(150, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Add actionListener for each button
        log.addActionListener(e0 -> {
            this.log_c.login(email.getText(), String.valueOf(psw.getPassword()));
        });

        mp.addActionListener(e1 -> {
            this.log_c.setMp(true);
            this.log_c.oublie();
        });

        close.addActionListener(e2 -> {
            this.log_c.guest();
            this.log_c.getGuest().setVisible(true);
            this.log_c.setVisible(false);
        });

        log_oub.addActionListener(e4 -> {
            this.log_c.mp_oub(name.getText(), email.getText(), String.valueOf(psw_oublie.getPassword()), tel.getText());
            this.log_c.guest();
            this.log_c.getGuest().setVisible(true);
            this.log_c.setVisible(false);
        });

        exit.addActionListener(e3 -> System.exit(0));

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        if (this.log_c.getMp()) {
            buttons.add(d, gbc);
        } else {
            buttons.add(a, gbc);
        }
        if (!this.log_c.getMp()) {
            buttons.add(email, gbc);
            buttons.add(p, gbc);
            buttons.add(psw, gbc);
            buttons.add(log, gbc);
            buttons.add(mp, gbc);
        } else {
            buttons.add(name, gbc);
            buttons.add(tel, gbc);
            buttons.add(email, gbc);
            buttons.add(p1, gbc);
            buttons.add(psw_oublie, gbc);
            buttons.add(log_oub, gbc);
        }
        buttons.add(c, gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons, gbc);
    }

    public void mes1() {
        JOptionPane.showMessageDialog(this, "Connexion réussite");
    }

    public void mes2() {
        JOptionPane.showMessageDialog(this, "Erreur de saisie\nMot de passe ou email incorrect");
    }
}