package View;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class Register extends JPanel{
    private Controller.Register register;
    String selectedItem;
    JFrame frame;
    public Register(Controller.Register ca, JFrame f) {
        this.register=ca;
        this.frame=f;
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2= new Font(Font.SERIF, Font.PLAIN,  20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 40);

        Label a= new Label("   GUEST ACCESS");
        a.setFont(f1);
        Label b= new Label("__________________");
        b.setFont(f1);

        JTextField name= new JTextField("enter your name", 30);
        name.setForeground(new Color(59,47,47));
        name.setFont(f2);

        JTextField email= new JTextField("enter your email", 30);
        email.setForeground(new Color(59,47,47));
        email.setFont(f2);

        JTextField psw= new JTextField("enter your password", 30);
        psw.setForeground(new Color(59,47,47));
        psw.setFont(f2);

        JTextField number= new JTextField("enter your number", 30);
        number.setForeground(new Color(59,47,47));
        number.setFont(f2);

        JTextField age= new JTextField("enter your age", 30);
        age.setForeground(new Color(59,47,47));
        age.setFont(f2);

        JLabel l_b= new JLabel("Select reduction: ");
        l_b.setFont(f2);
        String benef[]={"Student", "Elder", "Big Family", "Cinema Card", "Under 14", "Under 5"};
        JList list= new JList(benef);

        JButton access = new JButton("Access");
        access.setBackground(new Color(59,47,47));
        access.setForeground(new Color(239,223,187));
        access.setFont(f3);

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

        access.addActionListener(e0->{
            this.register.register1(name.getText(),email.getText(),psw.getText(),Integer.parseInt(number.getText()),Integer.parseInt(age.getText()),list.getSelectedIndex());
            this.register.getGuest().getMenu().exit();
        });

        close.addActionListener(e1 -> {
            this.register.guest();
            this.register.setVisible(false);
            this.register.getGuest().setVisible(true);
        });

        exit.addActionListener(e4 -> this.register.getGuest().getMenu().exit());

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        buttons.add(a, gbc);
        buttons.add(b, gbc);
        buttons.add(name, gbc);
        buttons.add(email, gbc);
        buttons.add(psw, gbc);
        buttons.add(number,gbc);
        buttons.add(age,gbc);
        buttons.add(l_b,gbc);
        buttons.add(list,gbc);
        buttons.add(access,gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons,gbc);
    }

}
