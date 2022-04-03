package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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


        Label a = new Label("            ADMIN");
        a.setFont(f1);
        Label b = new Label("__________________");
        b.setFont(f1);

        Label d = new Label("Mot de passe oublié");
        d.setFont(f3);

        //
        //TEXTFIELD : ENTRER LES COORDONNEES : SI ON CLIQUE DESSUS, LE TEXTE, L'ATTRIBUT INDIQUE PREND LA VALEUR
        //
        JTextField name = new JTextField("Entrez votre nom", 30);
        name.setForeground(new Color(59, 47, 47));
        name.setFont(f2);
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
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
        psw_oublie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                psw_oublie.setText("");
            }
        });

        JButton log = new JButton("Connexion");
        log.setBackground(new Color(59, 47, 47));
        log.setForeground(new Color(239, 223, 187));
        log.setFont(f3);

        JButton log_oublie = new JButton("Connexion");
        log_oublie.setBackground(new Color(59, 47, 47));
        log_oublie.setForeground(new Color(239, 223, 187));
        log_oublie.setFont(f3);

        JButton mp = new JButton("Admin : Mot de passe oublié");
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


        log.addActionListener(e0 -> this.admin.login(email.getText(), String.valueOf(psw.getPassword())));

        //
        //AFFICHAGE DE LA FRAME MP OUBLIE INCLUSE DANS LA CLASSE VIEW ACHAT
        //
        mp.addActionListener(e1 -> {
            this.admin.setMp_oublie(true);
            this.admin.oublie();
        });

        //RETOUR AU MENU
        close.addActionListener(e2 -> {
            this.admin.menu();
            this.admin.setVisible(false);
            this.admin.getMenu().setVisible(true);
        });

        exit.addActionListener(e3 -> System.exit(0));

        //FRAME MP OUBLIE
        log_oublie.addActionListener(e4 -> {
            this.admin.mp_oublie(name.getText(), email.getText(), String.valueOf(psw_oublie.getPassword()));
            this.admin.menu();
            this.admin.setVisible(false);
            this.admin.getMenu().setVisible(true);
        });

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        if (this.admin.getMp_oublie()) {
            buttons.add(d, gbc);
        } else {
            buttons.add(a, gbc);
        }
        buttons.add(b, gbc);

        if (!this.admin.getMp_oublie()) {
            buttons.add(email, gbc);
            buttons.add(p, gbc);
            buttons.add(psw, gbc);
            buttons.add(log, gbc);
            buttons.add(mp, gbc);
        } else {
            buttons.add(name, gbc);
            buttons.add(email, gbc);
            buttons.add(p1, gbc);
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
