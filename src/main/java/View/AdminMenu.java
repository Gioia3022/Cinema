package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminMenu extends JPanel {

    private Controller.AdminMenu adminMenu;
    private JFrame frame;

    public AdminMenu(Controller.AdminMenu ca, JFrame f) {
        this.adminMenu = ca;
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

        setBorder(new EmptyBorder(50, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);

        /*ADMIN MENU*/

        Label a = new Label("   Menu d'admin");
        a.setFont(f1);
        Label b = new Label("__________________");
        b.setFont(f1);

        JButton Seance = new JButton("Modification des séances");
        Seance.setBackground(new Color(59, 47, 47));
        Seance.setForeground(new Color(239, 223, 187));
        Seance.setFont(f3);

        JButton Film = new JButton("Modification des film");
        Film.setBackground(new Color(59, 47, 47));
        Film.setForeground(new Color(239, 223, 187));
        Film.setFont(f3);

        JButton benefices = new JButton("Modification des bénéfices");
        benefices.setBackground(new Color(59, 47, 47));
        benefices.setForeground(new Color(239, 223, 187));
        benefices.setFont(f3);

        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);


        Seance.addActionListener(e0 -> {
            this.adminMenu.seance();
            this.adminMenu.setVisible(false);
            this.adminMenu.getAdminSession().setVisible(true);
        });
        Film.addActionListener(e1 -> {
            this.adminMenu.film();
            this.adminMenu.setVisible(false);
            this.adminMenu.getAdminFilm().setVisible(true);
        });
        benefices.addActionListener(e2 -> {
            this.adminMenu.benef();
            this.adminMenu.setVisible(false);
            this.adminMenu.getAdminBenefice().setVisible(true);
        });
        close.addActionListener(e3 -> {
            this.adminMenu.menu();
            this.adminMenu.setVisible(false);
            this.adminMenu.getMenu().setVisible(true);
        });
        exit.addActionListener(e3 -> System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));
        buttons.add(a, gbc);
        buttons.add(b, gbc);
        buttons.add(benefices, gbc);
        buttons.add(Seance, gbc);
        buttons.add(Film, gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons, gbc);
    }
}
