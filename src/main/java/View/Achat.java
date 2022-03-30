package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Achat extends JPanel{
    private Controller.Achat achat;
    private JFrame frame;
    public Achat(Controller.Achat ca, JFrame f){
        this.achat = ca;
        this.frame = f;
        Font f1= new Font(Font.SERIF,  Font.BOLD, 60);
        Font f2= new Font(Font.SERIF, Font.PLAIN,  20);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  30);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc_1 = new GridBagConstraints();
        GridBagConstraints gbc_2 = new GridBagConstraints();

        JPanel info = new JPanel(gbl);
        JPanel buttons = new JPanel(gbl);

        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(150, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 10, 0);

        gbc_1.gridwidth = GridBagConstraints.CENTER;
        gbc_1.insets = new Insets(0, 0, 200, 0);
        gbc_1.anchor = GridBagConstraints.SOUTHEAST;
        gbc_1.fill = GridBagConstraints.HORIZONTAL;

        gbc_2.gridwidth = GridBagConstraints.CENTER;
        gbc_2.insets = new Insets(0, 100, 200, 0);
        gbc_2.anchor = GridBagConstraints.SOUTHWEST;
        gbc_2.fill = GridBagConstraints.HORIZONTAL;


        Label a= new Label("   Achat du ticket");
        a.setFont(f1);
        Label b= new Label("__________________");
        b.setFont(f1);
        JLabel cB= new JLabel("Numero de carte bancaire:");
        JTextField carte_bancaire= new JTextField(16);
        carte_bancaire.setForeground(new Color(59,47,47));
        carte_bancaire.setFont(f2);
        carte_bancaire.setHorizontalAlignment(JTextField.CENTER);

        JLabel date= new JLabel("Date d'echeance:");
        JLabel d= new JLabel("mm-yyyy");
        JTextField date_carte= new JTextField(7);
        date_carte.setForeground(new Color(59,47,47));
        date_carte.setFont(f2);
        date_carte.setHorizontalAlignment(JTextField.CENTER);


        JLabel code= new JLabel("Code de securite:");
        JTextField code_secu= new JTextField(3);
        code_secu.setForeground(new Color(59,47,47));
        code_secu.setFont(f2);
        code_secu.setHorizontalAlignment(JTextField.CENTER);


        JButton achat = new JButton("Achat");
        achat.setBackground(new Color(59,47,47));
        achat.setForeground(new Color(239,223,187));
        achat.setFont(f3);

        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        achat.addActionListener(e0->{
            if(carte_bancaire.getText().length()==16 && isValidDate(date_carte.getText()) && code_secu.getText().length()==3)
            {
                this.achat.ticket();
            }
            else {
                this.achat.setVisible(true);
                mes2();
            }
        });

        close.addActionListener(e1 -> {
            this.achat.menu();
            this.achat.setVisible(false);
            this.achat.getMenu().setVisible(true);
        });

        exit.addActionListener(e4 -> System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));
        info.setBackground(new Color(239, 223, 187));

        info.add(a, gbc);
        info.add(b, gbc);
        info.add(cB, gbc);
        info.add(carte_bancaire, gbc);
        info.add(date, gbc);
        info.add(d, gbc);
        info.add(date_carte, gbc);
        info.add(code, gbc);
        info.add(code_secu, gbc);
        info.add(achat, gbc);
        buttons.add(close, gbc_1);
        buttons.add(exit, gbc_2);

        this.add(info,gbc);
        this.add(buttons,gbc);
    }
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void mes1(){
        JOptionPane.showMessageDialog(this, "Payement reussi\n Vous aller recevoir un mail");
    }
    public void mes2(){
        JOptionPane.showMessageDialog(this, "Erreur de saisie");
    }
    public void mes3(){
        JOptionPane.showMessageDialog(this, "Payement reussi");
    }
}
