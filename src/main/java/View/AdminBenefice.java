package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminBenefice extends JPanel {
    private JFrame frame;
    private Controller.AdminBenefice adminBenefice;

    public AdminBenefice(Controller.AdminBenefice ab, JFrame f) {
        this.frame = f;
        this.adminBenefice = ab;
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 30);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(180, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 10, 0);


        JLabel delete = new JLabel("Voulez-vous ajouter des bénéfices ? ");
        delete.setFont(f3);

        JLabel c = new JLabel(" ");
        c.setFont(f1);

        JButton ajouter = new JButton("Ajouter");
        ajouter.setBackground(new Color(59, 47, 47));
        ajouter.setForeground(new Color(239, 223, 187));
        ajouter.setFont(f3);

        JLabel mod = new JLabel("Voulez-vous supprimer ou modifier un des bénéfices suivants ?");
        mod.setFont(f3);

        String[] fil = new String[this.adminBenefice.getBenefice().getNames().size()];

        for (int i = 0; i < fil.length; i++) {
            fil[i] = this.adminBenefice.getBenefice().getNames().get(i);
        }

        JComboBox list = new JComboBox(fil);
        list.setBounds(50, 50, 90, 20);
        list.setFont(f2);

        JButton supprimer = new JButton("Supprimer");
        supprimer.setBackground(new Color(59, 47, 47));
        supprimer.setForeground(new Color(239, 223, 187));
        supprimer.setFont(f3);

        JButton modifier = new JButton("Modifier");
        modifier.setBackground(new Color(59, 47, 47));
        modifier.setForeground(new Color(239, 223, 187));
        modifier.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        //button to go back to menu
        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        ajouter.addActionListener(e0 -> {
            this.adminBenefice.a();
        });
        modifier.addActionListener(e1 -> {
            this.adminBenefice.m((String) list.getItemAt(list.getSelectedIndex()));
        });
        supprimer.addActionListener(e2 -> {
            this.adminBenefice.delete((String) list.getItemAt(list.getSelectedIndex()));
        });

        close.addActionListener(e3 -> {
            this.adminBenefice.menu();
            this.adminBenefice.setVisible(false);
            this.adminBenefice.getAdminMenu().setVisible(true);
        });

        exit.addActionListener(e4 -> System.exit(0));

        //
        //INSCRIPTION DES VALEURS SOUHAITES POUR LES ATTRIBUTS DU BENEFICE
        JTextField name = new JTextField("Nom du bénéfice");
        name.setForeground(new Color(59, 47, 47));
        name.setFont(f2);
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }
        });
        JTextField discount = new JTextField("Pourcentage de remise (%)");
        discount.setForeground(new Color(59, 47, 47));
        discount.setFont(f2);
        discount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                discount.setText("");
            }
        });

        JButton ajout = new JButton("Ajout");
        ajout.setBackground(new Color(59, 47, 47));
        ajout.setForeground(new Color(239, 223, 187));
        ajout.setFont(f3);

        //VERIFICATION : EXPRESSION VALIDES
        ajout.addActionListener(e5 -> {
            boolean b1 = discount.getText().matches("-?\\d+");
            if (b1 && Integer.parseInt(discount.getText()) > -1 && Integer.parseInt(discount.getText()) < 101)
                this.adminBenefice.ajout(name.getText(), discount.getText());
            else
                mes1();
        });

        JTextField mod_dis = new JTextField("Remise (en %) de : " + this.adminBenefice.getBenefice().getName(), 30);
        mod_dis.setForeground(new Color(59, 47, 47));
        mod_dis.setFont(f2);
        mod_dis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mod_dis.setText("");
            }
        });

        JButton modifier_remise = new JButton("Modifier");
        modifier_remise.setBackground(new Color(59, 47, 47));
        modifier_remise.setForeground(new Color(239, 223, 187));
        modifier_remise.setFont(f3);

        modifier_remise.addActionListener(e6 -> {
            this.adminBenefice.modif(mod_dis.getText());
        });

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        buttons.setBackground(new Color(239, 223, 187));

        if (this.adminBenefice.getChoix() == 0) {
            buttons.add(delete, gbc);
            buttons.add(ajouter, gbc);
            buttons.add(mod, gbc);
            buttons.add(list, gbc);
            buttons.add(c, gbc);
            buttons.add(modifier, gbc);
            buttons.add(supprimer, gbc);
        }
        //si ajouter
        else if (this.adminBenefice.getChoix() == 1) {
            buttons.add(c, gbc);
            buttons.add(name, gbc);
            buttons.add(discount, gbc);
            buttons.add(ajout, gbc);
        } else if (this.adminBenefice.getChoix() == 2) {
            buttons.add(mod_dis, gbc);
            buttons.add(modifier_remise, gbc);
        }
        buttons.add(c, gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons, gbc);
    }

    public void mes1() {
        JOptionPane.showMessageDialog(this, "Remise incorrecte");
    }
}
