package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Admin extends JPanel  {
    private Controller.Admin admin;
    JFrame frame;
    public Admin(Controller.Admin ca, JFrame f) {
        this.admin=ca;
        this.frame=f;
        //Fonts
        Font f1= new Font(Font.SERIF,  Font.BOLD, 60);
        Font f2= new Font(Font.SERIF, Font.PLAIN,  20);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  30);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(200, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);


        Label a= new Label("   ADMIN LOGIN");
        a.setFont(f1);
        Label b= new Label("__________________");
        b.setFont(f1);

        JTextField email= new JTextField("enter your email", 30);
        email.setForeground(new Color(59,47,47));
        email.setFont(f2);

        JTextField psw= new JTextField("enter your password", 30);
        psw.setForeground(new Color(59,47,47));
        psw.setFont(f2);

        JButton log = new JButton("Login");
        log.setBackground(new Color(59,47,47));
        log.setForeground(new Color(239,223,187));
        log.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59,47,47));
        exit.setForeground(new Color(239,223,187));
        exit.setFont(f3);

        //button to go back to menu
        JButton close= new JButton("Back");
        close.setBackground(new Color(59,47,47));
        close.setForeground(new Color(239,223,187));
        close.setFont(f3);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        log.addActionListener(e0->{
            this.admin.login(email.getText(),psw.getText());
        });

        close.addActionListener(e1 -> {
            this.admin.menu();
            this.admin.setVisible(false);
            this.admin.getMenu().setVisible(true);
        });

        exit.addActionListener(e2 -> this.admin.getMenu().exit());


        buttons.setBackground(new Color(239,223,187));

        //Add buttons to JPanel
        buttons.add(a, gbc);
        buttons.add(b, gbc);
        buttons.add(email, gbc);
        buttons.add(psw, gbc);
        buttons.add(log,gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons,gbc);
    }

    public void mes1(){
        JOptionPane.showMessageDialog(this, "Login reussit");
    }
    public void mes2(){
        JOptionPane.showMessageDialog(this, "Erreur de saisie\nMot de passe ou mail incorect");
    }

}
