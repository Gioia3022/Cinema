package View;

import Controller.AdminFilm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdminFillm extends JPanel {
    private Controller.AdminFilm admin;
    private JFrame frame;

    /**
     * Constructeur
     * @param adminFilm
     * @param frame
     */
    public AdminFillm(AdminFilm adminFilm, JFrame frame) {
        this.admin = adminFilm;
        this.frame = frame;
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 50);
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
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel c = new JLabel(" ");
        c.setFont(f1);
        //SUPRESSION FILM
        JLabel delete = new JLabel("Voulez-vous supprimer un des films suivants ? ");
        delete.setFont(f3);

        String[] fil = new String[this.admin.getFilm().getNames().size()];

        for (int i = 0; i < fil.length; i++) {
            fil[i] = this.admin.getFilm().getNames().get(i);
        }

        JComboBox list = new JComboBox(fil);
        list.setBounds(50, 50, 90, 20);
        list.setFont(f2);

        //CREATION NOUVEAU FILM
        //
        JLabel create = new JLabel("Voulez-vous ajouter un nouveau film ? ");
        create.setFont(f3);
        //INSCRIPTION DES ATTRIBUTS ATTENDUS
        //
        JTextField name = new JTextField("Nom du film", 30);
        name.setForeground(new Color(59, 47, 47));
        name.setFont(f2);
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }
        });

        JTextField genre = new JTextField("Genre du film", 30);
        genre.setForeground(new Color(59, 47, 47));
        genre.setFont(f2);
        genre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                genre.setText("");
            }
        });

        JTextField length = new JTextField("Dur??e du film", 30);
        length.setForeground(new Color(59, 47, 47));
        length.setFont(f2);
        length.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                length.setText("");
            }
        });

        JTextField date = new JTextField("Date de sortie du film", 30);
        date.setForeground(new Color(59, 47, 47));
        date.setFont(f2);
        date.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                date.setText("");
            }
        });

        JTextField director = new JTextField("R??alisateur du film", 30);
        director.setForeground(new Color(59, 47, 47));
        director.setFont(f2);
        director.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                director.setText("");
            }
        });

        JTextField URL = new JTextField("Lien URL de l'affiche du film", 30);
        URL.setForeground(new Color(59, 47, 47));
        URL.setFont(f2);
        URL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                URL.setText("");
            }
        });

        JButton addFilm = new JButton("Nouveau film");
        addFilm.setBackground(new Color(59, 47, 47));
        addFilm.setForeground(new Color(239, 223, 187));
        addFilm.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        //button to go back to menu
        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);


        list.addActionListener(e0 -> {
            this.admin.delete((String) list.getItemAt(list.getSelectedIndex()));
        });
        addFilm.addActionListener(e1 -> {
            boolean b1 = length.getText().matches("-?\\d+");
            if (b1 && Integer.parseInt(length.getText())<240 && length.getText()!=null) {
                if(isValidDate(date.getText())) {
                    if(name.getText()!=null && genre.getText()!= null && director.getText()!=null && URL.getText()!=null)
                        this.admin.addFilm(name.getText(), genre.getText(), length.getText(), date.getText(), director.getText(), URL.getText());
                    else {
                        mes3();
                    }
                }
                else {
                    mes1();
                }
            }
            else {
                mes2();
            }
        });
        //CLOSE ADMIN RETOUR AU MENU
        close.addActionListener(e2 -> {
            this.admin.menu();
            this.admin.setVisible(false);
            this.admin.getMenu().setVisible(true);
        });

        exit.addActionListener(e3 -> System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));
        buttons.add(delete, gbc);
        buttons.add(list, gbc);
        buttons.add(create, gbc);
        buttons.add(name, gbc);
        buttons.add(genre, gbc);
        buttons.add(length, gbc);
        buttons.add(date, gbc);
        buttons.add(director, gbc);
        buttons.add(URL, gbc);
        buttons.add(addFilm, gbc);
        buttons.add(c, gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons, gbc);
    }
    //VERIFICATION FORMAT DATE
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void mes1() {
        JOptionPane.showMessageDialog(this, "Date de sortie incorrecte");
    }

    public void mes2() {
        JOptionPane.showMessageDialog(this, "Longueur du film incorrecte");
    }

    public void mes3() {
        JOptionPane.showMessageDialog(this, "Saisie incorrecte");
    }
}
