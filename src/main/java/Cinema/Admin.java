package Cinema;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class Admin extends JPanel {
    String mail, password;
    //String url= "jdbc:mysql://localhost:3306/cineme?autoReconnect=true&useSSL=false";

    String url =  "jdbc:mysql://localhost:3306/cineme?useUnicode=true\n" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&\n"
            + "wait_timeout=2147483\n"
            //+            "serverTimezone=UTC&\n"
            +"autoReconnect=true&useSSL=false";


    public Admin(JFrame frame) {

        //Fonts
        Font f1= new Font(Font.SERIF,  Font.BOLD, 60);
        Font f2= new Font(Font.SERIF, Font.PLAIN,  20);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  30);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();


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

/*
        setBorder(new EmptyBorder(150, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Set layout for the content pane
        buttons.setLayout(new GridBagLayout());
        //GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        buttons.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.SOUTH;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(600, 200, 5, 200);


 */

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


        //Add actionListener for each button
        log.addActionListener(e0->{
            this.mail=email.getText();
            this.password=psw.getText();
            System.out.println(mail + password);

            try {
                Connect c = new Connect(url);
                c.connectData();
                c.createStatement();
                c.SQLQueryAdmin(mail,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
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
        buttons.add(a, gbc);
        buttons.add(b, gbc);
        buttons.add(email, gbc);
        buttons.add(psw, gbc);
        buttons.add(log,gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons,gbc);

    }
}
