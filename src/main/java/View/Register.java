package View;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Register extends JPanel{
    private Controller.Register register;
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

        JTextField name= new JTextField("Enter your name", 30);
        name.setForeground(new Color(59,47,47));
        name.setFont(f2);

        name.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                name.setText("");
            }
        });

        JTextField email= new JTextField("Enter your email", 30);
        email.setForeground(new Color(59,47,47));
        email.setFont(f2);

        email.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                email.setText("");
            }
        });

        JLabel p= new JLabel("Enter your password");
        JPasswordField psw= new JPasswordField(30);
        psw.setForeground(new Color(59,47,47));
        psw.setFont(f2);

        psw.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                psw.setText("");
            }
        });

        JTextField number= new JTextField("Enter your number", 30);
        number.setForeground(new Color(59,47,47));
        number.setFont(f2);

        number.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                number.setText("");
            }
        });

        JTextField age= new JTextField("Enter your age", 30);
        age.setForeground(new Color(59,47,47));
        age.setFont(f2);

        age.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                age.setText("");
            }
        });

        JLabel l_b= new JLabel("Select reduction: ");
        l_b.setFont(f2);
        String[] benef ={"None", "Under 5", "Under 14","Student", "Big Family", "Elder", "Cinema Card"};
        JComboBox list= new JComboBox(benef);
        list.setBounds(50, 50,90,20);
        list.setFont(f2);

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
            if(email.getText().contains("@")&&email.getText().contains(".")) {
                this.register.register1(name.getText(), email.getText(), String.valueOf(psw.getPassword()), number.getText(), Integer.parseInt(age.getText()), (String) list.getItemAt(list.getSelectedIndex()));
            }
            else {
                this.register.setVisible(true);
                mes3();
            }
        });

        close.addActionListener(e1 -> {
            this.register.guest();
            this.register.setVisible(false);
            this.register.getGuest().setVisible(true);
        });

        exit.addActionListener(e4 -> System.exit(0));

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        buttons.add(a, gbc);
        buttons.add(b, gbc);
        buttons.add(name, gbc);
        buttons.add(email, gbc);
        buttons.add(p,gbc);
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
    public void mes1(){
        JOptionPane.showMessageDialog(this, "Login reussi");
    }
    public void mes2(){
        JOptionPane.showMessageDialog(this, "L'utilisateur existe deja");
    }
    public void mes3(){
        JOptionPane.showMessageDialog(this, "Mail incorect");
    }
}
